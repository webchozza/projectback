package first.com.controller.bcode;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import first.com.model.BcommentDTO;
import first.com.model.BfileDTO;
import first.com.model.BoardDTO;

@Service("bcodeService")
public class BcodeServiceImpl implements BcodeService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private AbstractDAO bcodedao;

	@Override
	public List<BoardDTO> bcodeList(Map map) throws Exception{
		return bcodedao.list(map);
	}
	@Override
	public BoardDTO bcodeDetail(int board_id) throws Exception{
		return bcodedao.detail(board_id);
	}
	@Override
	public BfileDTO bcodeDetailfile(int board_id) throws Exception{
		return bcodedao.detailfile(board_id);
	}
	@Override
	public void bcodeInreasehit(int increase) throws Exception{
		bcodedao.increamenthit(increase);
	}
	@Override
	public void bcodeInsert(BoardDTO dTO) throws Exception{
		bcodedao.insert(dTO);
	}
	
	@Override
	public BoardDTO bcodeUpdateform(int update2) throws Exception{
		return bcodedao.updateform(update2);
	}
	@Override
	public void bcodeUpdate(BoardDTO dTO) throws Exception{
		bcodedao.update(dTO);
	}
	
	@Override
	public void bcodeUpload(BfileDTO dTO) throws Exception{
		bcodedao.upload(dTO);
	}
	
	@Override
	public List<BoardDTO> bcodeSearch(Map map) throws Exception{
		return bcodedao.search(map);
	}
	
	@Override
	public List<BcommentDTO> bcodeComment(int board_id) throws Exception{
		return bcodedao.comment(board_id);
	}
	
	@Override
	public void bcodeCommentinsert(BcommentDTO dTO) throws Exception{
		bcodedao.commentinsert(dTO);
	}
	
	@Override
	public void bcodeInreaselike(int like) throws Exception{
		bcodedao.increamentlike(like);
	}
	
	@Override
	public void bcodeDelete(int delete) throws Exception{
		bcodedao.delete(delete);
	}
	
	@Override
	public void bcodeUploaddelete(int uploaddelete) throws Exception{
		bcodedao.uploaddelete(uploaddelete);
	}
}
