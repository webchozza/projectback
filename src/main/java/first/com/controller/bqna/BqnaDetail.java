package first.com.controller.bqna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import first.com.dao.BqnaDAO;
import first.com.dao.ScrapDAO;
import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;

@Controller
public class BqnaDetail {

	@Resource
	private BqnaDAO bqnaService;
	
	@Resource
	private ScrapDAO Scrap;

	@RequestMapping(value = "/bqnadetail")
	public ModelAndView bqnaDetail(@RequestParam(value = "currentPage", defaultValue="1") int currentPage,
			@RequestParam("board_id") int board_id,
			@RequestParam(value = "session_id", defaultValue = "-1") int session_id) {

		ModelAndView mav = new ModelAndView();

		BoardDTO bqnaDetail = bqnaService.bqnaDetail(board_id);
		List<BcommentDTO> bcqnaList = bqnaService.bcqnaList(board_id);
		bqnaService.bqnaUpdateHit(board_id);
		
		//답변체크 값 가져오는 로직
		int answerCheck = bqnaService.bqnaAnswerCheck(board_id);
		if(answerCheck > 0){
			mav.addObject("answerCheck", 1);//답변이 채택된 댓글이 있는 게시물이면
		}else{
			mav.addObject("answerCheck", -1);//답변이 채택된 댓글이 업는 게시물이면
		}
		//
		
		//답변채택글 뽑아서 모델객체에 저장
		for(int i=0;i<bcqnaList.size(); i++){
			if(bcqnaList.get(i).getBcomment_id() == bcqnaList.get(i).getAnswer()){
				mav.addObject("answercomment", bcqnaList.get(i));
				break;
			}
		}
		//
		

		mav.addObject("board_tag", bqnaDetail.getBoard_tag());
		mav.addObject("currentPage", currentPage);
		mav.addObject("detail", bqnaDetail);
		mav.addObject("bcqnaList", bcqnaList);
		mav.setViewName("QnaDetail");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("session_id", session_id);
		map.put("board_id", board_id);
		if (session_id == -1) {
			mav.addObject("scrapCheck", "-1");
		} else {
			mav.addObject("scrapCheck", Scrap.scrapCheck(map));
		}

		return mav;
	}

}
