package service;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.HomeVO;
import model.menu.AdVO;

public class HomeDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String url;
	private String dbUser;
	private String dbPw;

	
	public HomeDAO() {
		try {
			url = "jdbc:mysql://localhost:3306/homepage?useSSL=false";
			dbUser = "root";
			dbPw= "rootroot";
			
			Class.forName("com.mysql.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<HomeVO> list(String menu) {
		
		List<HomeVO> adList = new ArrayList<HomeVO>();
		int i = 0;
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "select num, title from " + menu + " order by num desc";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next() && i < 3) {
				String num = rs.getString("num");
				String title = rs.getString("title");
				
				HomeVO homeVO = new HomeVO(num, title);
				
				adList.add(homeVO);
				i++;
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return adList;
	}
}
