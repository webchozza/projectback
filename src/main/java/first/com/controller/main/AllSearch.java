package first.com.controller.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Resource
	private MainDAO mainSearch;
	
	private int startrow;
	private int endrow;
	private int totalCount; // 총 게시물의 수
	private int blockCount = 10; // 한 페이지의 게시물의 수
	private int blockPage = 5; // 한 화면에 보여줄 페이지 수
	private String pagingHtml; // 페이징을 구현한 HTML
	private AjaxPaging page; // 페이징 클래스
	private String path = "AllSearchList";//if (RequestMapping("/here.do")) => here = path
	private String[] category = { "board_date", "board_like", "board_comment_count", "scrap_count", "board_hit" };
	
	@RequestMapping("/AllSearchList.do")
	public String allSearch(@RequestParam(value="soundsearch", defaultValue="") String search,
							@RequestParam(value="AllSearch", required=false) String AllSearch,
							@RequestParam(value="n", defaultValue="0") int n,
							@RequestParam(value="currentPage", defaultValue="1") int currentPage, 
							@RequestParam(value="ap", required=false) String ap,
							Model model){

		if(AllSearch != null){ search = AllSearch; }

		startrow = ((currentPage-1) * blockCount)+1;
		endrow = (startrow + blockCount)-1;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		map.put("search", search.trim());
		map.put("category", category[n]);

		List<BoardDTO> list = mainSearch.allSearch(map);
		
		//추천 검색어 함 뽑아보자
		for(BoardDTO iter : list){
			System.out.println(iter.getSearchcount());
		}
		//
		
		totalCount = mainSearch.allBordCount(map);
		
		page = new AjaxPaging(path, currentPage, totalCount, blockCount, blockPage, search, n);
		pagingHtml = page.getPagingHtml().toString();
		
		model.addAttribute("allSearchList", list);
		model.addAttribute("page", pagingHtml);
		
		model.addAttribute("n", n);//select문의 selected 속성 부여를 위한 조건 구현에 필요
		
		//ajax를 이용한 검색을 구현하기 위해 넣어 보내준다
		model.addAttribute("i", currentPage);
		model.addAttribute("path", page.getFullPath());
		model.addAttribute("AllSearch", search);
		
		if(ap != null){
			return "main/AllSearchList";//at Ajax request
		}
		
		
		return "AllSearchList";
	}

}
