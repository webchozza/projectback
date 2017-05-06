package first.com.controller.scrap;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.ScrapDAO;

@Controller
public class DeleteScrap {
	
	@Resource
	private ScrapDAO Scrap;
	
	@RequestMapping(value="/ScrapDelete.do")
	public ModelAndView deleteScrap(@RequestParam(value="board_id") int board_id,
									@RequestParam(value="member_id") int member_id,
									@RequestParam(value="n", defaultValue="0") int n,
									@RequestParam(value="search", required=false, defaultValue="") String search,
									@RequestParam(value="currentPage", defaultValue="1") int currentPage,
									@RequestParam(value="ap", required=false) String ap){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("board_id", board_id);
		map.put("member_id", member_id);
		
		Scrap.deleteScrap(map);
		
		
		return new ModelAndView("forward:/ScrapList.do");
	}

}