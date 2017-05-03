package first.com.controller.follow;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import first.com.dao.FollowDAO;
import first.com.model.FollowDTO;

@Controller
public class DeleteFollow {
	
	@Resource
	private FollowDAO followService;
	
	//ajax로 처리하자
	@RequestMapping("/DeleteFollow.do")
	@ResponseBody
	public int addFollow(FollowDTO follow){
		
		followService.deleteFollow(follow);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("session_id", follow.getMember_id());//팔로우 하는 사람
		map.put("member_id", follow.getFollow_member_id());//팔로우 당하는 사람

		return followService.followCheck(map);
	}
	

}
