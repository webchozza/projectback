package first.com.dao;

import first.com.model.MemberDTO;

public interface MemberDAO {
	
	public String loginForm();
	
	public MemberDTO login(MemberDTO member);
	
	public void loginUpdate(MemberDTO member);
	
	public void logOut(MemberDTO member);
	
	public MemberDTO checkModify(MemberDTO member);
	
	public String findForm();
	
	public MemberDTO findPw(MemberDTO member);
	
	public void updatePw(MemberDTO member);
	
	public String joinForm();
	
	public String email();
	
	public void join(MemberDTO member);
	
	public boolean checkName(MemberDTO member);
	
	public boolean checkEmail(MemberDTO member);
	
	public MemberDTO modifyMemberForm(MemberDTO member);
	
	public void modifyMember(MemberDTO member);
	
	
	
	public String deleteMemberForm();
	
	public String deleteMember();
	
	
	
	public String alramList();
	
	public String deleteAlram();
	
	
	
	public String scrapList();
	
	public String deleteScrap();
	
}
