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
	public String loginForm() {
		
		return null;
	}

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
	public String findForm() {
		// TODO Auto-generated method stub
		return null;
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
	public String joinForm() {
		// TODO Auto-generated method stub
		return null;
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
	public String email() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public MemberDTO modifyMemberForm(MemberDTO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyMember(MemberDTO member) {
		System.out.println("¸â¹ö ÀÌ¸§"+member.getMember_name());
		System.out.println("¸â¹ö ÀÌ¸§"+member.getMember_email());
		sqlSession.update("member.modifyMember", member);
	}

	@Override
	public String deleteMemberForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteMember() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String alramList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteAlram() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String scrapList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteScrap() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
