package first.com.controller.bqna;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BqnaDAO;

@Controller
public class BqnaDelete {

	@Resource
	private BqnaDAO bqnaService;
	
	@RequestMapping("/bqnadelete")
	public ModelAndView bqnaDelete(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		bqnaService.bqnaDelete(board_id);
		mav.setViewName("redirect:/bqnalist.do");
		
		return mav;
	}

}
