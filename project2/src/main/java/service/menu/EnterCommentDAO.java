package service.menu;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.menu.EnterCommentVO;

public class EnterCommentDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Context init = null;
	private DataSource ds = null;

	public EnterCommentDAO() {
		try {

			init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addComment(EnterCommentVO enterComment) {

		try {
			conn = ds.getConnection();

			String sql = "insert into enter_comment (comment, writer, writer_id, enter_no) values (?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, enterComment.getComment());
			pstmt.setString(2, enterComment.getWriter());
			pstmt.setString(3, enterComment.getWriter_id());
			pstmt.setString(4, enterComment.getEnter_no());

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

	public void delComment(String num, String sessId, String comment_writer_id) {

		if (sessId != null) {
			if (sessId.equals("admin") || sessId.equals(comment_writer_id)) {
				try {
					conn = ds.getConnection();

					String sql = "delete from enter_comment where num =" + num;

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
		String sql = "select count(*) as 'count' from enter_comment";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count");
			}

			String sqlList[] = { "ALTER TABLE enter_comment AUTO_INCREMENT=1", "SET @CNT = 0",
					"UPDATE enter_comment SET enter_comment.num = @CNT:=@CNT+1",
					"ALTER TABLE enter_comment AUTO_INCREMENT=" + (count + 1) };

			for (int i = 0; i < 4; i++) {
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
