package first.com.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Maintain {
	
/*	@Resource
	private AdminDAO admin;*/
	
	@RequestMapping("/Maintain.do")
	public void maintain(){//세션 유지를 위한 maintain 메소드(ajax로 주기적으로 요청을 보내준다)
		
		/*admin.outCh();*/
		
	}
}
