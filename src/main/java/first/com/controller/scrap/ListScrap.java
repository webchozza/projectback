package first.com.controller.scrap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import first.com.common.AjaxPaging;
import first.com.dao.MemberPageDAO;
import first.com.dao.ScrapDAO;
import first.com.model.BoardDTO;

@Controller
public class ListScrap {
	
	@Resource
	private MemberPageDAO memberpage;
	
	@Resource
	private ScrapDAO Scrap;
	
	private int totalCount; // 총 게시물의 수
	private int blockCount = 10; // 한 페이지의 게시물의 수
	private int blockPage = 5; // 한 화면에 보여줄 페이지 수
	private String pagingHtml; // 페이징을 구현한 HTML
	private AjaxPaging page; // 페이징 클래스
	private String path = "ScrapList";//if (RequestMapping("/here.do")) => here = path
	
	
	Map<String, Object> map = new HashMap<String, Object>();
			
	@RequestMapping(value="/ScrapList.do")
	public String scrapList(@RequestParam(value="member_id", required=false, defaultValue="0") int member_id, 
							@RequestParam(value="n", defaultValue="0") int n,
							@RequestParam(value="search", required=false, defaultValue="") String search,
							@RequestParam(value="currentPage", defaultValue="1") int currentPage,
							@RequestParam(value="ap", required=false) String ap,
							Model model){
		
		map.put("member_id", member_id);
		map.put("search", search);
		
		List<BoardDTO> list = Scrap.scrapList(map);
		Map<String, Object> myCount = memberpage.myCount(map);
		
		totalCount = list.size();
		
		page = new AjaxPaging(path, currentPage, totalCount, blockCount, blockPage, search, n);
		pagingHtml = page.getPagingHtml().toString();
		
		int lastCount = totalCount;

		if (page.getEndCount() < totalCount){ lastCount = page.getEndCount() + 1; }
		
		list= list.subList(page.getStartCount(), lastCount);
		
		model.addAttribute("board", list);
		model.addAttribute("myCount", myCount);
		
		model.addAttribute("page", pagingHtml);
		
		model.addAttribute("n", n);
		
		model.addAttribute("i", currentPage);
		model.addAttribute("path", page.getFullPath());
		model.addAttribute("member_id", member_id);
		
		if(ap != null){
			return "scrap/ScrapList";//at Ajax request
		}
		
		return null;//스크랩 페이지를 따로 만들고 싶다면 여기에 타일즈 설정 잇자
		
	}
}