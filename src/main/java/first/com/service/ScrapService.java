package first.com.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import first.com.dao.ScrapDAO;
import first.com.model.BoardDTO;
import first.com.model.ScrapDTO;

@Component
@Resource(name = "Scrap")
public class ScrapService implements ScrapDAO {

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public List<BoardDTO> scrapList(Map<String, Object> map) {
		List<BoardDTO> list = sqlSessionTemplate.selectList("scrap.list", map);// 스크랩 목록 불러오기
		return list;
	}

	@Override
	public int insertScrap(ScrapDTO scrap) {
		System.out.println("???");
		return sqlSessionTemplate.insert("scrap.insert", scrap);
	}

	@Override
	public void deleteScrap(Map map) {
		sqlSessionTemplate.delete("scrap.delete", map);
	}

	@Override
	public int scrapCheck(Map map) {
		return sqlSessionTemplate.selectOne("scrap.Check", map);
	}

}
