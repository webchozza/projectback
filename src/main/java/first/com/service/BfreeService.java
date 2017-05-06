package first.com.service;

import java.util.List;
import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.BfreeDAO;
import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;

//주석확인
@Service
public class BfreeService implements BfreeDAO {

	@Resource
	private SqlSessionTemplate SqlSessionTemplate;
	
	@Override
	public List<BoardDTO> bfreeList() {

		return SqlSessionTemplate.selectList("bfree.bfreeList");
	}

	@Override
	public BoardDTO bfreeDetail(int board_id) {
		return SqlSessionTemplate.selectOne("bfree.bfreeSelect",board_id);
	}
	
	@Override
	public List<BcommentDTO> bcfreeList(int board_id){
		return SqlSessionTemplate.selectList("bfree.bcfreeList", board_id);
	}

	@Override
	public String bfreeWriteForm() {
		return null;
	}

	@Override
	public int bfreeWrite(BoardDTO boardDTO) {
		return SqlSessionTemplate.insert("bfree.bfreeWrite", boardDTO);
	}

	@Override
	public BoardDTO bfreeModifyForm(BoardDTO boardDTO) {
		return boardDTO;
	}

	@Override
	public int bfreeModify(BoardDTO boardDTO) {
		return SqlSessionTemplate.update("bfree.bfreeModify", boardDTO);
	}

	@Override
	public int bfreeDelete(int board_id) {
		return SqlSessionTemplate.delete("bfree.bfreeDelete", board_id);
	}
	
	@Override
	public int bfreeHit(int board_id){
		return SqlSessionTemplate.update("bfree.bfreeUpdateHit", board_id);
	}

	@Override
	public int bfreeRecommand(int board_id) {
		return SqlSessionTemplate.update("bfree.bfreeUpdateHit", board_id);
	}
	

	@Override
	public int bfreeLike(int board_id) {
		return SqlSessionTemplate.update("bfree.bfreeUpdateLike", board_id);
	}

	@Override
	public int bfreeBad(int board_id) {
		return SqlSessionTemplate.update("bfree.bfreeUpdateBad", board_id);
	}	

	@Override
	public int bCfreeWrite(BcommentDTO bcommentDTO) {
		return SqlSessionTemplate.insert("bfree.bcfreeWrite", bcommentDTO);
	}

	@Override
	public int bCfreeDelete(int bcomment_id) {
		return SqlSessionTemplate.delete("bfree.bcfreeDelete", bcomment_id);
	}

	@Override
	public int bfreeUpdateCountco(int board_id) {
		return SqlSessionTemplate.update("bfree.bfreeUpdateCountco", board_id);
	}

	@Override
	public List<BoardDTO> bfreeSearch0(String search) {
		return SqlSessionTemplate.selectList("bfree.bfreeSearchTitle", "%"+search+"%");
	}

	@Override
	public List<BoardDTO> bfreeSearch1(String search) {
		return SqlSessionTemplate.selectList("bfree.bfreeSearchContent", "%"+search+"%");
	}

	@Override
	public List<BoardDTO> bfreeSearch2(String search) {
		return SqlSessionTemplate.selectList("bfree.bfreeSearchMember", "%"+search+"%");
	}
	
	
}
