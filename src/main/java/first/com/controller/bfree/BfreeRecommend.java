package first.com.controller.bfree;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BfreeDAO;

@Controller
public class BfreeRecommend {

	@Resource
	private BfreeDAO bfreeService;

	@RequestMapping("/bfreerecommend")
	public ModelAndView bfreeRecommend(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int currentPage= Integer.parseInt(request.getParameter("currentPage"));
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		bfreeService.bfreeLike(board_id);
		mav.setViewName("redirect:bfreedetail.do?board_id=" + request.getParameter("board_id") + "&currentPage="
				+ request.getParameter("currentPage"));

		return mav;
	}

}
