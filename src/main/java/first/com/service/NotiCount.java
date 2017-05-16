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
	
	//맵 객체에서 확인하지 않은 알림이 있으면 가져온다.(ajax를 이용해서 주기적으로)
	public int getNotiCount(int member_id) {
		
		int count = 0;
		
		if(NotiCount != null && NotiCount.containsKey(member_id)){
			for(Map.Entry<Integer, Integer> entry : this.NotiCount.entrySet()){
				if(entry.getKey() == member_id){
					System.out.println(NotiCount.get(member_id));
					count  = entry.getValue();
					System.out.println(count+"카운트");
					break;
				}
			}
		}
			return count;
	}
	
	//작성글에 댓글이 달릴 시, 팔로우한 회원이 글을 작성하거나 댓글을 작성할 때 새 알림을 맵 객체에 저장한다.
	public void setNotiCount(int member_id) {
	
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
		
		NotiCount = notiCount;
	}
	
	//알림 목록을 펼치면 맵 객체에 저장된 해당 회원의 새 알림 개수를 초기화 한다.
	public void initCount(int member_id){
		if(NotiCount != null && NotiCount.containsKey(member_id)){
			for(Map.Entry<Integer, Integer> entry : this.NotiCount.entrySet()){
				if(entry.getKey() == member_id){
					NotiCount.remove(member_id);
				}
			}
		}
	}
}

