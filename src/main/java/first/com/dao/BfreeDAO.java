package first.com.dao;

import java.util.List;

import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;

public interface BfreeDAO {
	
	public List<BoardDTO> bfreeList();
	
	public BoardDTO bfreeDetail(int board_id);
	
	public String bfreeWriteForm();
	
	public int bfreeWrite(BoardDTO boardDTO);
	
	public BoardDTO bfreeModifyForm(BoardDTO boardDTO);
	
	public int bfreeModify(BoardDTO boardDTO);
	
	public int bfreeDelete(int board_id);
	
	public int bfreeHit(int board_id);
	
	public int bfreeRecommand(int board_id);
	
	public int bfreeLike(int board_id);
	
	public int bfreeBad(int board_id);	
		
	
	public int bCfreeWrite(BcommentDTO bcommentDTO);
	
	public int bCfreeDelete(int bcomment_id);
	
	public int bfreeUpdateCountco(int board_id);

	public List<BoardDTO> bfreeSearch0(String search);
	
	public List<BoardDTO> bfreeSearch1(String search);
	
	public List<BoardDTO> bfreeSearch2(String search);

	public List<BcommentDTO> bcfreeList(int board_id);

	

}
