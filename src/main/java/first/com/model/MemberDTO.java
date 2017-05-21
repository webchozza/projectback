package first.com.model;

import java.util.Date;

public class MemberDTO {
	
	private int member_id;
	private String member_email;
	private String member_pw;
	private String member_name;
	private String member_del;
	private int member_ch;//현재 접속자 확인 칼럼
	private String member_photo;
	private Date member_date;
	private int member_admin;
	
	
	public int getMember_admin() {
		return member_admin;
	}
	public void setMember_admin(int member_admin) {
		this.member_admin = member_admin;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_del() {
		return member_del;
	}
	public void setMember_del(String member_del) {
		this.member_del = member_del;
	}
	public int getMember_ch() {
		return member_ch;
	}
	public void setMember_ch(int member_ch) {
		this.member_ch = member_ch;
	}
	public String getMember_photo() {
		return member_photo;
	}
	public void setMember_photo(String member_photo) {
		this.member_photo = member_photo;
	}
	public Date getMember_date() {
		return member_date;
	}
	public void setMember_date(Date member_date) {
		this.member_date = member_date;
	}
	

}
