package first.com.controller.bqna;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BqnaDAO;
import first.com.dao.RecommendDAO;

@Controller
public class BqnaRecommend {
	
	@Resource
	private BqnaDAO bqnaService;
	@Resource 
	private RecommendDAO recommendSerivce;
	
	@RequestMapping
	public ModelAndView bqnaRecommend(HttpServletRequest request, @RequestParam(value="session_id") int session_id) {
		ModelAndView mav = new ModelAndView();
		
		int currentPage= Integer.parseInt(request.getParameter("currentPage"));
		int board_id = Integer.parseInt(request.getParameter("board_id"));
	
		bqnaService.bqnaLike(board_id);
		
		//by eongoo, recommend
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("member_id", session_id);
				map.put("board_id", board_id);
				recommendSerivce.addRecommend(map);
				//
		
		mav.setViewName("redirect:bqnadetail.do?board_id=" + request.getParameter("board_id") + "&currentPage="
				+ request.getParameter("currentPage") + "&session_id="+ session_id);

		return mav;
	}

}