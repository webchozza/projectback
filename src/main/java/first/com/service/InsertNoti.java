package first.com.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import first.com.model.BoardDTO;

/*@Controller*///test
@Service
@Resource(name="insertNoti")
public class InsertNoti {
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	Map<String, Object> map = new HashMap<String, Object>();
	
	/*@RequestMapping("/testAlramList.do")*/
	public void insertNoti(int board_id, int session_id, String path, String kinds){//board_id = The ID of the post where the comment was written
																		      //session_id = Session ID at the time of comment creation
																	          //board_url = The URL of the post where the comment was written
		
		BoardDTO board = (BoardDTO)sqlSessionTemplate.selectOne("noti.select_board", board_id);

		path = "/dokky"+path+".do?"+board_id;
		
		System.out.println(path);
		map.put("member_id", board.getMember_id());//댓글이 작성된 게시글의 작성자 id
		map.put("session_id", session_id);//댓글을 작성한 사람의 id
		map.put("board_title", board.getBoard_title());//게시글 제목
		map.put("board_url", path);//게시글 주소
		map.put("bgroup_id", board.getBgroup_id());//게시판 종류
		map.put("board_id", board_id);//게시글의 pk
		
		if(kinds.equals("comment")){ map.put("noti_kinds", "comment"); }//본인 게시글에 댓글이 달렸을 때
		if(kinds.equals("follow_newBoard")){ map.put("noti_kinds", "follow_newBoard"); }//팔로우한 사람이 새 글을 썼을 때
		if(kinds.equals("follow_comment")){ map.put("noti_kinds", "follow_comment"); }//팔로우한 사람이 댓글을 썼을 때
		
		sqlSessionTemplate.insert("noti.insert", map);
	}
	
}
