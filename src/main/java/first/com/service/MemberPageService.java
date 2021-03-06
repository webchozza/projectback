package first.com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import first.com.dao.MemberPageDAO;
import first.com.model.BoardDTO;

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

	@Override
	public Map<String, Object> myCount(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("memberpage.myCount", map);
	}

	@Override
	public void deleteMyBoard(int board_id) {
		sqlSessionTemplate.delete("memberpage.deleteMyBoard", board_id);
	}

	@Override
	public int myBoardCount(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("memberpage.myBoardCount", map);
	}

}
