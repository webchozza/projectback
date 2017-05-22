package first.com.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import first.com.dao.AdminDAO;

@Controller
public class AdminUpdate {
	
	@Resource
	private AdminDAO admin;
	
	@RequestMapping("/AdminUpdate.do")
	@ResponseBody
	public int adminUpdate(@RequestParam("member_admin") int member_admin,
						   @RequestParam("member_id") int member_id){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("member_admin", member_admin);
		map.put("member_id", member_id);
		
		admin.member_admin(map);
		
		return 0;
	}

}
