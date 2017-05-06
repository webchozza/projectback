package first.com.controller.memberpage;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.MemberPageDAO;

@Controller
public class DeleteMyBoard {

	@Resource
	private MemberPageDAO memberpage;

	@RequestMapping("/deleteMyBoard.do")
	public ModelAndView deleteMyBoard(@RequestParam("board_id") int board_id,
									  @RequestParam(value="member_id") int member_id, 
									  @RequestParam(value="session_id", defaultValue="0") int session_id, 
									  @RequestParam(value="n", defaultValue="0") int n,
									  @RequestParam(value="search", required=false, defaultValue="") String search,
									  @RequestParam(value="currentPage", defaultValue="1") int currentPage,
									  @RequestParam(value="ap", required=false) String ap){
		
		//자신이 작성한 게시글 삭제
		if(member_id != 0){ memberpage.deleteMyBoard(board_id); }
		
		return new ModelAndView("forward:/MemberPage.do");
	}
}
