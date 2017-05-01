package first.com.controller.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import first.com.dao.AlramDAO;
import first.com.model.NotiDTO;

@Controller
public class Alram {

	@Resource
	private AlramDAO noti;

	@RequestMapping(value = "/notilist.do")
	@ResponseBody
	public List<NotiDTO> alramList(@RequestParam(value = "session_id") int member_id, Model model) {

		System.out.println(member_id);
		
		//Notification테이블에 있는 정보 중 현재 접속 중인 회원의 session_id를 이용하여 일치하는 정보들을 꺼내온다.
		//NotiDTO에 값을 넣어서 전달하기 때문에 json객체의 키는 DTO의 각 property이다.
		List<NotiDTO> list = noti.notiList(member_id);
		
		return list;
	}

	public String deleteAlram() {

		return null;
	}

}
