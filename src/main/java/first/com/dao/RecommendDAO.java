package first.com.dao;

import java.util.Map;

import first.com.model.ScrapDTO;

public interface RecommendDAO {
	
	public void addRecommend(Map<String, Object> map);

	public int recommendCheck(Map<String, Object> map);
}
