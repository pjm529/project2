package service;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.HomeVO;

public class HomeDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Context init = null;
	private DataSource ds = null;

	public HomeDAO() {
		try {

			init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<HomeVO> list(String menu) {

		List<HomeVO> adList = new ArrayList<HomeVO>();
		int i = 0;

		try {
			conn = ds.getConnection();

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

		return adList;
	}
}
