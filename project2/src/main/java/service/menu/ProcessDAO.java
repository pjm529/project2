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

import model.menu.ProcessVO;

public class ProcessDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Context init = null;
	private DataSource ds = null;

	public ProcessDAO() {
		try {

			init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ProcessVO> listProcesss(String search_select, String search_text) {

		List<ProcessVO> processList = new ArrayList<ProcessVO>();

		if (search_text == null) {
			search_text = "";
		}

		if (search_select == null) {
			search_select = "title";
		}

		try {
			conn = ds.getConnection();

			String sql = "select * from process where " + search_select + " like '%" + search_text
					+ "%' order by num desc";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String num = rs.getString("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String writer_id = rs.getString("writer_id");
				String reg_date = rs.getString("reg_date").substring(0, 10);

				ProcessVO processVO = new ProcessVO(num, title, content, writer, writer_id, reg_date);

				processList.add(processVO);
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

		return processList;
	}

	public void addProcess(ProcessVO process) {

		try {
			conn = ds.getConnection();

			String sql = "insert into process (title, content, writer, writer_id) values (?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, process.getTitle());
			pstmt.setString(2, process.getContent());
			pstmt.setString(3, process.getWriter());
			pstmt.setString(4, process.getWriter_id());

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

	public ProcessVO findProcess(String _num) {

		ProcessVO processInfo = null;

		try {
			conn = ds.getConnection();

			String sql = "select * from process where num=" + _num;

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String num = rs.getString("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String writer_id = rs.getString("writer_id");
				String reg_date = rs.getString("reg_date").substring(0, 10);

				processInfo = new ProcessVO(num, title, content, writer, writer_id, reg_date);

				if (pstmt != null) {
					pstmt.close();
				}

				if (rs != null) {
					rs.close();
				}

				if (conn != null) {
					conn.close();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return processInfo;
	}

	public void modProcess(ProcessVO processVO) {

		String num = processVO.getNum();
		String title = processVO.getTitle();
		String content = processVO.getContent();
		String writer = processVO.getWriter();
		String writer_id = processVO.getWriter_id();

		try {
			conn = ds.getConnection();

			String sql = "update process set title = ?, content = ?, writer = ?, "
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

	public void delProcess(String num, String sessId, String writer_id) {

		if (sessId != null) {
			if (sessId.equals("admin") || sessId.equals(writer_id)) {
				try {
					conn = ds.getConnection();

					String sql = "delete from process where num =" + num;

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
		String sql = "select count(*) as 'count' from process";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count");
			}

			String sqlList[] = { "ALTER TABLE process AUTO_INCREMENT=1", "SET @CNT = 0",
					"UPDATE process SET process.num = @CNT:=@CNT+1",
					"ALTER TABLE process AUTO_INCREMENT=" + (count + 1) };

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
