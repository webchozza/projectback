package first.com.dao;

import java.util.List;
import java.util.Map;

import first.com.model.BoardDTO;

public interface MainDAO {
	
	public Map<String, List<Map<String, Object>>> main();
	
	public List<BoardDTO> allSearch(Map<String, Object> map);
	
	public int allBordCount(Map<String, Object> map);

}
