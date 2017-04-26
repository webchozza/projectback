package first.com.dao;

import java.util.List;

import first.com.model.MemberDTO;

public interface AdminDAO {
	
	public List<MemberDTO> memberList();
	
	public void memberModify(MemberDTO member);
	
	public void memberDelete(int member_id);
	
	/*public void outCh();*/

}
