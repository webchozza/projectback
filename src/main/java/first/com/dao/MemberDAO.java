package first.com.dao;

import first.com.model.MemberDTO;

public interface MemberDAO {
	
	public MemberDTO login(MemberDTO member);
	
	public void loginUpdate(MemberDTO member);
	
	public void logOut(MemberDTO member);
	
	public MemberDTO checkModify(MemberDTO member);
	
	public MemberDTO findPw(MemberDTO member);
	
	public void updatePw(MemberDTO member);
	
	public void join(MemberDTO member);
	
	public boolean checkName(MemberDTO member);
	
	public boolean checkEmail(MemberDTO member);
	
	public void modifyMember(MemberDTO member);
	
	public void deleteMember(MemberDTO member);
	
	public MemberDTO naverLogin(String member);
	
	
	
}
