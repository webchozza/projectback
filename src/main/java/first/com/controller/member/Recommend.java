package first.com.controller.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import first.com.dao.RecommendDAO;

@Controller
public class Recommend {
	
	@Resource
	private RecommendDAO recommendService;
	
	//추천 할 시 추천테이블에 정보 입력
	public void addRecommend(Map<String, Object> map){
		recommendService.addRecommend(map);
	}
	
	/*@RequestMapping(value="/RecommendList.do", method=RequestMethod.GET)
	public String Main(){
		return "redirect:main.do";
	}*/
	
	//로그인시 유저와 취향이 비슷한 유저의 추천 및 스크랩 목록을 보여준다(접속 회원이 추천 및 스크랩하지 않은 글에 한해서)
	@RequestMapping(value="/RecommendList.do")
	@ResponseBody
	public List<HashMap<String, Object>> RecommendList(@RequestParam("session_id") int member_id){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		
		List<HashMap<String, Object>> recommend_list =  recommendService.recommendList(map);
		
		//ajax 작성할 때 list가 null이 아닐 때 << 조건문 꼭 작성
		return recommend_list;
	}
	
	
	//검색 시 검색어와 유사도가 높은 글목록을 보여준다.
	@RequestMapping("/RecommendSearch.do")
	@ResponseBody
	public List<HashMap<String, Object>> Search(@RequestParam(value="search", defaultValue="") String search){
		
		List<String> searchtokenlist = new ArrayList<String>();
		
		//true면 검색어에 공백 포함, false면 검색어에 공백 미포함
		boolean check = false;
		//만약 검색어가 공백 기준으로 여러개라면 나눠서 따로 저장한다.
		for(int i=0; i<search.length(); i++){
			//검색어에 공백이 있다면
			if(search.charAt(i) == ' '){
				String[] searchsplit = search.split("\\s");
				for(int k=0;k<searchsplit.length;k++){
					searchtokenlist.add(k, searchsplit[k].trim());
				}
				check = true;
				break;
			}
		}
		//검색어에 공백이 없다면
		if(check == false){
			searchtokenlist.add(0, search);
		}
		
		List<HashMap<String, Object>> searchlist = recommendService.recommendSearch(searchtokenlist);
		
		return searchlist;
	}
	
	
	//상세보기에서 해당 글과 유사도가 높은 글목록을 보여준다.
	@RequestMapping("/SimilarBoard.do")
	@ResponseBody
	public List<HashMap<String, Object>> SimilarBoard(@RequestParam("board_id") int board_id){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", board_id);
		
		List<HashMap<String, Object>> SimilarList = recommendService.SimilarBoard(map);

		return SimilarList;
	}
}
