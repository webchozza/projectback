package first.com.controller.bqna;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BqnaDAO;
import first.com.model.BoardDTO;

@Controller
public class BqnaDetail {

	@Resource
	private BqnaDAO bqnaService;
	
	@RequestMapping(value= "/bqnadetail")
	public ModelAndView bqnaDetail(HttpServletRequest request, int board_id) {
		ModelAndView mav = new ModelAndView();
		
		int currentPage= Integer.parseInt(request.getParameter("currentPage"));
	    int id = Integer.parseInt(request.getParameter("board_id"));
	    
	    System.out.println(id);
		BoardDTO boardDTO= bqnaService.bqnaDetail(id);
		bqnaService.bqnaUpdateHit(id);
		
		mav.addObject("currentPage", currentPage);
		mav.addObject("detail", boardDTO);
		mav.setViewName("QnaDetail");
		return mav;
	}

}
