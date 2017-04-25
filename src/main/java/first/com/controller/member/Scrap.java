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

import first.com.dao.ScrapDAO;
import first.com.model.BoardDTO;
import first.com.model.ScrapDTO;

@Controller
public class Scrap {
	
	@Resource
	private ScrapDAO Scrap;
	
	Map<String, Object> map = new HashMap<String, Object>();
			
	@RequestMapping(value="/ScrapList.do")
	public String scrapList(@RequestParam(value="id", required=false, defaultValue="0") int id, 
							@RequestParam(value="search", required=false, defaultValue="") String search,
							Model model){
		
		map.put("member_id", 1000);
		map.put("search", search);
		
		//세션 아이디를 전송받아서 파라미터 값으로 넘겨준다
		List<BoardDTO> list = Scrap.scrapList(map);
		
		model.addAttribute("board", list);
		
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
