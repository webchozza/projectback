package first.com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import first.com.model.BoardDTO;

public interface RecommendDAO {
	
	public void addRecommend(Map<String, Object> map);

	public int recommendCheck(Map<String, Object> map);
	
	public List<HashMap<String, Object>> recommendList(Map<String, Object> map);
	
	public List<HashMap<String, Object>> recommendSearch(List<String> list);
	
	public List<HashMap<String, Object>> SimilarBoard(Map<String, Object> map);
}
