package first.com.controller.member;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	
	@RequestMapping("/checkmodify.do")
	public ModelAndView checkModify(MemberDTO member) {
		 System.out.println(member.getMember_email());
		mav.addObject("member_email", member.getMember_email().toString());
		mav.setViewName("CheckModify");
		
		return mav;
	}
	@RequestMapping("/modifyform.do")
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
	@RequestMapping("/modifyMember.do")
	public ModelAndView modifyMember(HttpServletResponse response,HttpServletRequest request,MemberDTO member)throws IOException  {

		memberService.modifyMember(member);
		mav.setViewName("ModifySuccess");
			memberService.logOut(member);
		
		
		return mav;
	}
	
	@RequestMapping("/deleteMember.do")
	public ModelAndView deleteMember(HttpServletRequest request,MemberDTO member) {
		
		memberService.deleteMember(member);
		mav.setViewName("DeleteSuccess");
		return mav;
	}
}
