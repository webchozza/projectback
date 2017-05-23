package first.com.controller.bfree;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BfreeDAO;
import first.com.model.BoardDTO;

@Controller
public class BfreeModify {
	
	@Resource
	private BfreeDAO bfreeService;

	@RequestMapping(value="/bfreemodifyform")
	public ModelAndView bfreeModifyForm(int board_id, HttpServletRequest request) {
		ModelAndView mav= new ModelAndView();

		BoardDTO boardDTO= bfreeService.bfreeDetail(board_id);
		
		String content= boardDTO.getBoard_content().replaceAll("<br />", "\r\n");
		String tag= boardDTO.getBoard_tag().replaceAll(" ", "");
		
		tag = tag.replaceAll("Community,", "");
		
		boardDTO.setBoard_content(content);
		boardDTO.setBoard_tag(tag);
		
		mav.addObject("currentPage", request.getParameter("currentPage"));
		mav.addObject("boardDTO", boardDTO);
		mav.setViewName("FreeModifyForm");
		
		return mav;
	}
	
	@RequestMapping(value="/bfreemodify")
	public ModelAndView bfreeModify(BoardDTO boardDTO, HttpServletRequest request) {
		ModelAndView mav= new ModelAndView();

		String content= boardDTO.getBoard_content().replaceAll("\r\n", "<br />");
		String tag = boardDTO.getBoard_tag().replaceAll(" ", "");
		while (tag.contains(",,")) {
			tag = tag.replaceAll(",,", ",");
		}
		if (tag.length() <2) {
			tag = "";
		} else {
			if (tag.charAt(tag.length() - 1) == ',') {
				tag=tag.substring(0, tag.length()-1);
			}
			if (tag.charAt(0) == ',') {
				tag=tag.substring(1, tag.length());
			}
		}

		boardDTO.setBoard_content(content);
		if(tag==""||tag.equals("Community"))
			boardDTO.setBoard_tag("Community");
		else
			boardDTO.setBoard_tag("Community," + tag);
		
		
		bfreeService.bfreeModify(boardDTO);
		mav.setViewName("redirect:bfreedetail.do?board_id="+boardDTO.getBoard_id()+"&currentPage="+request.getParameter("currentPage"));
		return mav;
	}


}
