package first.com.dao;

import java.util.List;
import java.util.Map;

import first.com.model.FollowDTO;

public interface FollowDAO {
	
	public List<FollowDTO> listFollow(int member_id);

	public void addFollow(FollowDTO follow);
	
	public void deleteFollow(FollowDTO follow);
	
	public FollowDTO followCount(int member_id);
	
	public int followCheck(Map<String, Object> map);
	
}
