package first.com.controller.main;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import first.com.common.AjaxPaging;
import first.com.dao.MainDAO;
import first.com.model.BoardDTO;

@Controller
public class AllSearch {
	
	private int totalCount; // 총 게시물의 수
	private int blockCount = 10; // 한 페이지의 게시물의 수
	private int blockPage = 5; // 한 화면에 보여줄 페이지 수
	private String pagingHtml; // 페이징을 구현한 HTML
	private AjaxPaging page; // 페이징 클래스
	private String path = "AllSearchList";//if (RequestMapping("/here.do")) => here = path
	
	@Resource
	private MainDAO mainSearch;
	
	@RequestMapping("/AllSearchList.do")
	public String allSearch(@RequestParam(value="soundsearch", defaultValue="") String search,
							@RequestParam(value="AllSearch", required=false) String AllSearch,
							@RequestParam(value="n", defaultValue="0") int n,
							@RequestParam(value="currentPage", defaultValue="1") int currentPage, 
							@RequestParam(value="ap", required=false) String ap,
							Model model){
		
		if(AllSearch != null){ search = AllSearch; }

		List<BoardDTO> list = mainSearch.AllSearch(search);
		
		totalCount = list.size();
		
		page = new AjaxPaging(path, currentPage, totalCount, blockCount, blockPage, search, n);
		pagingHtml = page.getPagingHtml().toString();
		
		int lastCount = totalCount;

		if (page.getEndCount() < totalCount){ lastCount = page.getEndCount() + 1; }
		
		list= list.subList(page.getStartCount(), lastCount);

		model.addAttribute("allSearchList", list);
		model.addAttribute("page", pagingHtml);
		
		model.addAttribute("n", n);//select문의 selected 속성 부여를 위한 조건 구현에 필요
		
		//ajax를 이용한 검색을 구현하기 위해 넣어 보내준다
		model.addAttribute("i", currentPage);
		model.addAttribute("path", page.getFullPath());
		
		if(ap != null){
			return "main/AllSearchList";//at Ajax request
		}
		
		
		return "AllSearchList";
	}

}
