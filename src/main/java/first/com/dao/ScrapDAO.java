package first.com.dao;

import java.util.List;
import java.util.Map;

import first.com.model.BoardDTO;
import first.com.model.ScrapDTO;

public interface ScrapDAO {
	
	public List<BoardDTO> scrapList(Map<String, Object> map);
	
	public int insertScrap(ScrapDTO scrap);
	
	public void deleteScrap(Map map);
	
	public int scrapCheck(Map map);
	
}
