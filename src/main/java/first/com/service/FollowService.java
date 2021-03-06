package first.com.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.FollowDAO;
import first.com.model.FollowDTO;

@Service
@Resource(name="followService")
public class FollowService implements FollowDAO {
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<FollowDTO> listFollow(Map<String, Object> map) {
		List<FollowDTO> list = sqlSessionTemplate.selectList("follow.list", map);
		return list;
	}

	@Override
	public void addFollow(FollowDTO follow) {
		sqlSessionTemplate.insert("follow.add", follow);
	}

	@Override
	public void deleteFollow(FollowDTO follow) {
		sqlSessionTemplate.delete("follow.delete", follow);
	}

	@Override
	public FollowDTO followCount(int member_id) {
		FollowDTO follow = (FollowDTO)sqlSessionTemplate.selectOne("follow.followCount", member_id);
		follow.setMember_name((String)sqlSessionTemplate.selectOne("memberpage.name", member_id));
		return follow;
	}
	
	@Override
	public int followCheck(Map<String, Object> map) {
		int follow = sqlSessionTemplate.selectOne("memberpage.followCheck", map);
		return follow;
	}

	@Override
	public int followAllCount(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("follow.followAllCount", map);
	}

}
