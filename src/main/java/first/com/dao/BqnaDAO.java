package first.com.dao;

import java.util.List;

import first.com.model.BoardDTO;

public interface BqnaDAO {
	
	public List<BoardDTO> bqnaList();
	
	public BoardDTO bqnaDetail(int id);
	
	public String bqnaWriteForm();
	
	public int bqnaWrite(BoardDTO boardDTO);
	
	public String bqnaModifyForm();
	
	public String bqnaModify();
	
	public String bqnaDelete();
	
	public String bqnaRecommand();
	
	public List<BoardDTO> bqnaSearch0(String search);
	
	public List<BoardDTO> bqnaSearch1(String search);
	
	public List<BoardDTO> bqnaSearch2(String search);
	
	public String bCqnaWrite();
	
	public String bCqnaDelete();
	
	public BoardDTO bqnaUpdateHit(int id);
}
