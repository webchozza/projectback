package first.com.dao;

import java.util.List;
import java.util.Map;

import first.com.model.MemberDTO;

public interface AdminDAO {
	
	public List<MemberDTO> memberList(Map<String, Object> map);
	
	public MemberDTO memberSelect(Map<String, Object> map);
	
	public void memberModify(MemberDTO member);
	
	public void memberDelete(MemberDTO member);
	
	public int memberCount(Map<String, Object> map);
	
	public void member_admin(Map<String, Object> map);

}
