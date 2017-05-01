package first.com.controller.follow;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FollowMain {
	
	@RequestMapping("/test.do")
	public String followMain(){
		return "FollowMain";
	}

}
