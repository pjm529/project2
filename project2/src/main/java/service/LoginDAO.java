package service;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import encoding.SHA256;

public class LoginDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Context init = null;
	private DataSource ds = null;

	public LoginDAO() {
		try {

			init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String login(String id, String pw, HttpSession session) {
		String name = null;
		String num = null;
		String hashpw = SHA256.encodeSha256(pw);

		try {
			conn = ds.getConnection();

			String sql = "select name, num from user where id = ? and pw = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, hashpw);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("name");
				num = rs.getString("num");
				session.setAttribute("num", num);
			}

			if (pstmt != null) {
				pstmt.close();
			}

			if (rs != null) {
				rs.close();
			}

			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return name;
	}
}
