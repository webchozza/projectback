package first.com.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import first.com.dao.AdminDAO;
import first.com.model.MemberDTO;

@Controller
public class MemberList {
	
	@Resource
	private AdminDAO admin;
	
	@RequestMapping("/MemberList.do")
	public String memberList(Model model){
		
		List<MemberDTO> list = admin.memberList();
		
		model.addAttribute("memberlist", list);
		
		return "MemberList";
	}

}
