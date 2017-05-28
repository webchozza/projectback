package first.com.dao;

import java.util.List;
import java.util.Map;

import first.com.model.ScrapDTO;

public interface RecommendDAO {
	
	public void addRecommend(Map<String, Object> map);

	public int recommendCheck(Map<String, Object> map);
	
	public List<Map<String, Object>> recommendList(Map<String, Object> map);
	
}
