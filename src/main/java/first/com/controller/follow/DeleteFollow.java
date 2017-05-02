package first.com.controller.follow;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import first.com.dao.FollowDAO;
import first.com.model.FollowDTO;

@Controller
public class DeleteFollow {
	
	@Resource
	private FollowDAO followService;
	
	//ajax로 처리하자
	@RequestMapping("/DeleteFollow.do")
	public String addFollow(FollowDTO follow){
		
		followService.deleteFollow(follow);

		return "memberpage/MemberPage";
	}
	

}
