package first.com.dao;

public interface NotiCountDAO {
	
	//주기적으로 갱신을 할 때마다 새로운 알림이 몇개인지 뽑아오는 메소드(실시간으로 알림생성을 체크해서 메뉴에 띄워줌)
	public int getNotiCount(int member_id);
	
	//새로운 알림이 생길때 카운트를 더해주는 메소드
	public void setNotiCount(int member_id);
	
	//알림목록을 열 때 메뉴에 보여주는 새로온 메시지 카운트를 초기화 시켜주는 메소드
	public void initCount(int member_id);
}
