package first.com.dao;

import java.util.List;

import first.com.model.FollowDTO;
import first.com.model.NotiDTO;

public interface AlramDAO {
	
	public List<NotiDTO> notiList(int member_id);
	
	public String deleteAlram();
	
	//댓글 작성시 나를 팔로우한 회원과 해당 게시글의 작성자에게 알림을 보내주는 메소드
	public void insertCommentNoti(int board_id, int session_id, String path);

	//새 글 작성시 나를 팔로우한 회원들에게 알림을 보내주는 메소드(작성한 글이 insert되고 난 후 호출해야 함)
	public void insertNewBoardNoti(int session_id, String path, int bgroup_id);
}
