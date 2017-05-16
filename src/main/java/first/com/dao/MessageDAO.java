package first.com.dao;

import java.util.List;

import first.com.model.MessageDTO;

public interface MessageDAO {

	
	public List<MessageDTO> getList(MessageDTO message);
	
	public List<MessageDTO> getSendList(MessageDTO message);
	
	public List<MessageDTO> getReceiveList(MessageDTO message);
	
	public List<MessageDTO> getSearchList(MessageDTO message);
	
	public void setDelete(MessageDTO message);
	
	public MessageDTO getContent(MessageDTO message);
	
	public void setWrite(MessageDTO message);
	
	public List<MessageDTO> getReadList(MessageDTO message);
	
	public void setRead(MessageDTO message);
}
