package first.com.controller.member;

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

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.MemberDAO;
import first.com.model.MemberDTO;

@Controller
public class Find {
	
	@Resource
	private MemberDAO memberService;
	
	ModelAndView mav = new ModelAndView();
	
	public String checkFind(){
		
		return null;
	}
	//비밀번호 찾기 form으로 이동
	@RequestMapping(value = "/findpwform.do", method = RequestMethod.POST)
	public String findForm(){
		System.out.println("패스워드찾기 폼");
		return"FindPWForm";
	}
	

	//비밀번호 찾기 및 난수 생성으로 임시비밀번호 보내기
	@RequestMapping(value = "/findpwform.do", method = RequestMethod.GET)
	public String findPw(HttpServletRequest request,MemberDTO member){
		
		MemberDTO result = memberService.findPw(member);
		 
		if(result==null){ // 입력한 이메일이 존재하지 않을 경우 에러 메세지 창
		  
			return"LoginError";
		}
		//난수 생성
		
		String pw = RandomStringUtils.random(8,"abcdefchijklmnopqrstuvwxyz0123456789");
		//새로 만든 임시비밀번호 저장
	

		member.setMember_pw(pw); 
		//업데이트 
	

		memberService.updatePw(member);
	

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
			try {
				message.setSender(new InternetAddress("oyg900420@gmail.com"));
				message.setSubject(" 회원님의 임시비밀번호 발급 메일입니다.");
			
		        
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(member.getMember_email()));
				System.out.println("이메일 전송 9");
				Multipart mp = new MimeMultipart();
				MimeBodyPart mbp1 = new MimeBodyPart();
				mbp1.setText("회원님의 임시비밀번호는 "+pw+" 입니다.\n\n비밀번호를 즉시 변경하시기 바랍니다.\n\n"
						+ "http://localhost:8080/dokky/loginform.do (DOKKY 로그인화면으로 이동)");
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
		
			} catch (AddressException e) {
				
				e.printStackTrace();
			} catch (MessagingException e) {
				
				e.printStackTrace();
			}
			return "FindEmail";	
		
}
	
}
