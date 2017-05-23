package first.com.controller.bcode;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import first.com.model.BcommentDTO;
import first.com.model.BfileDTO;
import first.com.model.BoardDTO;

@Resource(name="bcode")
public interface BcodeService{
	
	List<BoardDTO> bcodeList(Map map) throws Exception;
	
	BoardDTO bcodeDetail(int board_id) throws Exception;

	BfileDTO bcodeDetailfile(int board_id) throws Exception;
	
	void bcodeInreasehit(int increase) throws Exception;
	
	void bcodeInsert(BoardDTO dTO) throws Exception;
	
	BoardDTO bcodeUpdateform(int board_id) throws Exception;
	
	void bcodeUpdate(BoardDTO dTO) throws Exception;
	
	void bcodeUpload(BfileDTO dto) throws Exception;
	
	List<BoardDTO> bcodeSearch(Map map) throws Exception;
	
	List<BcommentDTO> bcodeComment(int board_id) throws Exception;
	
	void bcodeCommentinsert(BcommentDTO dTO) throws Exception;
	
	void bcodeInreaselike(int like) throws Exception;
	
	void bcodeDelete(int delete) throws Exception;
	
	void bcodeUploaddelete(int uploaddelete) throws Exception;
	
	
}