package first.com.controller.scrap;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import first.com.dao.ScrapDAO;
import first.com.model.ScrapDTO;

@Controller
public class InsertScrap {
	
	@Resource
	private ScrapDAO Scrap;
	
	@RequestMapping(value="/ScrapInsert.do")
	@ResponseBody
	public int insertScrap(ScrapDTO scrap) throws IOException{
		
		Scrap.insertScrap(scrap);
		
		return 1;
	}

}
