package model.menu;

public class ProcessVO {
	private String num;
	private String title;
	private String content;
	private String writer;
	private String writer_id;
	private String reg_date;
	
	public ProcessVO() {
		System.out.println("MemberVO 생성자");
	}
	
	public ProcessVO(String title, String content, String writer, String writer_id) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writer_id = writer_id;
	}

	public ProcessVO(String num, String title, String content, String writer, String writer_id, String reg_date) {
		this.num = num;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writer_id = writer_id;
		this.reg_date = reg_date;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
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

	
	
}
