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
		//session member_id∑Œ ¿˙¿Â 
		String member_email = (String)session.getAttribute("member_email");

		
		if(member_email==null){ 
			if(request.getRequestURI().contains("writeform.do")||
			 request.getRequestURI().contains("memberlist.do")||
			 request.getRequestURI().contains("recommand.do")){
				response.sendRedirect("/dokky/loginform.do");
				return false;
			}else{
				return true;
			}
		}else{
			if(member_email.equals("admin@")){
				return true;
			}else if(request.getRequestURI().contains("memberlist.do")){
				return false;
			}
				return true;
				
		}
		
	}
	
}
