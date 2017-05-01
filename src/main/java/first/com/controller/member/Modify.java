package first.com.controller.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.MemberDAO;
import first.com.model.MemberDTO;

@Controller
public class Modify {

	
	@Resource
	MemberDAO memberService;
	
	
	ModelAndView mav = new ModelAndView();
	
	@RequestMapping("checkmodify.do")
	public ModelAndView checkModify(MemberDTO member) {
		
		mav.addObject("member_email", member.getMember_email());
		mav.setViewName("CheckModify");
		
		return mav;
	}
	@RequestMapping("modifyform.do")
	public ModelAndView modifyMemberForm(MemberDTO member) {
		
		MemberDTO result = memberService.checkModify(member);
		if(result==null){
			mav.setViewName("LoginError");
			return mav;
		}
			mav.addObject("member", result);
			mav.setViewName("MemberModifyForm");
			return mav;
	}
	@RequestMapping("modifyMember.do")
	public ModelAndView modifyMember(HttpServletRequest request,MemberDTO member) {
		
		HttpSession session = request.getSession(false);
		
		
		memberService.modifyMember(member);
		mav.setViewName("ModifySuccess");
		if(session!=null){
			memberService.logOut(member);
			//历厘茄 技记 康开 昏力
			session.invalidate();
		}
		return mav;
	}

}
