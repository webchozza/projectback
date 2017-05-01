package first.com.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.model.BoardDTO;

@Service
@Resource(name="insertNoti")
public class InsertNoti {
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void insertNoti(int board_id, int session_id, String board_url){//board_id = The ID of the post where the comment was written
																		   //session_id = Session ID at the time of comment creation
																		   //board_url = The URL of the post where the comment was written
																		  
		
		BoardDTO board = (BoardDTO)sqlSessionTemplate.selectList("noti.memberid", board_id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("member_id", board.getMember_id());
		map.put("session_id", session_id);
		map.put("board_title", board.getBoard_title());
		map.put("board_url", board_url);
		map.put("bgroup_id", board.getBgroup_id());
		map.put("board_id", board_id);
		
		sqlSessionTemplate.insert("noti.insert", map);
	}

}
