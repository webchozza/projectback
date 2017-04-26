package first.com.dao;

import java.util.List;
import java.util.Map;

import first.com.model.MemberDTO;

public interface AdminDAO {
	
	public List<MemberDTO> memberList();
	
	public MemberDTO memberSelect(Map map);
	
	public void memberModify(MemberDTO member);
	
	public void memberDelete(MemberDTO member);
	
	/*public void outCh();*/

}
