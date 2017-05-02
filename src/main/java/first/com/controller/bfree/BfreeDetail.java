package first.com.controller.bfree;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import first.com.common.Paging;
import first.com.dao.BfreeDAO;
import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;

@Controller
public class BfreeDetail {

	@Resource
	private BfreeDAO bfreeService;
	private int currentPage;


	@RequestMapping("/bfreedetail")
	public ModelAndView bfreeDetail(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		int currentPage= Integer.parseInt(request.getParameter("currentPage"));
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		BoardDTO bfreeDetail = bfreeService.bfreeDetail(board_id);
		List<BcommentDTO> bcfreeList = bfreeService.bcfreeList(board_id);

		bfreeService.bfreeHit(board_id);
		System.out.println(currentPage);

		mav.addObject("currentPage", currentPage);
		mav.addObject("bfreeDetail", bfreeDetail);
		mav.addObject("bcfreeList", bcfreeList);
		mav.setViewName("FreeDetail");
		return mav;
	}

}
