package first.com.controller.scrap;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.ScrapDAO;

@Controller
public class DeleteScrap {
	
	@Resource
	private ScrapDAO Scrap;
	
	@RequestMapping(value="/ScrapDelete.do")
	public ModelAndView deleteScrap(@RequestParam(value="board_id") int board_id,
							  @RequestParam(value="session_id") int session_id){
		
		Map map = new HashMap();
		
		map.put("board_id", board_id);
		map.put("session_id", session_id);
		
		Scrap.deleteScrap(map);
		
		
		return new ModelAndView("redirect:/ScrapList.do", "ap", "AjaxDeleteScrap");
	}

}