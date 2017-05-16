package first.com.controller.member;

import java.util.Map;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.MemberDAO;
import first.com.model.MemberDTO;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

@Controller
public class Join {
	
	@Resource
	private MemberDAO memberService;
	
	@RequestMapping("/joinform.do")
	public String joinForm(){
		
		return "JoinForm";
	}
	
	@RequestMapping("/checkname.do")
	public void checkName(MemberDTO member,HttpServletResponse response)
	throws Exception{
		
		String memberNameCheck = "";
		if(memberService.checkName(member)){
			
			memberNameCheck = "true";
		}else{
			memberNameCheck = "false";
		}
		try{
			response.getWriter().print(memberNameCheck);
		}catch(Exception e){
		}
	}
	
	@RequestMapping("/checkemail.do")
	public void checkEmail(MemberDTO member,HttpServletResponse response)
	throws Exception{
		
		String memberNameCheck = "";
		if(memberService.checkEmail(member)){
			
			memberNameCheck = "true";
		}else{
			memberNameCheck = "false";
		}
		try{
			response.getWriter().print(memberNameCheck);
		}catch(Exception e){
		}
	}
	@RequestMapping(value = "/sendemail.do", method = RequestMethod.POST)
	public String sendEmail(HttpServletRequest request,MemberDTO member){
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.quitwait", "false");

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("oyg900420@gmail.com", "oyg5478!@");
			}
		};
		Session session = Session.getDefaultInstance(props, auth);

		MimeMessage message = new MimeMessage(session);
		try{
			message.setSender(new InternetAddress("oyg900420@gmail.com"));
			message.setSubject("회원가입 이메일 인증 메일입니다.");

			message.setRecipient(Message.RecipientType.TO, new InternetAddress(member.getMember_email()));
			
			String confirmUrl = "<a href='http://localhost:8080/dokky/join.do?member_email=" + member.getMember_email() + "&member_name=" + member.getMember_name()
					+ "&member_pw=" + member.getMember_pw()+"'>가입</a>를 누르시면 회원가입이 완료됩니다.";

			Multipart mp = new MimeMultipart();
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setContent(confirmUrl, "text/html;charset=UTF-8");
			mp.addBodyPart(mbp1);

			MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
			mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
			mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
			mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
			mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
			mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
			CommandMap.setDefaultCommandMap(mc);

			message.setContent(mp);

			Transport.send(message);
		}catch (AddressException e) {
			
			e.printStackTrace();
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		return "JoinEmail";
	}		
		
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public ModelAndView join(HttpServletRequest request,MemberDTO member){
			ModelAndView mav = new ModelAndView();
		memberService.join(member);
			mav.setViewName("Main");
			return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/validateRecaptcha.do", method = RequestMethod.POST)
	public String validateRecaptcha(@RequestParam Map<String, String> paramMap) {
	     
	    String check = "Y";
	     
	    ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
	    reCaptcha.setPrivateKey("6Ld2OCEUAAAAAJcUgRMDAbj208WOZCDYzJHkKBi8");//Secret key
	 
	    String host = paramMap.get("host");
	    String challenge = paramMap.get("challenge");
	    String res = paramMap.get("response");
	     
	    ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(host, challenge, res);
	 
	    if (reCaptchaResponse.isValid()) {
	    
	        check = "Y";
	    } else {
	     
	        check = "N";
	    }
	     
	    return check;
	 
	}
	
	@RequestMapping("/permit1.do")
	public String permit1(){
		return "permit1";
	}
 
	@RequestMapping("/permit2.do")
	public String permit2(){
		return "permit2";
	}

}
