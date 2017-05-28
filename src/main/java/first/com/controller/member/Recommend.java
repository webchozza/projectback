package first.com.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import first.com.dao.RecommendDAO;

@Controller
public class Recommend {
	
	@Resource
	private RecommendDAO recommendService;
	
	//추천 할 시 추천테이블에 정보 입력
	public void addRecommend(Map<String, Object> map){
		
		recommendService.addRecommend(map);
	}
	
	@RequestMapping("/retest.do")
	public void test(){
		Map<String, Object> map = new HashMap();
		map.put("member_id", 300);
		List<Map<String, Object>> recommendlist =  recommendService.recommendList(map);
		System.out.println(recommendlist);
	}
}
