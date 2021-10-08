package service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import encoding.SHA256;
import model.MemberVO;

public class SearchIdDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Context init = null;
	private DataSource ds = null;

	public SearchIdDAO() {

		try {

			init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String searchId(String name, String phone) {

		String id = null;

		try {
			conn = ds.getConnection();

			String sql = "select id from user where name = ? and phone = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.setString(2, phone);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				id = rs.getString("id");
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

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public String searchPw(MemberVO member) {

		String count = null;
		String result = null;

		try {
			conn = ds.getConnection();

			String sql = "select *, count(*) as 'count' from user where id = ? and name = ? and phone = ? "
					+ "and email = ? and email_domain = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getEmail_domain());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getString("count");
			}

			if (count.equals("1")) {
				result = "true";
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

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void modPw(MemberVO member, String pw) {

		String id = member.getId();
		String name = member.getName();
		String email = member.getEmail();
		String email_domain = member.getEmail_domain();
		String phone = member.getPhone();
		String hashpw = SHA256.encodeSha256(pw);

		try {

			conn = ds.getConnection();

			String sql = "update user set pw = ? where id = ? and name = ? and phone = ? and email = ? and email_domain = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, hashpw);
			pstmt.setString(2, id);
			pstmt.setString(3, name);
			pstmt.setString(4, phone);
			pstmt.setString(5, email);
			pstmt.setString(6, email_domain);

			pstmt.executeUpdate();

			if (pstmt != null) {
				pstmt.close();
			}

			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
