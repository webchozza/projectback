package first.com.model;

import java.util.List;

public class ScrapDTO {
	
	private int scrap_id;
	private int member_id;
	private int board_id;
	private int bgroup_id;
	
	private MemberDTO member_name;
	//해당 회원이 스트랩한 게시글들의 정보를 담을 객체
	private List<BoardDTO> board;
	
	
	
	public int getScrap_id() {
		return scrap_id;
	}
	public void setScrap_id(int scrap_id) {
		this.scrap_id = scrap_id;
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
	public MemberDTO getMember_name() {
		return member_name;
	}
	public void setMember_name(MemberDTO member_name) {
		this.member_name = member_name;
	}
	public List<BoardDTO> getBoard() {
		return board;
	}
	public void setBoard(List<BoardDTO> board) {
		this.board = board;
	}
	
}
