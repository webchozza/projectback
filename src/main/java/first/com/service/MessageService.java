package first.com.service;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import first.com.dao.MessageDAO;
import first.com.model.MessageDTO;
@Service
@Resource(name="massegeService")
public class MessageService implements MessageDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<MessageDTO> getList(MessageDTO massage) {
		  
		return sqlSession.selectList("message.getList",massage);
	}
	
	
	@Override
	public List<MessageDTO> getSearchList(MessageDTO message) {
		
		return sqlSession.selectList("message.getSearchList",message);
	}


	public List<MessageDTO> getReadList(MessageDTO message) {
		  
		return sqlSession.selectList("message.getReadList",message);
	}
	
	@Override
	public void setDelete(MessageDTO message) {
		sqlSession.delete("message.delete",message);
		
	}
	
	public void setRead(MessageDTO message) {
		sqlSession.update("message.setRead",message);
		
	}
	
	@Override
	public MessageDTO getContent(MessageDTO message) {
		
		return sqlSession.selectOne("message.getOne", message);
	}

	@Override
	public void setWrite(MessageDTO message) {
		sqlSession.insert("message.setContent", message);
		
	}
	
	
	

}
