package first.com.controller.bfree;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import first.com.common.Paging;
import first.com.dao.BfreeDAO;
import first.com.model.BoardDTO;

@Controller
public class BfreeList {
	
	//인코딩다시했어여
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

	@RequestMapping(value = "/bfreelist")
	public ModelAndView bfreeList(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();

		if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
				|| request.getParameter("currentPage").equals("0")) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		List<BoardDTO> boardDTO = null;

		String search = request.getParameter("search");
		
		// 검색할때
		if (search != null) {
			System.out.println(n);
			System.out.println(search);
			n = Integer.parseInt(request.getParameter("n"));
		
			if (n == 1)
				boardDTO = bfreeService.bfreeSearch0(search);
			else if (n == 2)
				boardDTO = bfreeService.bfreeSearch1(search);
			else if (n == 3)
				boardDTO = bfreeService.bfreeSearch2(search);
			else if (n==0) {
				boardDTO= bfreeService.bfreeList();
			}

			totalCount = boardDTO.size();
			page = new Paging("bfreelist", currentPage, totalCount, blockCount, blockPage, search, n);
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
			mav.addObject("bfreelist", boardDTO);
			mav.setViewName("FreeList");
			return mav;
		}

		boardDTO = bfreeService.bfreeList();

		totalCount = boardDTO.size();

		page = new Paging("bfreelist", currentPage, totalCount, blockCount, blockPage, "", 0);
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
		mav.addObject("bfreelist", boardDTO);

		mav.setViewName("FreeList");
		return mav;
	}

}
