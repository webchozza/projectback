package first.com.Test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ee {
	
	@RequestMapping("/ee.do")
	public String a(@RequestParam(value="soundsearch", required=false) String a, Model model){
		if(a != null){
			model.addAttribute("soundsearch", a);
		}
		return "Main";
	}
	
	@RequestMapping("/ml.do")
	public String b(){
		System.out.println("log");
		return "MemberList";
	}
	
	@RequestMapping("/soundtest.do")
	public String c(){
		return "Sound";
	}
	
	@RequestMapping("/ScrapList.do")
	public String d(){
		return "ScrapList";
	}


}
