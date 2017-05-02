package first.com.dao;

import java.util.List;
import java.util.Map;

import first.com.model.BoardDTO;
import first.com.model.FollowDTO;

public interface MemberPageDAO {
	
	public List<BoardDTO> myBoardList(Map<String, Object> map);
	
	public FollowDTO followCheck(Map<String, Object> map);

}
