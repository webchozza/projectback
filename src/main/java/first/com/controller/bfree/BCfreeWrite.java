package first.com.controller.bfree;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BfreeDAO;
import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;

@Controller
public class BCfreeWrite {

	@Resource
	private BfreeDAO bfreeService;

	@RequestMapping(value = "/bfreewritecomment")
	public ModelAndView bCfreeWrite(@ModelAttribute("BcommentDTO") BcommentDTO bcommentDTO, BindingResult result,
			HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		bfreeService.bCfreeWrite(bcommentDTO);
		bfreeService.bfreeUpdateCountco(bcommentDTO.getBoard_id());
		mav.addObject("bcommentDTO", bcommentDTO);

		mav.setViewName("redirect:bfreedetail.do?board_id="+bcommentDTO.getBoard_id()+"&currentPage="+request.getParameter("currentPage"));

		return mav;
	}

}
