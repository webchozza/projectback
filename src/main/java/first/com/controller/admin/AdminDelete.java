package first.com.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.AdminDAO;
import first.com.model.MemberDTO;

@Controller
public class AdminDelete {
	
	@Resource
	private AdminDAO admin;
	
	@RequestMapping("/MemberDelete.do")
	public ModelAndView adminDelete(MemberDTO member,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		admin.memberDelete(member);

		mv.setViewName("forward:/MemberList.do");
		
		return mv;
	}

}
