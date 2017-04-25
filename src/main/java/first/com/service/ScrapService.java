package first.com.service;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import first.com.dao.ScrapDAO;
import first.com.model.MemberDTO;
import first.com.model.ScrapDTO;

@Component
@Resource(name="Scrap")
public class ScrapService implements ScrapDAO{
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	public ScrapDTO scrapList(MemberDTO md) {
		ScrapDTO scd = (ScrapDTO)sqlSessionTemplate.selectOne("scrap.name", md);//스크랩 목록 불러오기
		return scd;
	}

	public String deleteScrap() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
