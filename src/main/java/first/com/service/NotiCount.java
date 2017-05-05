package first.com.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import first.com.dao.NotiCountDAO;

@Service
@Resource(name="noticount")
public class NotiCount implements NotiCountDAO{
		
	private Map<Integer, Integer> NotiCount;

	public int getNotiCount(int member_id) {//주기적으로 갱신을 할 때마다 새로운 알림이 몇개인지 뽑아오는 메소드(실시간으로 알림생성을 체크해서 메뉴에 띄워줌)
		int count = 0;
		if(NotiCount != null && NotiCount.containsKey(member_id)){
			for(Map.Entry<Integer, Integer> entry : this.NotiCount.entrySet()){
				if(entry.getKey() == member_id){
					count  = entry.getValue();
					break;
				}
			}
		}
			System.out.println(count+"get메소드");
			return count;
	}

	public void setNotiCount(int member_id) {//새로운 알림이 생길때 카운트를 더해주는 메소드
		int count = 1;
		
		if(NotiCount != null && NotiCount.containsKey(member_id)){
			for(Map.Entry<Integer, Integer> entry : this.NotiCount.entrySet()){
				if(entry.getKey() == member_id){
					count = entry.getValue() + 1;
					break;
				}
			}
		}
		
		Map<Integer, Integer> notiCount = new HashMap<Integer, Integer>();
		notiCount.put(member_id, count);
		System.out.println(count+"set메소드");
		
		NotiCount = notiCount;
	}
	
	public void initCount(int member_id){//알림목록을 열 때 메뉴에 보여주는 새로온 메시지 카운트를 초기화 시켜주는 메소드
		if(NotiCount != null && NotiCount.containsKey(member_id)){
			for(Map.Entry<Integer, Integer> entry : this.NotiCount.entrySet()){
				if(entry.getKey() == member_id){
					NotiCount.remove(member_id);
					System.out.println(NotiCount+"초기화");
				}
			}
		}
	}
}
