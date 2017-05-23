package first.com.dao;

import java.util.List;
import java.util.Map;

import first.com.controller.bqna.BqnaListDTO;
import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;

public interface BqnaDAO {
	
	public List<BoardDTO> bqnaList(BqnaListDTO bqnaListVO);
	
	public BoardDTO bqnaDetail(int id);
	
	public String bqnaWriteForm();
	
	public int bqnaWrite(BoardDTO boardDTO);
	
	public BoardDTO bqnaModifyForm(BoardDTO boardDTO);
	
	public int bqnaModify(BoardDTO boardDTO);
	
	public int bqnaDelete(int board_id);
	
	public int bqnaLike(int board_id);
	
	public String bqnaRecommand();
	
	public List<BoardDTO> bqnaSearch0(String search);
	
	public List<BoardDTO> bqnaSearch1(String search);
	
	public List<BoardDTO> bqnaSearch2(String search);
	
	public List<BcommentDTO> bcqnaList(int board_id);
	
	public int bCqnaWrite(BcommentDTO bcommentDTO);
	
	public int bCqnaDelete(int bcomment_id);
	
	public int bqnaUpdateHit(int board_id);
	
	public int bqnaUpdateCountco(int board_id);
	
	public void answerChoice(Map map);
	
	public int bqnaAnswerCheck(int board_id);

	public int bqnaAnswerDelete(int bcomment_id);
}
