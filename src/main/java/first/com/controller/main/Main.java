package first.com.controller.main;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import first.com.dao.MainDAO;

@Controller
public class Main {
	
	@Resource
	private MainDAO mainSearch;
	
	@RequestMapping("/main.do")
	public String main(Model model){
		
		Map<String, List<Map<String, Object>>> mainlistmap = mainSearch.main();

		model.addAttribute("main", mainlistmap);
		
		return "Main";
	}

}
