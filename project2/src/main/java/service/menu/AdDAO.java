package service.menu;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.menu.AdVO;

public class AdDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String url;
	private String dbUser;
	private String dbPw;

	
	public AdDAO() {
		try {
			url = "jdbc:mysql://localhost:3306/homepage?useSSL=false";
			dbUser = "root";
			dbPw= "rootroot";
			
			Class.forName("com.mysql.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<AdVO> listAds(String search_select, String search_text) {
		
		List<AdVO> adList = new ArrayList<AdVO>();
		
		if(search_text == null) {
			search_text = "";
		}
		
		if(search_select == null) {
			search_select = "title";
		}
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "select * from ad where " 
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
				
				AdVO adVO = new AdVO(num, title, content, writer, writer_id, reg_date);
				
				adList.add(adVO);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return adList;
	}
	
	public void addAd(AdVO ad) {
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "insert into ad (title, content, writer, writer_id) values (?, ?, ?, ?)";
			
	 		pstmt = conn.prepareStatement(sql);
	 	
	 		pstmt.setString(1, ad.getTitle());
	 		pstmt.setString(2, ad.getContent());
	 		pstmt.setString(3, ad.getWriter());
	 		pstmt.setString(4, ad.getWriter_id());
	 	
	 		pstmt.executeUpdate();
	 	
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public AdVO findAd(String _num) {
		
		AdVO adInfo = null;
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "select * from ad where num=" + _num;
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String num = rs.getString("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String writer_id = rs.getString("writer_id");
				String reg_date = rs.getString("reg_date").substring(0, 10);
				
				adInfo = new AdVO(num, title, content, writer, writer_id, reg_date);
				
				pstmt.close();
				conn.close();
				rs.close();
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return adInfo;
	}
	
	public void modAd(AdVO adVO) {
		
		String num = adVO.getNum();
		String title = adVO.getTitle();
		String content = adVO.getContent();
		String writer = adVO.getWriter();
		String writer_id = adVO.getWriter_id();
		
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "update ad set title = ?, content = ?, writer = ?, "
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
	
	public void delAd(String num, String sessId, String writer_id) {
		
		if(sessId != null) {
			if(sessId.equals("admin") || sessId.equals(writer_id)) {
				try {
					conn = DriverManager.getConnection(url, dbUser, dbPw);
					
					String sql = "delete from ad where num =" + num;
					
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
		String sql = "select count(*) as 'count' from ad";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("count");
			}
			
			String sqlList[] = {
					"ALTER TABLE ad AUTO_INCREMENT=1", 
					"SET @CNT = 0", 
					"UPDATE ad SET ad.num = @CNT:=@CNT+1",
					"ALTER TABLE ad AUTO_INCREMENT="+(count+1), 
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
