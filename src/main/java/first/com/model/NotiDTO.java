package first.com.model;

import java.util.Date;

public class NotiDTO {
	
	private int noti_id;
	private int member_id;
	private int noti_sender_id;
	private String noti_subject;
	private String noti_url;
	private Date noti_date;
	private int noti_typeid;
	private String noti_content;
	private int noti_confirm;
	private int board_id;
	private int bgroup_id;
	private String noti_kinds;
	private String sender_name;
	private int sender_id;
	
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}
	public int getSender_id() {
		return sender_id;
	}
	public void setSender_id(int sender_id) {
		this.sender_id = sender_id;
	}
	public String getNoti_kinds() {
		return noti_kinds;
	}
	public void setNoti_kinds(String noti_kinds) {
		this.noti_kinds = noti_kinds;
	}
	public int getNoti_id() {
		return noti_id;
	}
	public void setNoti_id(int noti_id) {
		this.noti_id = noti_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public int getNoti_sender_id() {
		return noti_sender_id;
	}
	public void setNoti_sender_id(int noti_sender_id) {
		this.noti_sender_id = noti_sender_id;
	}
	public String getNoti_subject() {
		return noti_subject;
	}
	public void setNoti_subject(String noti_subject) {
		this.noti_subject = noti_subject;
	}
	public String getNoti_url() {
		return noti_url;
	}
	public void setNoti_url(String noti_url) {
		this.noti_url = noti_url;
	}
	public Date getNoti_date() {
		return noti_date;
	}
	public void setNoti_date(Date noti_date) {
		this.noti_date = noti_date;
	}
	public int getNoti_typeid() {
		return noti_typeid;
	}
	public void setNoti_typeid(int noti_typeid) {
		this.noti_typeid = noti_typeid;
	}
	public String getNoti_content() {
		return noti_content;
	}
	public void setNoti_content(String noti_content) {
		this.noti_content = noti_content;
	}
	public int getNoti_confirm() {
		return noti_confirm;
	}
	public void setNoti_confirm(int noti_confirm) {
		this.noti_confirm = noti_confirm;
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
	
	
	
	
}
