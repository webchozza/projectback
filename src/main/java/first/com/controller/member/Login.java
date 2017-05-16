package first.com.controller.member;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;

import first.com.dao.MemberDAO;
import first.com.model.MemberDTO;
import first.com.oauth.bo.NaverLoginBO;

@Controller
public class Login {

	private static final int String = 0;

	@Resource
	private MemberDAO memberService;

	private NaverLoginBO naverLoginBO;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	ModelAndView mav = new ModelAndView();

	// LoginForm으로 이동
	@RequestMapping("/loginform.do")
	public ModelAndView loginForm(HttpSession session) {
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

		return new ModelAndView("LoginForm", "url", naverAuthUrl);
	}

	// 로그인
	@RequestMapping("/login.do")
	public ModelAndView login(HttpServletRequest request, MemberDTO member) {
		// DB에서 data를 꺼내와 MemberDTO에 값 저장
		MemberDTO result = memberService.login(member);

		// 값이 존재한다면 session 영역에 저장한다.
		if (result != null) {

			HttpSession session = request.getSession();

			memberService.loginUpdate(member);

			session.setAttribute("member_email", result.getMember_email());
			session.setAttribute("member_name", result.getMember_name());
			session.setAttribute("member_id", result.getMember_id());

			mav.setViewName("Main");

			return mav;
		} // 에러 발생할 경우 이동
		mav.setViewName("LoginError");
		return mav;
	}

	// Logout
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpServletResponse response, HttpServletRequest request, MemberDTO member)
			throws IOException {
		HttpSession session = request.getSession(false);

		session.invalidate();

		memberService.logOut(member);
		// 저장한 세션 영역 삭제

		// 새로운 객체 생성하여 기존에 객체에 저장한 값 delete
		mav.addObject("member", new MemberDTO());
		// MainForm으로 이동
		mav.setViewName("Main");

		return mav;
	}

	@RequestMapping("/callback.do")

	public ModelAndView callback(@RequestParam String code, @RequestParam String state, HttpSession session,
			HttpServletRequest request) throws IOException, ParseException {
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
		String apiResult = naverLoginBO.getUserProfile(oauthToken);

		
		JSONParser parser = new JSONParser();

		JSONObject jsonObjAll = (JSONObject) parser.parse(apiResult);
					System.out.println("jsonObjAll: "+jsonObjAll.toJSONString());
		String result = jsonObjAll.get("response").toString();
				
		JSONObject jsonObj = (JSONObject) parser.parse(result);
					System.out.println("jsonObj: "+jsonObj.toJSONString());
		String member_name = jsonObj.get("nickname").toString();
					
		String member_email = jsonObj.get("email").toString();

		ModelAndView mav = new ModelAndView();
		MemberDTO member = memberService.naverLogin(member_email);
		if(member!=null){
			
			session.setAttribute("member_id", member.getMember_id());
			session.setAttribute("member_name", member.getMember_name());
			session.setAttribute("member_email", member.getMember_email());
			mav.setViewName("Main");
			return mav; 
		}
		mav.addObject("member_email", member_email);
		mav.addObject("member_name", member_name);
		mav.setViewName("JoinForm");
		return mav;

	}

}
