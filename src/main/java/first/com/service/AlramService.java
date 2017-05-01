package first.com.service;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.AlramDAO;
import first.com.model.NotiDTO;

@Service
@Resource(name="noti")
public class AlramService implements AlramDAO{
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<NotiDTO> notiList(int member_id) {
		List<NotiDTO> list = sqlSessionTemplate.selectList("noti.list", member_id);
		return list;
	}

	@Override
	public String deleteAlram() {
		
		return null;
	}

}
