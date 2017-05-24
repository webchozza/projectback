package first.com.controller.bqna;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BqnaDAO;
import first.com.model.BoardDTO;

@Controller
public class BqnaModify {

	@Resource
	private BqnaDAO bqnaService;
	
	@RequestMapping(value="/bqnamodifyform")
	public ModelAndView bqnaModifyForm(int board_id, HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		BoardDTO boardDTO = bqnaService.bqnaDetail(board_id);
		
		String board_content = boardDTO.getBoard_content().replaceAll("<br />", "\r\n");
		boardDTO.setBoard_content(board_content);
		
		mav.addObject("currentPage", request.getParameter("currentPage"));
		mav.addObject("boardDTO", boardDTO);
		mav.setViewName("QnaModifyForm");
		
		return mav;
	}

	@RequestMapping(value="/bqnamodify")
	public ModelAndView bqnaModify(BoardDTO boardDTO, HttpServletRequest request,@RequestParam("session_id") int session_id) {
		ModelAndView mav = new ModelAndView();

		String content = boardDTO.getBoard_content().replaceAll("\r\n", "<br />");
		String tag = boardDTO.getBoard_tag().replaceAll(" ", "");
		boardDTO.setBoard_tag(tag);
		boardDTO.setBoard_content(content);
		
		bqnaService.bqnaModify(boardDTO);
		mav.setViewName("redirect:bqnadetail.do?board_id="+boardDTO.getBoard_id()+"&currentPage="+request.getParameter("currentPage")+"&session_id="+session_id);
		return mav;
	}

}
