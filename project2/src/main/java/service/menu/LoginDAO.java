package service.menu;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import encoding.SHA256;
import model.menu.AdVO;
import model.menu.ProcessVO;

public class LoginDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String url;
	private String dbUser;
	private String dbPw;

	
	public LoginDAO() {
		try {
			url = "jdbc:mysql://localhost:3306/homepage?useSSL=false";
			dbUser = "root";
			dbPw= "rootroot";
			
			Class.forName("com.mysql.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String login(String id, String pw, HttpSession session) {
		String name = null;
		String num = null;
		String hashpw = SHA256.encodeSha256(pw);

		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "select name, num from user where id = ? and pw = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, hashpw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				name = rs.getString("name");
				num = rs.getString("num");
				session.setAttribute("num", num);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return name;
	}
}
	