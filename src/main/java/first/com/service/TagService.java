package first.com.service;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.TagDAO;
import first.com.model.BoardDTO;
import first.com.model.TagDTO;

@Service
public class TagService implements TagDAO{
	
	@Resource
	private SqlSessionTemplate SqlSessionTemplate;
	
	@Override
	public List<BoardDTO> tagList(TagDTO tagDTO) {//태그로검색해서 정렬
		return SqlSessionTemplate.selectList("main.allSearchTag", tagDTO);
	}
}
