package model;

public class MemberVO {
	private String num;
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private String email_domain;
	private String year;
	private String month;
	private String day;
	private String gender;
	private String reg_date;
	
	public MemberVO() {
		System.out.println("MemberVO 생성자");
	}

	public MemberVO(String num, String id, String pw, String name) {
		super();
		this.num = num;
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	
	public MemberVO(String id, String name, String phone, String email, String email_domain) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.email_domain = email_domain;
	}

	public MemberVO(String id, String pw, String name, String phone, String email, String email_domain, String year,
			String month, String day, String gender) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.email_domain = email_domain;
		this.year = year;
		this.month = month;
		this.day = day;
		this.gender = gender;
	}

	
	public MemberVO(String num, String id, String pw, String name, String phone, String email, String email_domain,
			String year, String month, String day, String gender) {
		super();
		this.num = num;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.email_domain = email_domain;
		this.year = year;
		this.month = month;
		this.day = day;
		this.gender = gender;
	}

	public MemberVO(String num, String id, String pw, String name, String phone, String email, String email_domain,
			String year, String month, String day, String gender, String reg_date) {
		super();
		this.num = num;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.email_domain = email_domain;
		this.year = year;
		this.month = month;
		this.day = day;
		this.gender = gender;
		this.reg_date = reg_date;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail_domain() {
		return email_domain;
	}

	public void setEmail_domain(String email_domain) {
		this.email_domain = email_domain;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	
	
	
}
