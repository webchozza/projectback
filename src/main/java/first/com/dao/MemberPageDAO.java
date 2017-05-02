package first.com.dao;

import java.util.List;
import java.util.Map;

import first.com.model.BoardDTO;

public interface MemberPageDAO {
	
	public List<BoardDTO> myBoardList(Map map);

}
