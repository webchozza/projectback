package first.com.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.AdminDAO;
import first.com.model.MemberDTO;

@Service
@Resource(name="admin")
public class AdminService implements AdminDAO{

	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public List<MemberDTO> memberList(Map<String, Object> map) {
		List<MemberDTO> list = sqlSessionTemplate.selectList("admin.memberList", map);
		return list;
	}
	
	@Override
	public MemberDTO memberSelect(Map<String, Object> model) {
		MemberDTO member = sqlSessionTemplate.selectOne("admin.selectOne", model);
		return member;
	}

	@Override
	public void memberModify(MemberDTO member) {
		sqlSessionTemplate.update("admin.memberModify", member);
	}

	@Override
	public void memberDelete(MemberDTO member) {
		sqlSessionTemplate.update("admin.memberDelete", member);
	}
	
	
	
/*	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void updateCh() {//이 메소드는 세션 리스너에서 사용한다.
		sqlSessionTemplate.update("admin.updateCh");
	}*/
	
	

}
