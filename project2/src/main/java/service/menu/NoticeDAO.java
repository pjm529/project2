package service.menu;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.menu.NoticeVO;

public class NoticeDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String url;
	private String dbUser;
	private String dbPw;

	
	public NoticeDAO() {
		try {
			url = "jdbc:mysql://localhost:3306/homepage?useSSL=false";
			dbUser = "root";
			dbPw= "rootroot";
			
			Class.forName("com.mysql.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<NoticeVO> listNotices(String search_select, String search_text) {
		
		List<NoticeVO> noticeList = new ArrayList<NoticeVO>();
		
		if(search_text == null) {
			search_text = "";
		}
		
		if(search_select == null) {
			search_select = "title";
		}
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "select * from notice where " 
					+ search_select + " like '%" + search_text + "%' order by num desc";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String num = rs.getString("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String writer_id = rs.getString("writer_id");
				String reg_date = rs.getString("reg_date").substring(0, 10);
				
				NoticeVO noticeVO = new NoticeVO(num, title, content, writer, writer_id, reg_date);
				
				noticeList.add(noticeVO);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return noticeList;
	}
	
	public void addNotice(NoticeVO notice) {
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "insert into notice (title, content, writer, writer_id) values (?, ?, ?, ?)";
			
	 		pstmt = conn.prepareStatement(sql);
	 	
	 		pstmt.setString(1, notice.getTitle());
	 		pstmt.setString(2, notice.getContent());
	 		pstmt.setString(3, notice.getWriter());
	 		pstmt.setString(4, notice.getWriter_id());
	 	
	 		pstmt.executeUpdate();
	 	
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public NoticeVO findNotice(String _num) {
		
		NoticeVO noticeInfo = null;
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "select * from notice where num=" + _num;
			
			pstmt = conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				String num = rs.getString("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String writer_id = rs.getString("writer_id");
				String reg_date = rs.getString("reg_date").substring(0, 10);
				
				noticeInfo = new NoticeVO(num, title, content, writer, writer_id, reg_date);
				
				pstmt.close();
				conn.close();
				rs.close();
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return noticeInfo;
	}
	
	public void modNotice(NoticeVO noticeVO) {
		
		String num = noticeVO.getNum();
		String title = noticeVO.getTitle();
		String content = noticeVO.getContent();
		String writer = noticeVO.getWriter();
		String writer_id = noticeVO.getWriter_id();
		
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "update notice set title = ?, content = ?, writer = ?, "
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
	
	public void delNotice(String num, String sessId, String writer_id) {
		
		if(sessId != null) {
			if(sessId.equals("admin") || sessId.equals(writer_id)) {
				try {
					conn = DriverManager.getConnection(url, dbUser, dbPw);
					
					String sql = "delete from notice where num =" + num;
					
					pstmt = conn.prepareStatement(sql);
				
					pstmt.executeUpdate();
					
					init(conn, pstmt, rs); // 게시글번호 정렬
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void init(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException{
		
		
		int count=0;
		String sql = "select count(*) as 'count' from notice";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("count");
			}
			
			String sqlList[] = {
					"ALTER TABLE notice AUTO_INCREMENT=1", 
					"SET @CNT = 0", 
					"UPDATE notice SET notice.num = @CNT:=@CNT+1",
					"ALTER TABLE notice AUTO_INCREMENT="+(count+1), 
					};
			
			for(int i = 0 ; i < 4 ; i++) {
				pstmt = conn.prepareStatement(sqlList[i]);
				pstmt.executeUpdate();
			}
		
		} finally {
			rs.close();
		} 
	}
}
