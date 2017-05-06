package first.com.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.MainDAO;
import first.com.model.BoardDTO;

@Service
@Resource(name="mainSearch")
public class MainService implements MainDAO{
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public String Main() {
		return null;
	}

	@Override
	public List<BoardDTO> AllSearch(Map<String,Object> map) {
		return sqlSessionTemplate.selectList("allSearchList", map);
	}
	
	
	

}
