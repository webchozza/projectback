package first.com.dao;

import java.util.List;
import java.util.Map;

import first.com.model.BoardDTO;
import first.com.model.ScrapDTO;

public interface ScrapDAO {
	
	public List<BoardDTO> scrapList(Map<String, Object> map);
	
	public void insertScrap(ScrapDTO scrap);
	
	public String deleteScrap(int scrap_id);

}
