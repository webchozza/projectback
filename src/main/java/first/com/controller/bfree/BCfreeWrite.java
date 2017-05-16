package first.com.controller.bfree;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.AlramDAO;
import first.com.dao.BfreeDAO;
import first.com.model.BcommentDTO;

@Controller
public class BCfreeWrite {

	@Resource
	private BfreeDAO bfreeService;
	
	//notiI
	@Resource 
	private AlramDAO noti;
	/////

	@RequestMapping(value = "/bfreewritecomment")
	public ModelAndView bCfreeWrite(@ModelAttribute("BcommentDTO") BcommentDTO bcommentDTO, BindingResult result,
			HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		String content= bcommentDTO.getBcomment_content().replaceAll("\r\n", "<br />");
		bcommentDTO.setBcomment_content(content);
		
		bfreeService.bCfreeWrite(bcommentDTO);
		bfreeService.bfreeUpdateCountco(bcommentDTO.getBoard_id());
		mav.addObject("bcommentDTO", bcommentDTO);
		
		noti.insertCommentNoti(bcommentDTO.getBoard_id(), bcommentDTO.getMember_id(), "/bfreedetail");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", bcommentDTO.getBoard_id());
		map.put("currentPage", request.getParameter("currentPage"));
		map.put("session_id", bcommentDTO.getMember_id());

		mav.addAllObjects(map);
		mav.setViewName("redirect:bfreedetail.do");

		
		return mav;
	}

}
