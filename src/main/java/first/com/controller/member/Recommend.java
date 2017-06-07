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
	
	//��õ �� �� ��õ���̺� ���� �Է�
	public void addRecommend(Map<String, Object> map){
		recommendService.addRecommend(map);
	}
	
	/*@RequestMapping(value="/RecommendList.do", method=RequestMethod.GET)
	public String Main(){
		return "redirect:main.do";
	}*/
	
	//�α��ν� ������ ������ ����� ������ ��õ �� ��ũ�� ����� �����ش�(���� ȸ���� ��õ �� ��ũ������ ���� �ۿ� ���ؼ�)
	@RequestMapping(value="/RecommendList.do")
	@ResponseBody
	public List<HashMap<String, Object>> RecommendList(@RequestParam("session_id") int member_id){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member_id);
		
		List<HashMap<String, Object>> recommend_list =  recommendService.recommendList(map);
		
		//ajax �ۼ��� �� list�� null�� �ƴ� �� << ���ǹ� �� �ۼ�
		return recommend_list;
	}
	
	
	//�˻� �� �˻���� ���絵�� ���� �۸���� �����ش�.
	@RequestMapping("/RecommendSearch.do")
	@ResponseBody
	public List<HashMap<String, Object>> Search(@RequestParam(value="search", defaultValue="") String search){
		
		List<String> searchtokenlist = new ArrayList<String>();
		
		//true�� �˻�� ���� ����, false�� �˻�� ���� ������
		boolean check = false;
		//���� �˻�� ���� �������� ��������� ������ ���� �����Ѵ�.
		for(int i=0; i<search.length(); i++){
			//�˻�� ������ �ִٸ�
			if(search.charAt(i) == ' '){
				String[] searchsplit = search.split("\\s");
				for(int k=0;k<searchsplit.length;k++){
					searchtokenlist.add(k, searchsplit[k].trim());
				}
				check = true;
				break;
			}
		}
		//�˻�� ������ ���ٸ�
		if(check == false){
			searchtokenlist.add(0, search);
		}
		
		List<HashMap<String, Object>> searchlist = recommendService.recommendSearch(searchtokenlist);
		
		return searchlist;
	}
	
	
	//�󼼺��⿡�� �ش� �۰� ���絵�� ���� �۸���� �����ش�.
	@RequestMapping("/SimilarBoard.do")
	@ResponseBody
	public List<HashMap<String, Object>> SimilarBoard(@RequestParam("board_id") int board_id){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", board_id);
		
		List<HashMap<String, Object>> SimilarList = recommendService.SimilarBoard(map);

		return SimilarList;
	}
}
