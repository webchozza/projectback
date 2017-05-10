package first.com.controller.tag;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.common.Paging_tag;
import first.com.dao.TagDAO;
import first.com.model.BoardDTO;
import first.com.model.TagDTO;

@Controller
public class ListTag {
	
	@Resource
	private TagDAO tagService;
	
	private String tag;
	
	private int currentPage = 1;
	private int totalCount;
	private int blockCount = 10;
	private int blockPage = 5;
	private String pagingHtml;
	private Paging_tag page;
	private String sort;
	
	private TagDTO tagDTO= new TagDTO(); 
	
	@RequestMapping(value = "/taglist")
	public ModelAndView tagList(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<BoardDTO> boardDTO = null;

		if (request.getParameter("currentPage") == null || request.getParameter("currentPage").trim().isEmpty()
				|| request.getParameter("currentPage").equals("0")) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		if (request.getParameter("tag") == null || request.getParameter("tag").trim().isEmpty()) {
			tag = "";
		} else {
			tag = request.getParameter("tag");
		}

		if (request.getParameter("sort") == null || request.getParameter("sort").trim().isEmpty()) {
			sort = "";
		} else {
			sort = request.getParameter("sort");
		}

		tagDTO.setSort(sort);
		tagDTO.setTag(tag);
		boardDTO = tagService.tagList(tagDTO);
		
		totalCount = boardDTO.size();
		page = new Paging_tag("taglist", currentPage, totalCount, blockCount, blockPage, tag, sort);
		pagingHtml = page.getPagingHtml().toString();
		int lastCount = totalCount;
		if (page.getEndCount() < totalCount)
			lastCount = page.getEndCount() + 1;
		
		boardDTO = boardDTO.subList(page.getStartCount(), lastCount);
		
		mav.addObject("totalCount", totalCount);
		mav.addObject("pagingHtml", pagingHtml);
		mav.addObject("currentPage", currentPage);
		mav.addObject("sort", sort);
		mav.addObject("tag", tag);
		mav.addObject("taglist", boardDTO);
		mav.setViewName("TagList");
		return mav;

	}

}
