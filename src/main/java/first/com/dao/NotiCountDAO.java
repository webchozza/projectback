package first.com.dao;

public interface NotiCountDAO {
	
	public int getNotiCount(int member_id);
	
	public void setNotiCount(int member_id);
	
	//알림목록을 열 때 메뉴에 보여주는 새로온 메시지 카운트를 초기화 시켜주는 메소드
	public void initCount(int member_id);
}
