package first.com.dao;

import java.util.List;
import java.util.Map;

import first.com.model.BoardDTO;

public interface MemberPageDAO {
	
	public List<BoardDTO> myBoardList(Map<String, Object> map);
	
	public Map<String, Object> myCount(Map<String, Object> map);
	
}
