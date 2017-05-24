package first.com.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.AlramDAO;
import first.com.dao.NotiCountDAO;
import first.com.model.BoardDTO;
import first.com.model.FollowDTO;
import first.com.model.NotiDTO;

@Service
@Resource(name="noti")
public class AlramService implements AlramDAO{
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Resource
	private NotiCountDAO noticount;

	@Override
	public List<NotiDTO> notiList(int member_id) {
		List<NotiDTO> list = sqlSessionTemplate.selectList("noti.list", member_id);
		return list;
	}

	@Override
	public int deleteAlram(int noti_id) {
		return sqlSessionTemplate.delete("noti.delete", noti_id);
	}
	
	//댓글 작성시 나를 팔로우한 회원과 해당 게시글의 작성자에게 알림을 보내주는 메소드
	public void insertCommentNoti(int board_id, int session_id, String path){//board_id = 댓글 작성한 게시글의 board_id
																			 //session_id = 접속중인 회원의 session_id
																		     //board_url = 상세보기의 url을 ex) /dokky/bfreedetail.do => "bfreedetail"
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("session_id", session_id);//댓글을 작성한 사람의 id
		
		BoardDTO board = (BoardDTO)sqlSessionTemplate.selectOne("noti.select_board", board_id);
		List<FollowDTO> followComment = sqlSessionTemplate.selectList("noti.follower_member_id", map);
		
		//알림창에서 해당 게시글로 바로 이동할 수 있도록 url 생성
		path = "/dokky"+path+".do?board_id="+board_id;

		map.put("board_title", board.getBoard_title());//게시글 제목
		map.put("board_url", path);//게시글 주소
		map.put("bgroup_id", board.getBgroup_id());//게시판 종류
		map.put("board_id", board_id);//게시글의 pk
		
		if(followComment != null){//만약 작성한 사람을 팔로우한 회원이 있다면
			for(int i=0; i < followComment.size(); i++){
				map.put("member_id", followComment.get(i).getMember_id());//댓글작성자를 팔로우한 회원의 아이디를 map객체에 세팅
				map.put("noti_kinds", "follow_comment");//팔로우한 회원이 댓글을 작성했을 때
				noticount.setNotiCount(followComment.get(i).getMember_id());//새 알림을 맵 객체에 저장한다.
				sqlSessionTemplate.insert("noti.insert", map);//작성자를 팔로우한 회원의 알림테이블에 알림정보를 넣어준다.
			}
		}
	
		map.put("member_id", board.getMember_id());//댓글이 작성된 게시글의 작성자 id(만약 같은 이름의 key를 가진 map객체가 있다면 덮어씌워짐)
		map.put("noti_kinds", "comment"); //본인 게시글에 댓글이 달렸을 때
		noticount.setNotiCount(board.getMember_id());
		sqlSessionTemplate.insert("noti.insert", map);
		
	}
	
	//새 글 작성시 나를 팔로우한 회원들에게 알림을 보내주는 메소드(작성한 글이 insert되고 난 후 호출해야 함)
	public void insertNewBoardNoti(int session_id, String path, int bgroup_id){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("session_id", session_id);//새 글을 작성한 사람의 id
		map.put("bgroup_id", bgroup_id);//게시판 종류

		BoardDTO board = (BoardDTO)sqlSessionTemplate.selectOne("noti.New_Board_select_board", map);

		List<FollowDTO> followNewBoard = sqlSessionTemplate.selectList("noti.follower_member_id", session_id);
		
		path = "/dokky"+path+".do?board_id="+board.getBoard_id();
		
		map.put("board_title", board.getBoard_title());//게시글 제목
		map.put("board_url", path);//게시글 주소
		map.put("board_id", board.getBoard_id());//게시글의 pk
		
		if(followNewBoard != null){//만약 작성한 사람을 팔로우한 회원이 있다면
			for(int i=0; i < followNewBoard.size(); i++){
				map.put("member_id", followNewBoard.get(i).getMember_id());
				map.put("noti_kinds", "follow_NewBoard");
				noticount.setNotiCount(followNewBoard.get(i).getMember_id());
				sqlSessionTemplate.insert("noti.insert", map);//작성자를 팔로우한 회원의 알림테이블에 알림정보를 넣어준다.
			}
		}
	}
}
		
