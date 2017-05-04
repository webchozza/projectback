package first.com.controller.bfree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BfreeDAO;
import first.com.dao.ScrapDAO;
import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;

@Controller
public class BfreeDetail {

	@Resource
	private BfreeDAO bfreeService;
	private int currentPage;
	
	@Resource 
	private ScrapDAO Scrap;

	@RequestMapping("/bfreedetail")
	public ModelAndView bfreeDetail(HttpServletRequest request,@RequestParam("session_id") int session_id) {
		

		ModelAndView mav = new ModelAndView();

		int currentPage= Integer.parseInt(request.getParameter("currentPage"));
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		BoardDTO bfreeDetail = bfreeService.bfreeDetail(board_id);
		List<BcommentDTO> bcfreeList = bfreeService.bcfreeList(board_id);

		bfreeService.bfreeHit(board_id);

		mav.addObject("currentPage", currentPage);
		mav.addObject("bfreeDetail", bfreeDetail);
		mav.addObject("bcfreeList", bcfreeList);
		mav.setViewName("FreeDetail");
		
		//add by eongoo
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("session_id", session_id);
		map.put("board_id", board_id);
		mav.addObject("scrapCheck", Scrap.scrapCheck(map));
		//
		
		return mav;
	}

}
