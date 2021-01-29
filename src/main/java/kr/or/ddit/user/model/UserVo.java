package kr.or.ddit.user.model;

public class UserVo {

	private String userid;
	private String usernm;

	// 기본 생성자 (사용할 생성자 활용시 선언 필수!)
	public UserVo() {
	}

	// 사용할 생성자
	public UserVo(String userid, String usernm) {
		setUserid(userid);
		setUsernm(usernm);
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsernm() {
		return usernm;
	}

	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}

	@Override
	public String toString() {
		return "UserVo [userid=" + userid + ", usernm=" + usernm + "]";
	}

}
