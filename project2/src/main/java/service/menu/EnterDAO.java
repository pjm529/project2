package service.menu;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import encoding.SHA256;
import model.menu.EnterVO;
import model.menu.EnterCommentVO;

public class EnterDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String url;
	private String dbUser;
	private String dbPw;

	
	public EnterDAO() {
		try {
			url = "jdbc:mysql://localhost:3306/homepage?useSSL=false";
			dbUser = "root";
			dbPw= "rootroot";
			
			Class.forName("com.mysql.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<EnterVO> listEnters(String search_select, String search_text) {
		
		List<EnterVO> enterList = new ArrayList<EnterVO>();
		
		if(search_text == null) {
			search_text = "";
		}
		
		if(search_select == null) {
			search_select = "title";
		}
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "select * from enter where " 
					+ search_select + " like '%" + search_text + "%' order by num desc";
			
			String sql2 = null;
			
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			PreparedStatement pstmt2 = null;
			ResultSet rs2 = null;
			
			while(rs.next()) {
				
				String comment = null;
				
				sql2 = "select count(*) as 'comment' from enter_comment where enter_no=" + rs.getString("num");
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
				
				EnterVO enterVO = new EnterVO(num, title, content, writer, writer_id, reg_date, comment);
				
				enterList.add(enterVO);
			}
			
			rs.close();
			rs2.close();
			pstmt.close();
			pstmt2.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return enterList;
	}
	
	public List<EnterCommentVO> listComments(String num) {
		
		List<EnterCommentVO> enterCommentList = new ArrayList<EnterCommentVO>();
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "select * from enter_comment where enter_no = " + num + " order by reg_date desc";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				String num2= rs.getString("num");
				String comment = rs.getString("comment");
				String writer = rs.getString("writer");
				String writer_id = rs.getString("writer_id");
				String enter_no = rs.getString("enter_no");
				String reg_date = rs.getString("reg_date").substring(0, 10);
				
				EnterCommentVO enterCommentVO = new EnterCommentVO(num2, comment, writer, writer_id, enter_no, reg_date);
				
				enterCommentList.add(enterCommentVO);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return enterCommentList;
	}
	
	public void addEnter(EnterVO enter) {
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "insert into enter (title, content, writer, writer_id, pw) values (?, ?, ?, ?, ?)";
			
	 		pstmt = conn.prepareStatement(sql);
	 	
	 		pstmt.setString(1, enter.getTitle());
	 		pstmt.setString(2, enter.getContent());
	 		pstmt.setString(3, enter.getWriter());
	 		pstmt.setString(4, enter.getWriter_id());
	 		pstmt.setString(5, enter.getPw());
	 	
	 		pstmt.executeUpdate();
	 	
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public EnterVO findEnter(String _num, String pw_text) {
		
		EnterVO enterInfo = null;
		String hashpw = SHA256.encodeSha256(pw_text);
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "select * from enter where num= ?";
			String sql2 = null;
			
			PreparedStatement pstmt2 = null;
			ResultSet rs2 = null;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, _num);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String comment = null;
				
				sql2 = "select count(*) as 'comment' from enter_comment where enter_no=" + _num;
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
				
				enterInfo = new EnterVO(num, title, content, writer, writer_id, hashpw, reg_date, comment);
				
				pstmt.close();
				pstmt2.close();
				conn.close();
				rs.close();
				rs2.close();
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return enterInfo;
	}
	
	public String logInEnter(String sessId, String num, String pw_text) {
		
		String hashpw = SHA256.encodeSha256(pw_text);
		
		String count = "0";
		String result = "false";
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "select count(*) as 'count' from enter where num= ? and pw = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, num);
			pstmt.setString(2, hashpw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getString("count");
			}
			
			if(count.equals("1")) {
				result = "true";
			}
				
			pstmt.close();
			conn.close();
			rs.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void modEnter(EnterVO enterVO) {
		
		String num = enterVO.getNum();
		String title = enterVO.getTitle();
		String content = enterVO.getContent();
		String writer = enterVO.getWriter();
		String writer_id = enterVO.getWriter_id();
		
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "update enter set title = ?, content = ?, writer = ?, "
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
	
	
	
	public void delEnter(String num, String sessId, String writer_id) {
		
		if(sessId != null) {
			if(sessId.equals("admin") || sessId.equals(writer_id)) {
				try {
					PreparedStatement pstmt2 = null;
					conn = DriverManager.getConnection(url, dbUser, dbPw);
					
					String sql = "delete from enter where num =" + num;
					String sql2 = "delete from enter_comment where enter_no =" + num;
					
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
		String sql = "select count(*) as 'count' from enter";
		String sql2 = "select count(*) as 'count' from enter_comment";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("count");
			}
			
			String sqlList[] = {
					"ALTER TABLE enter AUTO_INCREMENT=1", 
					"SET @CNT = 0", 
					"UPDATE enter SET enter.num = @CNT:=@CNT+1",
					"ALTER TABLE enter AUTO_INCREMENT="+(count+1), 
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
					"ALTER TABLE enter_comment AUTO_INCREMENT=1", 
					"SET @CNT = 0", 
					"UPDATE enter_comment SET enter_comment.num = @CNT:=@CNT+1",
					"ALTER TABLE enter_comment AUTO_INCREMENT="+(count+1), 
					"UPDATE enter_comment SET enter_no = enter_no - 1 where enter_no > " + num
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
