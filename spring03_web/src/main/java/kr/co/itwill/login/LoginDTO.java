package kr.co.itwill.login;

public class LoginDTO {
	private String id;
	private String pw;
	
	public LoginDTO() {}

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

	@Override
	public String toString() {
		return "LoginDTO [id=" + id + ", pw=" + pw + "]";
	}

}
