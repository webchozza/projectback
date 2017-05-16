package first.com.controller.member;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import first.com.dao.RecommendDAO;

@Controller
public class Recommend {
	
	@Resource
	private RecommendDAO recommendService;
	
	//추천 할 시 입력
	public void addRecommend(Map<String, Object> map){
		
		recommendService.addRecommend(map);
		
	}
	

}
