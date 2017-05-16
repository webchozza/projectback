package first.com.controller.message;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.MessageListener;
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
		     String name_to = (String)session.getAttribute("member_name");
		     message.setName_to(name_to);
		     ModelAndView mav = new ModelAndView();
		     List<MessageDTO> total = messageService.getList(message);
				mav.addObject("totalList",total);
		if(message.getSearch()==null){
			if(message.getN()==0){
		List<MessageDTO> result = messageService.getList(message);
		mav.addObject("messageList", result);
			}else if(message.getN()==1){
				List<MessageDTO> result = messageService.getSendList(message);
				mav.addObject("messageList", result);
			}else if(message.getN()==2){
				List<MessageDTO> result = messageService.getReceiveList(message);
				mav.addObject("messageList", result);
			}
		List<MessageDTO> read = messageService.getReadList(message);
		        mav.addObject("messageNotRead", read);
				
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

			String name_to = message.getName_from();
			String name_from = (String)session.getAttribute("member_name");
			ModelAndView mav = new ModelAndView();
			if(message.getMessage_id()!=0){
			MessageDTO result = messageService.getContent(message);
                 mav.addObject("result",result);			
			}
		 	
			
		 	mav.addObject("name_from", name_from);
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
