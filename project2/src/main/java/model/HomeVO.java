package model;

public class HomeVO {
	private String num;
	private String title;
	
	public HomeVO() {
		System.out.println("MemberVO 생성자");
	}

	public HomeVO(String num, String title) {
		super();
		this.num = num;
		this.title = title;
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

	
	
}
