package service.menu;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import encoding.SHA256;
import model.menu.EnterVO;
import model.menu.EnterCommentVO;

public class EnterDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Context init = null;
	private DataSource ds = null;

	public EnterDAO() {
		try {

			init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<EnterVO> listEnters(String search_select, String search_text) {

		List<EnterVO> enterList = new ArrayList<EnterVO>();

		if (search_text == null) {
			search_text = "";
		}

		if (search_select == null) {
			search_select = "title";
		}

		try {
			conn = ds.getConnection();

			String sql = "select * from enter where " + search_select + " like '%" + search_text
					+ "%' order by num desc";

			String sql2 = null;

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			PreparedStatement pstmt2 = null;
			ResultSet rs2 = null;

			while (rs.next()) {

				String comment = null;

				sql2 = "select count(*) as 'comment' from enter_comment where enter_no=" + rs.getString("num");
				pstmt2 = conn.prepareStatement(sql2);

				rs2 = pstmt2.executeQuery();

				if (rs2.next()) {
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

			if (pstmt != null) {
				pstmt.close();
			}

			if (rs != null) {
				rs.close();
			}

			if (conn != null) {
				conn.close();
			}

			if (pstmt2 != null) {
				pstmt.close();
			}

			if (rs2 != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return enterList;
	}

	public List<EnterCommentVO> listComments(String num) {

		List<EnterCommentVO> enterCommentList = new ArrayList<EnterCommentVO>();

		try {
			conn = ds.getConnection();

			String sql = "select * from enter_comment where enter_no = " + num + " order by reg_date desc";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				String num2 = rs.getString("num");
				String comment = rs.getString("comment");
				String writer = rs.getString("writer");
				String writer_id = rs.getString("writer_id");
				String enter_no = rs.getString("enter_no");
				String reg_date = rs.getString("reg_date").substring(2, 16);

				EnterCommentVO enterCommentVO = new EnterCommentVO(num2, comment, writer, writer_id, enter_no,
						reg_date);

				enterCommentList.add(enterCommentVO);
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

		return enterCommentList;
	}

	public void addEnter(EnterVO enter) {

		try {
			conn = ds.getConnection();

			String sql = "insert into enter (title, content, writer, writer_id, pw) values (?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, enter.getTitle());
			pstmt.setString(2, enter.getContent());
			pstmt.setString(3, enter.getWriter());
			pstmt.setString(4, enter.getWriter_id());
			pstmt.setString(5, enter.getPw());

			pstmt.executeUpdate();

			if (pstmt != null) {
				pstmt.close();
			}

			if (conn != null) {
				conn.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EnterVO findEnter(String _num, String pw_text) {

		EnterVO enterInfo = null;
		String hashpw = SHA256.encodeSha256(pw_text);

		try {
			conn = ds.getConnection();

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

				if (rs2.next()) {
					comment = rs2.getString("comment");
				}

				String num = rs.getString("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String writer_id = rs.getString("writer_id");
				String reg_date = rs.getString("reg_date").substring(0, 10);

				enterInfo = new EnterVO(num, title, content, writer, writer_id, hashpw, reg_date, comment);

				if (pstmt != null) {
					pstmt.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (conn != null) {
					conn.close();
				}

				if (pstmt2 != null) {
					pstmt2.close();
				}

				if (rs2 != null) {
					rs2.close();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return enterInfo;
	}

	public String logInEnter(String sessId, String num, String pw_text) {

		String hashpw = SHA256.encodeSha256(pw_text);

		String count = "0";
		String result = "false";
		try {
			conn = ds.getConnection();

			String sql = "select count(*) as 'count' from enter where num= ? and pw = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, num);
			pstmt.setString(2, hashpw);

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

		} catch (Exception e) {
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
			conn = ds.getConnection();

			String sql = "update enter set title = ?, content = ?, writer = ?, "
					+ "reg_date = current_timestamp where num = ? and writer_id = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setString(4, num);
			pstmt.setString(5, writer_id);

			pstmt.executeUpdate();

			if (pstmt != null) {
				pstmt.close();
			}

			if (conn != null) {
				conn.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void delEnter(String num, String sessId, String writer_id) {

		if (sessId != null) {
			if (sessId.equals("admin") || sessId.equals(writer_id)) {
				try {
					conn = ds.getConnection();

					String sql = "delete from enter where num =" + num;

					pstmt = conn.prepareStatement(sql);

					pstmt.executeUpdate();

					init(conn, pstmt, rs); // 게시글번호 정렬

					if (pstmt != null) {
						pstmt.close();
					}

					if (conn != null) {
						conn.close();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void init(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {

		int count = 0;
		String sql = "select count(*) as 'count' from enter";
		String sql2 = "select count(*) as 'count' from enter_comment";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count");
			}

			String sqlList[] = { "SET @CNT = 0", 
					"UPDATE enter SET enter.num = @CNT:=@CNT+1",
					"ALTER TABLE enter AUTO_INCREMENT=" + (count + 1), };

			for (int i = 0; i < 3; i++) {
				pstmt = conn.prepareStatement(sqlList[i]);
				pstmt.executeUpdate();
			}

		} finally {
			if (rs != null) {
				rs.close();
			}
		}

		try {
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count");
			}

			String sqlList[] = {"SET @CNT = 0",
					"UPDATE enter_comment SET enter_comment.num = @CNT:=@CNT+1",
					"ALTER TABLE enter_comment AUTO_INCREMENT=" + (count + 1)};

			for (int i = 0; i < 3; i++) {
				pstmt = conn.prepareStatement(sqlList[i]);
				pstmt.executeUpdate();
			}

		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}
}
