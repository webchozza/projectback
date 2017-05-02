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
public class Login {
	
	
	@Resource
	private MemberDAO memberService;
	
	ModelAndView mav = new ModelAndView();
	// LoginForm으로 이동 
	@RequestMapping("/loginform.do") 
	public String loginForm(){
		
		return "LoginForm";
	}
	
	//로그인
	@RequestMapping("/login.do") 
	public ModelAndView login(HttpServletRequest request,MemberDTO member){
		//DB에서 data를 꺼내와 MemberDTO에 값 저장
		MemberDTO result = memberService.login(member);
		
		//값이 존재한다면 session 영역에 저장한다.
		if(result!=null){
				
			HttpSession session = request.getSession();
			
			memberService.loginUpdate(member);
			
			session.setAttribute("member_email", result.getMember_email());
			session.setAttribute("member_name", result.getMember_name());
			session.setAttribute("member_id", result.getMember_id());
			
			mav.setViewName("Main");
			
			return mav;
		}	//에러 발생할 경우 이동
			mav.setViewName("LoginError");
			return mav;
	}
	//Logout
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpServletRequest request,MemberDTO member){
		
		HttpSession session = request.getSession(false);
		//왜 false가 뜰까용???
		if(session!=null){
			//저장한 세션 영역 삭제
			session.invalidate();
		}
		//새로운 객체 생성하여 기존에 객체에 저장한 값 delete
		mav.addObject("member",new MemberDTO());
		// MainForm으로 이동
		mav.setViewName("Main");
		
		return mav;
	}
	
	

}









