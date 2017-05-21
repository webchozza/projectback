package first.com.service;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import first.com.dao.MemberDAO;
import first.com.model.MemberDTO;
@Service
@Resource(name="memberService")
public class MemberService implements MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public MemberDTO login(MemberDTO member){
		
		return sqlSession.selectOne("member.loginCheck",member);
	}
	
	@Override
	public void loginUpdate(MemberDTO member) {
		sqlSession.update("member.loginUpdate", member);
		
	}
	
	@Override
	public void logOut(MemberDTO member) {
		sqlSession.update("member.logOut",member);
		
	}

	@Override
	public MemberDTO findPw(MemberDTO member) {
		return sqlSession.selectOne("member.findPw", member);
	}
	
	

	@Override
	public void updatePw(MemberDTO member) {
		sqlSession.update("member.updatePw", member);
		
	}

		
	@Override
	public boolean checkName(MemberDTO member) {
		if(sqlSession.selectOne("member.checkName", member)==null){
			return true;
		};
			return false;
	}

	@Override
	public boolean checkEmail(MemberDTO member) {
		if(sqlSession.selectOne("member.checkEmail", member)==null){
			return true;
		};
			return false;
	}
	
	@Override
	public void join(MemberDTO member) {
		sqlSession.insert("member.join", member);
	}
	
	@Override
	public MemberDTO checkModify(MemberDTO member) {
		
		return sqlSession.selectOne("member.checkModify", member);
	}
	
	@Override
	public void deleteMember(MemberDTO member) {
		sqlSession.update("member.delete", member);
		
	}


	@Override
	public void modifyMember(MemberDTO member) {
		
		sqlSession.update("member.modifyMember", member);
	}
	

	@Override
	public MemberDTO naverLogin(String member) {
		
		return sqlSession.selectOne("member.naverLogin",member);
	}

	
}
