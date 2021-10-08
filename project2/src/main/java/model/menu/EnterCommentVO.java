package model.menu;

public class EnterCommentVO {
	private String num;
	private String comment;
	private String writer;
	private String writer_id;
	private String enter_no;
	private String reg_date;
	
	
	public EnterCommentVO() {
		System.out.println("MemberVO 생성자");
	}

	public EnterCommentVO(String num, String comment, String writer, String writer_id, String enter_no,
			String reg_date) {
		super();
		this.num = num;
		this.comment = comment;
		this.writer = writer;
		this.writer_id = writer_id;
		this.enter_no = enter_no;
		this.reg_date = reg_date;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public String getEnter_no() {
		return enter_no;
	}

	public void setEnter_no(String enter_no) {
		this.enter_no = enter_no;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}


	

	
	
}
