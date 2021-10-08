package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import encoding.SHA256;
import model.MemberVO;

public class MemberDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String url;
	private String dbUser;
	private String dbPw;

	
	public MemberDAO() {
		try {
			url = "jdbc:mysql://localhost:3306/homepage?useSSL=false";
			dbUser = "root";
			dbPw= "rootroot";
			
			Class.forName("com.mysql.jdbc.Driver");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MemberVO> listMembers() {
		List<MemberVO> membersList = new ArrayList<MemberVO>();
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			String sql = "select * from user";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String num = rs.getString("num");
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String email_domain = rs.getString("email_domain");
				String year = rs.getString("year");
				String month = rs.getString("month");
				String day = rs.getString("day");
				String gender = rs.getString("gender");
				String reg_date = rs.getString("reg_date");
				
				MemberVO memberVO = new MemberVO(num, id, pw, name, phone, 
						email, email_domain, year, month, day, gender, reg_date);
				membersList.add(memberVO);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return membersList;
	}
	
	public void addMember(MemberVO m) {
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String id = m.getId();
			String pw = m.getPw();
			String name = m.getName();
			String phone = m.getPhone();
			String email = m.getEmail();
			String email_domain = m.getEmail_domain();
			String year = m.getYear();
			String month = m.getMonth();
			String day = m.getDay();
			String gender = m.getGender();
			
			String hashpw = SHA256.encodeSha256(pw);
			
			String sql = "insert into user (id, pw, name, phone, email, email_domain, year, month, day, gender) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, hashpw);
			pstmt.setString(3, name);
			pstmt.setString(4, phone);
			pstmt.setString(5, email);
			pstmt.setString(6, email_domain);
			pstmt.setString(7, year);
			pstmt.setString(8, month);
			pstmt.setString(9, day);
			pstmt.setString(10, gender);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberVO findMember(String _num) {
		
		MemberVO memInfo = null;
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "select * from user where num=" + _num;
			
			pstmt = conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if (rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String email_domain = rs.getString("email_domain");
				String year = rs.getString("year");
				String month = rs.getString("month");
				String day = rs.getString("day");
				String gender = rs.getString("gender");
				
				memInfo = new MemberVO(_num, id, pw, name, phone, email, email_domain, year, month, day, gender);
				
				pstmt.close();
				conn.close();
				rs.close();
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return memInfo;
	}
	
	public void modMember(MemberVO memberVO) {
		
		String num = memberVO.getNum();
		String name = memberVO.getName();
		String email = memberVO.getEmail();
		String email_domain = memberVO.getEmail_domain();
		String phone = memberVO.getPhone();
		String year = memberVO.getYear();
		String month = memberVO.getMonth();
		String day = memberVO.getDay();
		String gender = memberVO.getGender();
		
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "update user set name = ?, phone = ?, email = ?, email_domain = ?,"
					+ " year = ?,  month = ?, day = ?, gender = ? where num =" + num;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, email);
			pstmt.setString(4, email_domain);
			pstmt.setString(5, year);
			pstmt.setString(6, month);
			pstmt.setString(7, day);
			pstmt.setString(8, gender);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void modMemberMy(MemberVO memberVO) {
		
		String num = memberVO.getNum();
		String pw = memberVO.getPw();
		String name = memberVO.getName();
		String email = memberVO.getEmail();
		String email_domain = memberVO.getEmail_domain();
		String phone = memberVO.getPhone();
		String year = memberVO.getYear();
		String month = memberVO.getMonth();
		String day = memberVO.getDay();
		String gender = memberVO.getGender();
		
		String hashpw = SHA256.encodeSha256(pw);
		
		try {
			conn = DriverManager.getConnection(url, dbUser, dbPw);
			
			String sql = "update user set pw = ?, name = ?, phone = ?, email = ?, email_domain = ?,"
					+ " year = ?,  month = ?, day = ?, gender = ? where num =" + num;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, hashpw);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			pstmt.setString(4, email);
			pstmt.setString(5, email_domain);
			pstmt.setString(6, year);
			pstmt.setString(7, month);
			pstmt.setString(8, day);
			pstmt.setString(9, gender);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void delMember(String num, String sessId, String sessNum) {
		
		if(sessId != null) {
			if(sessId.equals("admin") || num.equals(sessNum)) {
				try {
					conn = DriverManager.getConnection(url, dbUser, dbPw);
					
					String sql = "delete from user where num =" + num;
					
					pstmt = conn.prepareStatement(sql);
				
					pstmt.executeUpdate();
					
					init(conn, pstmt, rs); // 게시글번호 정렬
					
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void init(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException{
		
		
		int count=0;
		String sql = "select count(*) as 'count' from user";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("count");
			}
			
			String sqlList[] = {
					"ALTER TABLE user AUTO_INCREMENT=1", 
					"SET @CNT = 0", 
					"UPDATE user SET user.num = @CNT:=@CNT+1",
					"ALTER TABLE user AUTO_INCREMENT="+(count+1), 
					};
			
			for(int i = 0 ; i < 4 ; i++) {
				pstmt = conn.prepareStatement(sqlList[i]);
				pstmt.executeUpdate();
			}
		
		} finally {
			rs.close();
		} 
	}
}
