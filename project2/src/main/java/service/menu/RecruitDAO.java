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

import model.menu.RecruitVO;

public class RecruitDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Context init = null;
	private DataSource ds = null;

	public RecruitDAO() {
		try {

			init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MySQL");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<RecruitVO> listRecruits(String search_select, String search_text) {

		List<RecruitVO> recruitList = new ArrayList<RecruitVO>();

		if (search_text == null) {
			search_text = "";
		}

		if (search_select == null) {
			search_select = "title";
		}

		try {
			conn = ds.getConnection();

			String sql = "select * from recruit where " + search_select + " like '%" + search_text
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

				RecruitVO recruitVO = new RecruitVO(num, title, content, writer, writer_id, reg_date);

				recruitList.add(recruitVO);
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

		return recruitList;
	}

	public void addRecruit(RecruitVO recruit) {

		try {
			conn = ds.getConnection();

			String sql = "insert into recruit (title, content, writer, writer_id, training_period, "
					+ "recruit_period, time, count, location, professor) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, recruit.getTitle());
			pstmt.setString(2, recruit.getContent());
			pstmt.setString(3, recruit.getWriter());
			pstmt.setString(4, recruit.getWriter_id());
			pstmt.setString(5, recruit.getTraining_period());
			pstmt.setString(6, recruit.getRecruit_period());
			pstmt.setString(7, recruit.getTime());
			pstmt.setString(8, recruit.getCount());
			pstmt.setString(9, recruit.getLocation());
			pstmt.setString(10, recruit.getProfessor());

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

	public RecruitVO findRecruit(String _num) {

		RecruitVO recruitInfo = null;

		try {
			conn = ds.getConnection();

			String sql = "select * from recruit where num=" + _num;

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String num = rs.getString("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String writer_id = rs.getString("writer_id");
				String training_period = rs.getString("training_period");
				String recruit_period = rs.getString("recruit_period");
				String time = rs.getString("time");
				String count = rs.getString("count");
				String location = rs.getString("location");
				String professor = rs.getString("professor");
				String reg_date = rs.getString("reg_date").substring(0, 10);

				recruitInfo = new RecruitVO(num, title, content, writer, writer_id, training_period, recruit_period,
						time, count, location, professor, reg_date);

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

		return recruitInfo;
	}

	public void modRecruit(RecruitVO recruitVO) {

		String num = recruitVO.getNum();
		String title = recruitVO.getTitle();
		String content = recruitVO.getContent();
		String writer = recruitVO.getWriter();
		String writer_id = recruitVO.getWriter_id();
		String training_period = recruitVO.getTraining_period();
		String recruit_period = recruitVO.getRecruit_period();
		String time = recruitVO.getTime();
		String count = recruitVO.getCount();
		String location = recruitVO.getLocation();
		String professor = recruitVO.getProfessor();

		try {
			conn = ds.getConnection();

			String sql = "update recruit set title = ?, content = ?, writer = ?, training_period = ?, recruit_period = ?"
					+ ", time = ?, count = ?, location = ?, professor = ?, reg_date = current_timestamp "
					+ "where num = ? and writer_id = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setString(4, training_period);
			pstmt.setString(5, recruit_period);
			pstmt.setString(6, time);
			pstmt.setString(7, count);
			pstmt.setString(8, location);
			pstmt.setString(9, professor);
			pstmt.setString(10, num);
			pstmt.setString(11, writer_id);

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

	public void delRecruit(String num, String sessId, String writer_id) {

		if (sessId != null) {
			if (sessId.equals("admin") || sessId.equals(writer_id)) {
				try {
					conn = ds.getConnection();

					String sql = "delete from recruit where num =" + num;

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
		String sql = "select count(*) as 'count' from recruit";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count");
			}

			String sqlList[] = { "ALTER TABLE recruit AUTO_INCREMENT=1", "SET @CNT = 0",
					"UPDATE recruit SET recruit.num = @CNT:=@CNT+1",
					"ALTER TABLE recruit AUTO_INCREMENT=" + (count + 1) };

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
