package first.com.service;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.NotiCountDAO;

@Service
@Resource(name="noticount")
public class NotiCount implements NotiCountDAO{
	
	HttpSession session;
	
	int count;
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
		
	public int getNotiCount(int noti_count) {//주기적으로 갱신을 할 때마다 새로운 알림이 몇개인지 뽑아오는 메소드(실시간으로 알림생성을 체크해서 메뉴에 띄워줌)
		
		return noti_count;
	}

	public void setNotiCount(int member_id,  int noti_count) {//새로운 알림이 생길때 카운트를 더해주는 메소드

		String id = String.valueOf(member_id);
		System.out.println(id+"새로운 알림이다");
		System.out.println(noti_count);
		int a = noti_count+1;
		session.setAttribute(id,a);
	}
	
	public void initCount(int member_id){//알림목록을 열 때 메뉴에 보여주는 새로온 메시지 카운트를 초기화 시켜주는 메소드
		
		String id = String.valueOf(member_id);
		session.removeAttribute(id);
		
		}

	@Override
	public int login_noti_count(int member_id) {
		return sqlSessionTemplate.selectOne("noti.noti_count",  member_id);
	}

	@Override
	public void logout_noti_count(Map<String, Object> map) {
		sqlSessionTemplate.update("noti.insert_noti_count", map);
	}
}
