package first.com.dao;

import java.util.List;

import first.com.model.BoardDTO;
import first.com.model.TagDTO;

public interface TagDAO {

	
	public List<BoardDTO> tagList(TagDTO tagDTO);
}
