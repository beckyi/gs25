package kr.ac.sungkyul.gs25.vo;

public class PassLinkVo {
	private Long no;
	private String link;
	private String state;
	private Long user_no;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getUser_no() {
		return user_no;
	}
	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}
	
	@Override
	public String toString() {
		return "PassLinkVo [no=" + no + ", link=" + link + ", state=" + state + ", user_no=" + user_no + "]";
	}
}
