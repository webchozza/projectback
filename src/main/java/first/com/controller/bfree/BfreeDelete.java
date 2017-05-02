package first.com.controller.bfree;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BfreeDAO;

@Controller
public class BfreeDelete {

	@Resource
	private BfreeDAO bfreeService;
	
	@RequestMapping("/bfreedelete")
	public ModelAndView bfreeDelete(HttpServletRequest request) {
		
		ModelAndView mav= new ModelAndView();
		int board_id= Integer.parseInt(request.getParameter("board_id"));
		bfreeService.bfreeDelete(board_id);
		mav.setViewName("redirect:/bfreelist.do");

		return mav;
	}

}
