package first.com.controller.bqna;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BqnaDAO;

@Controller
public class BqnaRecommend {
	
	@Resource
	private BqnaDAO bqnaService;
	
	@RequestMapping
	public ModelAndView bqnaRecommend(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int currentPage= Integer.parseInt(request.getParameter("currentPage"));
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		bqnaService.bqnaLike(board_id);
		mav.setViewName("redirect:bqnadetail.do?board_id=" + request.getParameter("board_id") + "&currentPage="
				+ request.getParameter("currentPage"));

		return mav;
	}

}
