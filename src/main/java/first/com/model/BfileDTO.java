package first.com.model;

import java.util.Date;

public class BfileDTO {
	
	private int bfile_id;
	private int member_id;
	private int board_id;
	private int bgroup_id;
	private Date bfile_date;
	private int bfile_size;
	private int bfile_count;
	
	public int getBfile_id() {
		return bfile_id;
	}
	public void setBfile_id(int bfile_id) {
		this.bfile_id = bfile_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
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
	public Date getBfile_date() {
		return bfile_date;
	}
	public void setBfile_date(Date bfile_date) {
		this.bfile_date = bfile_date;
	}
	public int getBfile_size() {
		return bfile_size;
	}
	public void setBfile_size(int bfile_size) {
		this.bfile_size = bfile_size;
	}
	public int getBfile_count() {
		return bfile_count;
	}
	public void setBfile_count(int bfile_count) {
		this.bfile_count = bfile_count;
	}
	
	

}
