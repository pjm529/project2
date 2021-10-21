package model.menu;

public class RecruitVO {
	private String num;
	private String title;
	private String content;
	private String writer;
	private String writer_id;
	private String training_period;
	private String recruit_period;
	private String time;
	private String count;
	private String location;
	private String professor;
	private String reg_date;
	private String views;
	
	public RecruitVO() {
		System.out.println("MemberVO 생성자");
	}
	
	
	public RecruitVO(String num, String title, String content, String writer, String writer_id, String reg_date) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writer_id = writer_id;
		this.reg_date = reg_date;
	}
	
	public RecruitVO(String num, String title, String content, String writer, String writer_id, String reg_date,
			String views) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writer_id = writer_id;
		this.reg_date = reg_date;
		this.views = views;
	}


	public RecruitVO(String title, String content, String writer, String writer_id, String training_period,
			String recruit_period, String time, String count, String location, String professor) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writer_id = writer_id;
		this.training_period = training_period;
		this.recruit_period = recruit_period;
		this.time = time;
		this.count = count;
		this.location = location;
		this.professor = professor;
	}

	public RecruitVO(String num, String title, String content, String writer, String writer_id, String training_period,
			String recruit_period, String time, String count, String location, String professor, String reg_date) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writer_id = writer_id;
		this.training_period = training_period;
		this.recruit_period = recruit_period;
		this.time = time;
		this.count = count;
		this.location = location;
		this.professor = professor;
		this.reg_date = reg_date;
	}

	
	public RecruitVO(String num, String title, String content, String writer, String writer_id, String training_period,
			String recruit_period, String time, String count, String location, String professor, String reg_date,
			String views) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writer_id = writer_id;
		this.training_period = training_period;
		this.recruit_period = recruit_period;
		this.time = time;
		this.count = count;
		this.location = location;
		this.professor = professor;
		this.reg_date = reg_date;
		this.views = views;
	}


	public String getViews() {
		return views;
	}


	public void setViews(String views) {
		this.views = views;
	}


	public String getTraining_period() {
		return training_period;
	}


	public void setTraining_period(String training_period) {
		this.training_period = training_period;
	}


	public String getRecruit_period() {
		return recruit_period;
	}


	public void setRecruit_period(String recruit_period) {
		this.recruit_period = recruit_period;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getCount() {
		return count;
	}


	public void setCount(String count) {
		this.count = count;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getProfessor() {
		return professor;
	}


	public void setProfessor(String professor) {
		this.professor = professor;
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
