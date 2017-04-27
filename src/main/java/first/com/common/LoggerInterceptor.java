package first.com.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoggerInterceptor extends HandlerInterceptorAdapter {
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		//session member_id로 저장 
		String member_id = (String)session.getAttribute("member_id");
		System.out.println("인터셉터 :1");
		/*비 로그인 시 writeform, memberlist, recommend 로 이동 불가  따라서, main.do로 이동*/
		if(member_id==null){ 
			System.out.println("인터셉터 :2");
			if(request.getRequestURI().contains("writeform.do")||
			 request.getRequestURI().contains("memberlist.do")||
			 request.getRequestURI().contains("recommand.do")){
				response.sendRedirect("/dokky/loginform.do");
				return false;
			}else{
				System.out.println("인터셉터 :3");
				return true;
			}
		}else{
			if(member_id.equals("admin")){
				System.out.println("인터셉터 :4");
				return true;
			}else if(request.getRequestURI().contains("memberlist.do")){
				System.out.println("인터셉터 :5");
				
				return false;
			}
			System.out.println("인터셉터 :6");
				return true;
				
		}
		
	}
	
}
