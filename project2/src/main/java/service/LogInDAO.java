package service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LogInDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Context init = null;
	private DataSource ds = null;

	public LogInDAO() {
		try {

			init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int Login(String userId, String userPw) {

		try {
			String sql = "select * from user where id = ? and pw = ?";

			conn = ds.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return 1;
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
			System.out.println("아이디 비교 실패");
		}
		return 0;
	}
}
