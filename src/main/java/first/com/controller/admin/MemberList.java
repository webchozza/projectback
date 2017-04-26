package first.com.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import first.com.common.Paging;
import first.com.dao.AdminDAO;
import first.com.model.MemberDTO;

@Controller
public class MemberList {
	
	@Resource
	private AdminDAO admin;
	
	private int totalCount; // 총 게시물의 수
	private int blockCount = 10; // 한 페이지의 게시물의 수
	private int blockPage = 5; // 한 화면에 보여줄 페이지 수
	private String pagingHtml; // 페이징을 구현한 HTML
	private Paging page; // 페이징 클래스
	private String path = "MemberList";//url에서 프로젝트 경로 다음부터 .do의 앞부분까지 ex)/dokky/~~~.do =>> ~~~ = path
	private String[] kind = { "member_email", "member_name" };
	
	
	@RequestMapping("/MemberList.do")
	public String memberList(@RequestParam(value="search", defaultValue="") String search,
							 @RequestParam(value="n", defaultValue="0") int n,
							 @RequestParam(value="currentPage", defaultValue="1") int currentPage, 
							 @RequestParam(value="ch", required=false) String ch,
							 Model model){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("search", search.trim());
		map.put("n", kind[n]);
		if(ch != null){ map.put("ch", ch); }
		
		List<MemberDTO> list = admin.memberList(map);
		
		totalCount = list.size();
		
		page = new Paging(path, currentPage, totalCount, blockCount, blockPage, search, n);
		pagingHtml = page.getPagingHtml().toString();
		
		int lastCount = totalCount;

		if (page.getEndCount() < totalCount){ lastCount = page.getEndCount() + 1; }
		
		list= list.subList(page.getStartCount(), lastCount);

		model.addAttribute("memberlist", list);
		model.addAttribute("page", pagingHtml);
		model.addAttribute("n", n);
		
		
		return "MemberList";
	}
}