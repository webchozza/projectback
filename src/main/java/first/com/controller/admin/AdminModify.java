package first.com.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import first.com.dao.AdminDAO;
import first.com.model.MemberDTO;

@Controller
public class AdminModify {
	
	@Resource
	private AdminDAO admin;
	
	@RequestMapping("/MemberModify.do")
	public String adminModify(MemberDTO member) {
		
		admin.memberModify(member);

		return "MemberList";
	}

}
