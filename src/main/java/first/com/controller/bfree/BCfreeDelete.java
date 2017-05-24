package first.com.controller.bfree;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BfreeDAO;

@Controller
public class BCfreeDelete {
	
	@Resource
	private BfreeDAO bfreeService;
	
	@RequestMapping("/bfreedeletecomment")
	public ModelAndView bCfreeDelete(HttpServletRequest request,@RequestParam("session_id") int session_id) {
		
		ModelAndView mav= new ModelAndView();
		int bcomment_id= Integer.parseInt(request.getParameter("bcomment_id"));
		bfreeService.bCfreeDelete(bcomment_id);
		bfreeService.bfreeUpdateCountco(Integer.parseInt(request.getParameter("board_id")));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", request.getParameter("board_id"));
		map.put("currentPage", request.getParameter("currentPage"));
		map.put("session_id", request.getParameter("session_id"));
		
		mav.addAllObjects(map);
		mav.setViewName("redirect:bfreedetail.do?session_id="+session_id);

		return mav;
	}
}
