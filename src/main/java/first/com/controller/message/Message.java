package first.com.controller.message;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.MessageDAO;
import first.com.model.MessageDTO;

@Controller
public class Message {
	@Resource
	private MessageDAO messageService;
	
	
	
	@RequestMapping("/massagelist.do")
	public ModelAndView listMassage(MessageDTO message,HttpSession session){
		     String email_to = (String)session.getAttribute("member_email");
		     message.setEmail_to(email_to);
		     ModelAndView mav = new ModelAndView();
		     System.out.println(message.getSearch());
		if(message.getSearch()==null){
		List<MessageDTO> result = messageService.getList(message);
		List<MessageDTO> read = messageService.getReadList(message);
		        mav.addObject("messageNotRead", read);
				mav.addObject("messageList", result);
		}else{
			List<MessageDTO> result = messageService.getSearchList(message);
			List<MessageDTO> read = messageService.getReadList(message);
	        mav.addObject("messageNotRead", read);
			mav.addObject("messageList", result);
		}
				mav.setViewName("MessageList");
		return mav;
	}
	
	@RequestMapping("/messagewriteform.do") //글 쓰기  session 영역에 있는 email과  name값은 from으로 바뀌어야 한다.
	public ModelAndView writeMessageForm(MessageDTO message,HttpSession session){   
			String email_to = message.getEmail_from();
			String name_to = message.getName_from();
			
			String email_from = (String)session.getAttribute("member_email");
			String name_from = (String)session.getAttribute("member_name");
		 	
			ModelAndView mav = new ModelAndView();
		 	
			mav.addObject("email_from", email_from);
		 	mav.addObject("name_from", name_from);
		 	mav.addObject("email_to", email_to);
		 	mav.addObject("name_to",name_to);
		 	mav.setViewName("MessageForm");
		return mav;
	}
	
	@RequestMapping("/messagewrite.do")
	public ModelAndView writeMessage(MessageDTO message){
		
		messageService.setWrite(message);
		
		ModelAndView mav = new ModelAndView();
		 mav.setViewName("MessageSuccess");
		return mav;
	}
	
	
	@RequestMapping("/messagedelete.do")
	public String deleteMessage(MessageDTO message){
		messageService.setDelete(message);
		
		return "redirect:/massagelist.do";
	}

	@RequestMapping("/messagecontent.do")
	public ModelAndView contentMessage(MessageDTO message){
		MessageDTO result = messageService.getContent(message);
		   messageService.setRead(message);
		ModelAndView mav = new ModelAndView();
		   mav.addObject("message", result);
		   mav.setViewName("MessageContent");
		return mav;
	}
}
