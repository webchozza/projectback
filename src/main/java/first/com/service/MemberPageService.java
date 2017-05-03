package first.com.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import first.com.dao.MemberPageDAO;
import first.com.model.BoardDTO;
import first.com.model.FollowDTO;

@Component
@Resource(name="memberpage")
public class MemberPageService implements MemberPageDAO {
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<BoardDTO> myBoardList(Map<String, Object> map) {
		List<BoardDTO> list = sqlSessionTemplate.selectList("memberpage.myBoardList", map);
		return list;
	}


}
