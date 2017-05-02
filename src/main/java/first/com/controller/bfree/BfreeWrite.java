package first.com.controller.bfree;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import first.com.common.Paging;
import first.com.dao.BfreeDAO;
import first.com.model.BoardDTO;

@Controller
public class BfreeWrite {
	
	@Resource
	private BfreeDAO bfreeService;
	
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
	public ModelAndView bfreeWrite(@ModelAttribute("BoardDTO")BoardDTO boardDTO, BindingResult result, HttpServletRequest request, HttpSession session) {
		
		ModelAndView mav= new ModelAndView();
		
		bfreeService.bfreeWrite(boardDTO);
		
		mav.addObject("boardDTO", boardDTO);
		
		mav.setViewName("redirect:bfreelist.do");
		
		
		return mav;
	}

}
