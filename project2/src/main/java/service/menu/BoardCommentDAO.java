package service.menu;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.menu.BoardCommentVO;

public class BoardCommentDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Context init = null;
	private DataSource ds = null;

	public BoardCommentDAO() {
		try {

			init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addComment(BoardCommentVO boardComment) {

		try {
			conn = ds.getConnection();

			String sql = "insert into board_comment (comment, writer, writer_id, board_no) values (?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boardComment.getComment());
			pstmt.setString(2, boardComment.getWriter());
			pstmt.setString(3, boardComment.getWriter_id());
			pstmt.setString(4, boardComment.getBoard_no());

			pstmt.executeUpdate();

			if (pstmt != null) {
				pstmt.close();
			}

			if (conn != null) {
				conn.close();
			}
			;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String delComment(String num, String sessId, String comment_writer_id) {

		String result = "false";

		if (sessId != null) {
			if (sessId.equals("admin") || sessId.equals(comment_writer_id)) {
				try {
					conn = ds.getConnection();

					String sql = "delete from board_comment where num =" + num;

					pstmt = conn.prepareStatement(sql);

					pstmt.executeUpdate();

					init(conn, pstmt, rs); // 게시글번호 정렬

					result = "true";

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

		return result;
	}

	public void init(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {

		int count = 0;
		String sql = "select count(*) as 'count' from board_comment";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count");
			}

			String sqlList[] = { "ALTER TABLE board_comment AUTO_INCREMENT=1", "SET @CNT = 0",
					"UPDATE board_comment SET board_comment.num = @CNT:=@CNT+1",
					"ALTER TABLE board_comment AUTO_INCREMENT=" + (count + 1) };

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
