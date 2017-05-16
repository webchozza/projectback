package first.com.controller.bcode;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import first.com.model.BcommentDTO;
import first.com.model.BfileDTO;
import first.com.model.BoardDTO;

@Resource(name = "bcodedao")
public interface AbstractDAO {
	public List<BoardDTO> list(Map map) throws Exception;

	public BoardDTO detail(int board_id) throws Exception;
	public BfileDTO detailfile(int board_id) throws Exception;
	
	public void increamenthit(int increase) throws Exception;

	public void insert(BoardDTO dTO) throws Exception;
	
	public BoardDTO updateform(int update2) throws Exception;
	
	public void update(BoardDTO dTO) throws Exception;

	public void delete(BoardDTO dTO) throws Exception;
	
	public void upload(BfileDTO dTO) throws Exception;
	
	public List<BoardDTO> search(Map map) throws Exception;
	
	public List<BcommentDTO> comment(int board_id) throws Exception;
	
	public void commentinsert(BcommentDTO dTO) throws Exception;
	
	public void increamentlike(int like) throws Exception;
	
}