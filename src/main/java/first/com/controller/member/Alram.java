package first.com.controller.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import first.com.dao.AlramDAO;
import first.com.dao.NotiCountDAO;
import first.com.model.NotiDTO;

@Controller
public class Alram {

	@Resource
	private AlramDAO noti;
	
	@Resource
	private NotiCountDAO noticount;

	@RequestMapping(value = "/notilist.do")
	@ResponseBody
	public List<NotiDTO> alramList(@RequestParam(value = "session_id", required=false) int member_id, Model model) {

		//Notification테이블에 있는 정보 중 현재 접속 중인 회원의 session_id를 이용하여 일치하는 정보들을 꺼내온다.
		//NotiDTO에 값을 넣어서 전달하기 때문에 json객체의 키는 DTO의 각 property이다.
		List<NotiDTO> list = noti.notiList(member_id);
		
		noticount.initCount(member_id);//알림을 확인할 때 새로운 알림을 알려주는 카운트가 있다면 초기화
		
		return list;
	}
	
	@RequestMapping(value="/notiDelete.do")
	public String notiDelete() {

		return null;
	}
	
	@RequestMapping(value="/notiCount.do")
	@ResponseBody
	public int notiCount(@RequestParam(value="session_id") int member_id){
		
		return noticount.getNotiCount(member_id);
	}

}
