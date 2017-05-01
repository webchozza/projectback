package first.com.dao;

import java.util.List;
import java.util.Map;

import first.com.model.BoardDTO;

public interface MainDAO {
	
	public String Main();
	
	public List<BoardDTO> AllSearch(String search);

}
