package first.com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RecommendDAO {
	
	public void addRecommend(Map<String, Object> map);

	public int recommendCheck(Map<String, Object> map);
	
	public List<HashMap<String, Object>> recommendList(Map<String, Object> map);
	
	public List<HashMap<String, Object>> recommendSearch(List<String> list);
	
	
}
