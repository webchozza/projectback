package first.com.controller.follow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import first.com.common.AjaxPaging;
import first.com.dao.FollowDAO;
import first.com.dao.MemberPageDAO;
import first.com.model.BoardDTO;
import first.com.model.FollowDTO;

@Controller
public class MemberPage {
	
	@Resource
	private MemberPageDAO memberpage;
	
	@Resource
	private FollowDAO followService;
	
	private int totalCount; // �� �Խù��� ��
	private int blockCount = 10; // �� �������� �Խù��� ��
	private int blockPage = 5; // �� ȭ�鿡 ������ ������ ��
	private String pagingHtml; // ����¡�� ������ HTML
	private AjaxPaging page; // ����¡ Ŭ����
	private String path = "MemberPage";//if (RequestMapping("/here.do")) => here = path
	
	//ȸ������ ȭ�� �Խù� ���
	@RequestMapping("/MemberPage.do")
	public String followMain(@RequestParam(value="member_id") int member_id, 
							 @RequestParam(value="n", defaultValue="0") int n,
							 @RequestParam(value="search", required=false, defaultValue="") String search,
							 @RequestParam(value="currentPage", defaultValue="1") int currentPage,
							 @RequestParam(value="ap", required=false) String ap,
							 Model model){

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("member_id", member_id);//�׽�Ʈ������ ���� �ٲ�����Ѵ�
		map.put("search", search);
		
		List<BoardDTO> list = memberpage.myBoardList(map);
		FollowDTO follow = followService.followCount(member_id);

		totalCount = list.size();
		
		page = new AjaxPaging(path, currentPage, totalCount, blockCount, blockPage, search, n);
		pagingHtml = page.getPagingHtml().toString();
		
		int lastCount = totalCount;

		if (page.getEndCount() < totalCount){ lastCount = page.getEndCount() + 1; }
		
		list= list.subList(page.getStartCount(), lastCount);
		
		model.addAttribute("list", list);
		model.addAttribute("followCount", follow);
		model.addAttribute("page", pagingHtml);
		
		model.addAttribute("n", n);
		
		model.addAttribute("i", currentPage);
		model.addAttribute("path", page.getFullPath());
		model.addAttribute("member_id", member_id);
		
		if(ap != null){
			return "memberpage/MemberPage";//at Ajax request
		}
		
		return "MemberPage";
	}

}