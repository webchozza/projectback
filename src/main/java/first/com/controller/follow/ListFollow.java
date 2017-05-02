package first.com.controller.follow;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import first.com.dao.FollowDAO;
import first.com.model.FollowDTO;

@Controller
public class ListFollow {
	
	@Resource
	private FollowDAO followService;
	
	//ajax로 처리하자
	@RequestMapping("/ListFollow.do")
	public String addFollow(@RequestParam(value="member_id", defaultValue="0") int member_id,
							Model model){
		
		List<FollowDTO> list = followService.listFollow(member_id);
		
		model.addAttribute("list", list);

		return "memberpage/MemberPage";
	}
	

}
