package first.com.controller.bqna;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.common.Paging;
import first.com.common.Paging_tag;
import first.com.dao.BqnaDAO;
import first.com.model.BoardDTO;

@Controller
public class BqnaList {
	
	@Resource
	private BqnaDAO bqnaService;
	
	private int n;
	private String search;
	
	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 10;
	private int blockPage = 5;
	private String pagingHtml;
	private Paging page;
	
	@RequestMapping(value="/bqnalist")
	public ModelAndView bqnaList(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		if(request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty() 
				|| request.getParameter("currentPage").equals("0")){
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
			List<BoardDTO> boardDTO =null;
		
		String search = request.getParameter("search");
		
		// �˻��ҋ�
		if (search != null) {
			n = Integer.parseInt(request.getParameter("n"));
			
	         if (n == 1)
	             boardDTO = bqnaService.bqnaSearch0(search);
	          else if (n == 2)
	             boardDTO = bqnaService.bqnaSearch1(search);
	          else if (n == 3)
	             boardDTO = bqnaService.bqnaSearch2(search);
	          else if (n==0) {
	             boardDTO= bqnaService.bqnaList();
	          }
		
		totalCount = boardDTO.size();
		page = new Paging("bqnalist", currentPage, totalCount, blockCount, blockPage, search, n);
		pagingHtml = page.getPagingHtml().toString();
		
		int lastCount = totalCount;
		
		if (page.getEndCount() < totalCount)
			lastCount = page.getEndCount() + 1;
		boardDTO = boardDTO.subList(page.getStartCount(), lastCount);
		
		mav.addObject("search", search);
		mav.addObject("n", n);
		mav.addObject("totalCount", totalCount);
		mav.addObject("pagingHtml", pagingHtml);
		mav.addObject("currentPage", currentPage);
		mav.addObject("bqnalist", boardDTO);
		mav.setViewName("QnaList");
		
		return mav;
	}
		
		boardDTO = bqnaService.bqnaList();
		
		totalCount = boardDTO.size();
		
		page = new Paging("bqnalist", currentPage, totalCount, blockCount, blockPage, "", 0);
		pagingHtml = page.getPagingHtml().toString();
		
		int lastCount = totalCount;
		
		if (page.getEndCount() < totalCount)
			lastCount = page.getEndCount() + 1;
		
		boardDTO = boardDTO.subList(page.getStartCount(), lastCount);
		
		mav.addObject("search", "");
		mav.addObject("n", 0);
		mav.addObject("totalCount", totalCount);
		mav.addObject("pagingHtml", pagingHtml);
		mav.addObject("currentPage", currentPage);
		mav.addObject("bqnalist", boardDTO);

		mav.setViewName("QnaList");
		return mav;
	}
}
