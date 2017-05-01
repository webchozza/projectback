package first.com.dao;

import java.util.List;

import first.com.model.NotiDTO;

public interface AlramDAO {
	
	public List<NotiDTO> notiList(int member_id);
	
	public String deleteAlram();

}
