package first.com.controller.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
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
import first.com.dao.NotiCountDAO;
import first.com.model.MemberDTO;
import first.com.oauth.bo.NaverLoginBO;

@Controller
public class Login {

	@Resource
	private MemberDAO memberService;
	
	@Resource 
	NotiCountDAO noticount;

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
			
			//eongoo//로그인할 때 새로운 알림 개수를 꺼내서 세션에 저장
			session.setAttribute(String.valueOf(session.getAttribute("member_id")), noticount.login_noti_count((Integer)(session.getAttribute("member_id"))));
			mav.setViewName("Main");

			return mav;
		} // 에러 발생할 경우 이동
		mav.setViewName("LoginError");
		return mav;
	}

	
	// Logout
	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpServletResponse response,HttpServletRequest request, MemberDTO member)throws IOException{
		HttpSession session = request.getSession(false);
			
		//새로운 객체 생성하여 기존에 객체에 저장한 값 delete
		mav.addObject("member",new MemberDTO());
		
		//eongoo//로그아웃할 때 새로운 알림의 개수를 DB에 저장
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_id", member.getMember_id());
		map.put("noti_count", session.getAttribute(String.valueOf(member.getMember_id())));
		noticount.logout_noti_count(map);
		*/
		System.out.println("로그아웃");
		//저장한 세션 영역 삭제
		session.invalidate();
		memberService.logOut(member);
		// MainForm으로 이동
		mav.setViewName("Main");

		return mav;
	}

	@RequestMapping("/callback.do")

	public ModelAndView callback(@RequestParam String code, @RequestParam String state, HttpSession session,HttpServletRequest request)
			throws IOException {
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
		String apiResult = naverLoginBO.getUserProfile(oauthToken);
						
		JSONObject json = new JSONObject();
		JSONParser parser = new JSONParser();
		try {
			JSONObject jsonObjAll = (JSONObject)parser.parse(apiResult);
			String result = jsonObjAll.get("response").toString();
			JSONObject jsonObj = (JSONObject)parser.parse(result);
				String member_name	= jsonObj.get("nickname").toString();
				String member_email = jsonObj.get("email").toString();
				String member_id = jsonObj.get("id").toString();
				
				session = request.getSession();
				session.setAttribute("member_email", member_email);
				session.setAttribute("member_name", member_name);
				session.setAttribute("member_id", member_id);
				
				
				
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Main");
		
		return mav;
	}

}
