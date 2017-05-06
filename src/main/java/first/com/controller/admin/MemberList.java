package first.com.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import first.com.common.AjaxPaging;
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
	private AjaxPaging page; // 페이징 클래스
	private String path = "MemberList";//if (RequestMapping("/here.do")) => here = path
	private String[] kind = { "member_email", "member_name" };
	
	
	@RequestMapping("/MemberList.do")//ch= 접소자 학인 칼럼 값
	public String memberList(@RequestParam(value="search", defaultValue="") String search,
							 @RequestParam(value="n", defaultValue="0") int n,
							 @RequestParam(value="currentPage", defaultValue="1") int currentPage, 
							 @RequestParam(value="ch", required=false) String ch,
							 @RequestParam(value="ap", required=false) String ap,
							 Model model){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("search", search.trim());
		map.put("n", kind[n]);
		if(ch != null && !ch.equals("")){ map.put("ch", ch);
		model.addAttribute("ch", ch);
		}

		List<MemberDTO> list = admin.memberList(map);
		
		totalCount = list.size();
		
		page = new AjaxPaging(path, currentPage, totalCount, blockCount, blockPage, search, n);
		pagingHtml = page.getPagingHtml().toString();
		
		int lastCount = totalCount;

		if (page.getEndCount() < totalCount){ lastCount = page.getEndCount() + 1; }
		
		list= list.subList(page.getStartCount(), lastCount);

		model.addAttribute("memberlist", list);
		model.addAttribute("page", pagingHtml);
		
		model.addAttribute("n", n);//select문의 selected 속성 부여를 위한 조건 구현에 필요
		
		//ajax를 이용한 검색을 구현하기 위해 넣어 보내준다
		model.addAttribute("i", currentPage);
		model.addAttribute("path", page.getFullPath());
		model.addAttribute("search", search);
		
		if(ap != null){
			return "admin/MemberListPage";//at Ajax request
		}
		
		
		return "MemberList";
	}
}