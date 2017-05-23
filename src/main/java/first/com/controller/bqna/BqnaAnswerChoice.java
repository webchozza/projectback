package first.com.controller.bqna;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import first.com.dao.BqnaDAO;

@Controller
public class BqnaAnswerChoice {
	
	@Resource
	private BqnaDAO bqnaService;
	
	@RequestMapping("AnswerChoice.do")
	public String answerChoice(@RequestParam(value="member_id", defaultValue="-1") int member_id,
							   @RequestParam("bcomment_id") int bcomment_id,Model model,
							   @RequestParam("board_id") int board_id){
	
	Map map = new HashMap();
	map.put("member_id", member_id);
	map.put("bcomment_id", bcomment_id);
	map.put("board_id", board_id);
	
	/*model.addAttribute("name", map);*/
	
	bqnaService.answerChoice(map);
	return "forward:/bqnadetail.do";
	}


}
