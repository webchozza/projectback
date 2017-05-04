package first.com.controller.bfree;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.com.common.Paging;
import first.com.dao.AlramDAO;
import first.com.dao.BfreeDAO;
import first.com.model.BoardDTO;

@Controller
public class BfreeWrite {
	
	@Resource
	private BfreeDAO bfreeService;
	
	//알림을 위해 DI
	@Resource 
	private AlramDAO noti;
	/////
	
	private int n;
	private String search;
	
	private int currentPage = 1;	 
	private int totalCount; 		 
	private int blockCount = 10;	 
	private int blockPage = 5; 	 
	private String pagingHtml;  
	private Paging page;
	

	@RequestMapping(value="/bfreewriteform")
	public ModelAndView bfreeWriteForm(HttpServletRequest request) {
		ModelAndView mav= new ModelAndView();
		mav.addObject("BoardDTO", new BoardDTO());
		mav.setViewName("FreeWriteForm");
		return mav;
	}

	@RequestMapping(value="/bfreewrite")
	public ModelAndView bfreeWrite(@ModelAttribute("BoardDTO")BoardDTO boardDTO, 
								   BindingResult result, 
								   HttpServletRequest request, 
								   HttpSession session) {
		
		ModelAndView mav= new ModelAndView();
		
		bfreeService.bfreeWrite(boardDTO);
		
		mav.addObject("boardDTO", boardDTO);
		
		noti.insertNewBoardNoti(boardDTO.getMember_id(), "/bfreedetail", 2);
		
		mav.setViewName("redirect:bfreelist.do");
		
		return mav;
	}

}
