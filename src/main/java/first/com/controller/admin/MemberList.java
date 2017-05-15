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
import first.com.common.Paging_tag;
import first.com.dao.AdminDAO;
import first.com.model.MemberDTO;

@Controller
public class MemberList {
	
	@Resource
	private AdminDAO admin;
	
	private int startrow;
	private int endrow;
	private int totalCount; // �� �Խù��� ��
	private int blockCount = 10; // �� �������� �Խù��� ��
	private int blockPage = 5; // �� ȭ�鿡 ������ ������ ��
	private String pagingHtml; // ����¡�� ������ HTML
	private AjaxPaging page; // ����¡ Ŭ����
	private String path = "MemberList";//if (RequestMapping("/here.do")) => here = path
	private String[] kind = { "member_email", "member_name" };
	
	
	@RequestMapping("/MemberList.do")//ch= ������ ���� Į�� ��
	public String memberList(@RequestParam(value="search", defaultValue="") String search,
							 @RequestParam(value="n", defaultValue="0") int n,
							 @RequestParam(value="currentPage", defaultValue="1") int currentPage, 
							 @RequestParam(value="ch", required=false) String ch,
							 @RequestParam(value="ap", required=false) String ap,
							 Model model){
		
		Map<String, Object> map = new HashMap<String, Object>();

		startrow = ((currentPage-1) * 10) + 1;
		endrow = (startrow + blockCount) -1;
		
		map.put("startrow", startrow);
		map.put("endrow", endrow);
		map.put("search", search.trim());
		map.put("n", kind[n]);
		if(ch != null && !ch.equals("")){ map.put("ch", ch);
		model.addAttribute("ch", ch);
		}

		List<MemberDTO> list = admin.memberList(map);
		
		totalCount = admin.memberCount(map);
		
		page = new AjaxPaging(path, currentPage, totalCount, blockCount, blockPage, search, n);
		pagingHtml = page.getPagingHtml().toString();
		
		model.addAttribute("memberlist", list);
		model.addAttribute("page", pagingHtml);
		
		model.addAttribute("n", n);//select���� selected �Ӽ� �ο��� ���� ���� ������ �ʿ�
		
		//ajax�� �̿��� �˻��� �����ϱ� ���� �־� �����ش�
		model.addAttribute("i", currentPage);
		model.addAttribute("path", page.getFullPath());
		model.addAttribute("search", search);
		
		if(ap != null){
			return "admin/MemberListPage";//at Ajax request
		}
		
		
		return "MemberList";
	}
}