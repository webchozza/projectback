package first.com.controller.bfree;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BfreeDAO;

@Controller
public class BCfreeDelete {
	
	@Resource
	private BfreeDAO bfreeService;
	
	@RequestMapping("/bfreedeletecomment")
	public ModelAndView bCfreeDelete(HttpServletRequest request) {
		
		ModelAndView mav= new ModelAndView();
		int bcomment_id= Integer.parseInt(request.getParameter("bcomment_id"));
		bfreeService.bCfreeDelete(bcomment_id);
		bfreeService.bfreeUpdateCountco(Integer.parseInt(request.getParameter("board_id")));
		mav.setViewName("redirect:bfreedetail.do?board_id="+request.getParameter("board_id")+"&currentPage="+request.getParameter("currentPage"));

		return mav;
	}
}
