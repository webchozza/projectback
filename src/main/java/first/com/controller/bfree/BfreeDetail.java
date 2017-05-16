package first.com.controller.bfree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BfreeDAO;
import first.com.dao.RecommendDAO;
import first.com.dao.ScrapDAO;
import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;

@Controller
public class BfreeDetail {

	@Resource
	private BfreeDAO bfreeService;
	
	@Resource 
	private ScrapDAO Scrap;
	@Resource 
	private RecommendDAO recommendSerivce;

	
	@RequestMapping("/bfreedetail")
	public ModelAndView bfreeDetail(@RequestParam(value="currentPage", defaultValue="1") int currentPage,
			@RequestParam(value="board_id") int board_id,
			@RequestParam(value="session_id", defaultValue="-1") int session_id) {

		ModelAndView mav = new ModelAndView();
		
		BoardDTO bfreeDetail = bfreeService.bfreeDetail(board_id);		
		List<BcommentDTO> bcfreeList = bfreeService.bcfreeList(board_id);
		bfreeService.bfreeHit(board_id);
		
		mav.addObject("board_tag", bfreeDetail.getBoard_tag());
		mav.addObject("currentPage", currentPage);
		mav.addObject("bfreeDetail", bfreeDetail);
		mav.addObject("bcfreeList", bcfreeList);
		mav.setViewName("FreeDetail");
		
		//add by eongoo
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("session_id", session_id);
		map.put("board_id", board_id);
		if(session_id == -1){ 
			mav.addObject("scrapCheck", "-1");
			mav.addObject("recommendCheck", "-1");
		} else {
			mav.addObject("scrapCheck", Scrap.scrapCheck(map));
			mav.addObject("recommendCheck", recommendSerivce.recommendCheck(map));
		}
		
		/////
		
		return mav;
	}

}
