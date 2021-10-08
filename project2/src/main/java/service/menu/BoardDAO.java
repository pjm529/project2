package service.menu;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.menu.BoardVO;
import model.menu.BoardCommentVO;

public class BoardDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String url;
	private String dbUser;
	private String dbPw;

	
	public BoardDAO() {
		try {
			url = "jdbc:mysql://localhost:3306/homepage?useSSL=false";
			dbUser = "root";
			dbPw= "rootroot";
			
			Class.forName("com.mysql.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BoardVO> listBoards(String search_select, String search_text) {
		
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		if(search_text == null) {
			search_text = "";
		}
		
		if(search_select == null) {
			search_select = "title";
		}
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "select * from board where " 
					+ search_select + " like '%" + search_text + "%' order by num desc";
			
			String sql2 = null;
			
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			PreparedStatement pstmt2 = null;
			ResultSet rs2 = null;
			
			while(rs.next()) {
				
				String comment = null;
				
				sql2 = "select count(*) as 'comment' from board_comment where board_no=" + rs.getString("num");
				pstmt2 = conn.prepareStatement(sql2);
				
				rs2 = pstmt2.executeQuery();
				
				if(rs2.next()) {
					comment = rs2.getString("comment");
				}
				
				String num = rs.getString("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String writer_id = rs.getString("writer_id");
				String reg_date = rs.getString("reg_date").substring(0, 10);
				
				BoardVO boardVO = new BoardVO(num, title, content, writer, writer_id, reg_date, comment);
				
				boardList.add(boardVO);
			}
			
			rs.close();
			rs2.close();
			pstmt.close();
			pstmt2.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boardList;
	}
	
	public List<BoardCommentVO> listComments(String num) {
		
		List<BoardCommentVO> boardCommentList = new ArrayList<BoardCommentVO>();
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "select * from board_comment where board_no = " + num + " order by reg_date desc";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				String num2= rs.getString("num");
				String comment = rs.getString("comment");
				String writer = rs.getString("writer");
				String writer_id = rs.getString("writer_id");
				String board_no = rs.getString("board_no");
				String reg_date = rs.getString("reg_date").substring(0, 10);
				
				BoardCommentVO boardCommentVO = new BoardCommentVO(num2, comment, writer, writer_id, board_no, reg_date);
				
				boardCommentList.add(boardCommentVO);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boardCommentList;
	}
	
	public void addBoard(BoardVO board) {
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "insert into board (title, content, writer, writer_id) values (?, ?, ?, ?)";
			
	 		pstmt = conn.prepareStatement(sql);
	 	
	 		pstmt.setString(1, board.getTitle());
	 		pstmt.setString(2, board.getContent());
	 		pstmt.setString(3, board.getWriter());
	 		pstmt.setString(4, board.getWriter_id());
	 	
	 		pstmt.executeUpdate();
	 	
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BoardVO findBoard(String _num) {
		
		BoardVO boardInfo = null;
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "select * from board where num=" + _num;
			String sql2 = null;
			
			PreparedStatement pstmt2 = null;
			ResultSet rs2 = null;
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String comment = null;
				
				sql2 = "select count(*) as 'comment' from board_comment where board_no=" + _num;
				pstmt2 = conn.prepareStatement(sql2);
				
				rs2 = pstmt2.executeQuery();
				
				if(rs2.next()) {
					comment = rs2.getString("comment");
				}
				
				String num = rs.getString("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String writer_id = rs.getString("writer_id");
				String reg_date = rs.getString("reg_date").substring(0, 10);
				
				boardInfo = new BoardVO(num, title, content, writer, writer_id, reg_date, comment);
				
				pstmt.close();
				pstmt2.close();
				conn.close();
				rs.close();
				rs2.close();
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return boardInfo;
	}
	
	public void modBoard(BoardVO boardVO) {
		
		String num = boardVO.getNum();
		String title = boardVO.getTitle();
		String content = boardVO.getContent();
		String writer = boardVO.getWriter();
		String writer_id = boardVO.getWriter_id();
		
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "update board set title = ?, content = ?, writer = ?, "
					+ "reg_date = current_timestamp where num = ? and writer_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setString(4, num);
			pstmt.setString(5, writer_id);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void delBoard(String num, String sessId, String writer_id) {
		
		if(sessId != null) {
			if(sessId.equals("admin") || sessId.equals(writer_id)) {
				try {
					PreparedStatement pstmt2 = null;
					conn = DriverManager.getConnection(url, dbUser, dbPw);
					
					String sql = "delete from board where num =" + num;
					String sql2 = "delete from board_comment where board_no =" + num;
					
					pstmt = conn.prepareStatement(sql);
					pstmt2 = conn.prepareStatement(sql2);
					
					pstmt.executeUpdate();
					pstmt2.executeUpdate();
					
					init(conn, pstmt, rs, num); // 게시글번호 정렬
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void init(Connection conn, PreparedStatement pstmt, ResultSet rs, String num) throws SQLException{
		
		
		int count=0;
		String sql = "select count(*) as 'count' from board";
		String sql2 = "select count(*) as 'count' from board_comment";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("count");
			}
			
			String sqlList[] = {
					"ALTER TABLE board AUTO_INCREMENT=1", 
					"SET @CNT = 0", 
					"UPDATE board SET board.num = @CNT:=@CNT+1",
					"ALTER TABLE board AUTO_INCREMENT="+(count+1), 
					};
			
			for(int i = 0 ; i < 4 ; i++) {
				pstmt = conn.prepareStatement(sqlList[i]);
				pstmt.executeUpdate();
			}
		
		} finally {
			rs.close();
		}  
		
		try {
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("count");
			}
			
			String sqlList[] = {
					"ALTER TABLE board_comment AUTO_INCREMENT=1", 
					"SET @CNT = 0", 
					"UPDATE board_comment SET board_comment.num = @CNT:=@CNT+1",
					"ALTER TABLE board_comment AUTO_INCREMENT="+(count+1), 
					"UPDATE board_comment SET board_no = board_no - 1 where board_no > " + num
					};
			
			for(int i = 0 ; i < 5 ; i++) {
				pstmt = conn.prepareStatement(sqlList[i]);
				pstmt.executeUpdate();
			}
		
		} finally {
			rs.close();
		}
	}
}
