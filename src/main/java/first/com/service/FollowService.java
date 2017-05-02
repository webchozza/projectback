package first.com.service;

import java.util.List;

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
	public List<FollowDTO> listFollow(int member_id) {
		List<FollowDTO> list = sqlSessionTemplate.selectList("follow.list", member_id);
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
		return follow;
	}

}
