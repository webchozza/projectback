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

	BoardDTO board = new BoardDTO();

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public List<BoardDTO> scrapList(Map<String, Object> map) {
		List<BoardDTO> list = sqlSessionTemplate.selectList("scrap.list", map);// 스크랩 목록 불러오기
		return list;
	}

	@Override
	public void insertScrap(ScrapDTO scrap) {
		sqlSessionTemplate.insert("scrap.insert", scrap);
	}

	@Override
	public String deleteScrap(int scrap_id) {
		sqlSessionTemplate.delete("scrap.delete", scrap_id);
		return null;
	}

}
