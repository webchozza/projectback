package first.com.controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import first.com.common.Paging;
import first.com.dao.ScrapDAO;
import first.com.model.BoardDTO;
import first.com.model.ScrapDTO;

@Controller
public class Scrap {
	
	@Resource
	private ScrapDAO Scrap;
	
	private int totalCount; // 총 게시물의 수
	private int blockCount = 10; // 한 페이지의 게시물의 수
	private int blockPage = 5; // 한 화면에 보여줄 페이지 수
	private String pagingHtml; // 페이징을 구현한 HTML
	private Paging page; // 페이징 클래스
	private String path = "ScrapList";//if (RequestMapping("/here.do")) => here = path
	
	
	Map<String, Object> map = new HashMap<String, Object>();
			
	@RequestMapping(value="/ScrapList.do")
	public String scrapList(@RequestParam(value="id", required=false, defaultValue="0") int id, 
							@RequestParam(value="n", defaultValue="0") int n,
							@RequestParam(value="search", required=false, defaultValue="") String search,
							@RequestParam(value="currentPage", defaultValue="1") int currentPage,
							Model model){
		
		map.put("member_id", 1000);//테스트끝나면 여기 바꿔줘야한다
		map.put("search", search);
		
		//세션 아이디를 전송받아서 파라미터 값으로 넘겨준다
		List<BoardDTO> list = Scrap.scrapList(map);
		
		totalCount = list.size();
		
		page = new Paging(path, currentPage, totalCount, blockCount, blockPage, search, n);
		pagingHtml = page.getPagingHtml().toString();
		
		int lastCount = totalCount;

		if (page.getEndCount() < totalCount){ lastCount = page.getEndCount() + 1; }
		
		list= list.subList(page.getStartCount(), lastCount);
		
		model.addAttribute("board", list);
		model.addAttribute("page", pagingHtml);
		model.addAttribute("n", n);
		
		return "ScrapList";
		
	}
	
	
	//여유가 되면 ajax로 구현해보자(js파일을 따로 만들고 스크랩버튼을 클릭했을 시 ajax로 이 메소드로 요청을 보낸 후 응답을 받으면서 alert창을 띄워준 후 그 페이지 상태를 유지하도록 구현
	@RequestMapping(value="/ScrapInsert.do")
	public String insertScrap(ScrapDTO scrap, HttpServletResponse response) throws IOException{
		
		Scrap.insertScrap(scrap);
		
		response.setContentType("text/html; charset=UTF-8"); //캐릭터셋 설정(한글 사용)
		PrintWriter out = response.getWriter();
		out.println("<script>alert('스크랩 되었습니다.'); history.go(-1); </script>");
		out.close();
		
		return "ScrapInsert";//스크립트가 동작하지 않는다면 스크랩 목록으로 이동
	}
	
	@RequestMapping(value="/ScrapDelete.do")
	public String deleteScrap(@RequestParam(value="scrap_id") int scrap_id){
		
		Scrap.deleteScrap(scrap_id);
		
		return "ScrapDelete";
	}

}
