package first.com.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import first.com.dao.AdminDAO;

@Controller
public class AdminDelete {
	
	@Resource
	private AdminDAO admin;
	
	@RequestMapping("/MemberDelete.do")
	public String adminDelete(@RequestParam(value="id") int member_id) {

		admin.memberDelete(member_id);
		
		return "MemberList";
	}

}
