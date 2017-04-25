package first.com.controller.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import first.com.dao.ScrapDAO;
import first.com.model.BoardDTO;
import first.com.model.MemberDTO;
import first.com.model.ScrapDTO;

@Controller
public class Scrap {
	
	@Resource
	private ScrapDAO Scrap;
	
	@RequestMapping(value="/ScrapList.do")
	public void scrapList(Model model){
		
		MemberDTO md = new MemberDTO();
		ScrapDTO scd = new ScrapDTO();
		
		md.setMember_id(1000);
		
		scd = (ScrapDTO)Scrap.scrapList(md);
		System.out.println(scd+"scd´Ù");
		
		model.addAttribute("board", scd);
		
	}
	
	public String deleteScrap(){
		
		return null;
	}

}
