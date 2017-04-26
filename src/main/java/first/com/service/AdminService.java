package first.com.service;

import java.util.List;

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
	public List<MemberDTO> memberList() {
		List<MemberDTO> list = sqlSessionTemplate.selectList("admin.memberList");
		return list;
	}

	@Override
	public void memberModify(MemberDTO member) {
		sqlSessionTemplate.update("admin.memberModify", member);
	}

	@Override
	public void memberDelete(int member_id) {
		sqlSessionTemplate.delete("admin.memberDelete", member_id);
	}

	
	
	//ajax로 지속적으로 요청을 받아 Ch칼럼값이 2인 회원들의 Ch값을 0으로 수정
	/*@Override
	public void outCh() {
		sqlSessionTemplate.update("admin.outCh");
	}*/
	
	
/*	@Resource
	private SqlSessionTemplate sqlSessionTemplate;
	
	public void updateCh() {//이 메소드는 세션 리스너에서 사용한다.
		sqlSessionTemplate.update("admin.updateCh");
	}*/
	
	

}
