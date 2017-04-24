package first.com.model;

import java.util.Date;

public class BcommentDTO {
	
	private int bcomment_id;
	private int board_id;
	private int bgroup_id;//게시판 별 구분 아이디
	private String bcomment_content;
	private Date bcomment_date;
	private int bcomment_password;
	private int member_id;
	
	
	public int getBcomment_id() {
		return bcomment_id;
	}
	public void setBcomment_id(int bcomment_id) {
		this.bcomment_id = bcomment_id;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public int getBgroup_id() {
		return bgroup_id;
	}
	public void setBgroup_id(int bgroup_id) {
		this.bgroup_id = bgroup_id;
	}
	public String getBcomment_content() {
		return bcomment_content;
	}
	public void setBcomment_content(String bcomment_content) {
		this.bcomment_content = bcomment_content;
	}
	public Date getBcomment_date() {
		return bcomment_date;
	}
	public void setBcomment_date(Date bcomment_date) {
		this.bcomment_date = bcomment_date;
	}
	public int getBcomment_password() {
		return bcomment_password;
	}
	public void setBcomment_password(int bcomment_password) {
		this.bcomment_password = bcomment_password;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	
	

}
