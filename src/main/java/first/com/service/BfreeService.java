package first.com.service;

import java.util.List;
import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.controller.bfree.BfreeListDTO;
import first.com.dao.BfreeDAO;
import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;

@Service
public class BfreeService implements BfreeDAO {

	@Resource
	private SqlSessionTemplate SqlSessionTemplate;

	@Override
	public List<BoardDTO> bfreeList(BfreeListDTO bfreeListVO) {// 리스트
		return SqlSessionTemplate.selectList("bfree.bfreeList", bfreeListVO);
	}

	@Override
	public BoardDTO bfreeDetail(int board_id) {// 상세
		return SqlSessionTemplate.selectOne("bfree.bfreeSelect", board_id);
	}

	@Override
	public List<BcommentDTO> bcfreeList(int board_id) {// 코멘트리스트
		return SqlSessionTemplate.selectList("bfree.bcfreeList", board_id);
	}

	@Override
	public String bfreeWriteForm() {// 쓰기폼
		return null;
	}

	@Override
	public int bfreeWrite(BoardDTO boardDTO) {// 쓰기
		return SqlSessionTemplate.insert("bfree.bfreeWrite", boardDTO);
	}

	@Override
	public BoardDTO bfreeModifyForm(BoardDTO boardDTO) {// 수정폼
		return boardDTO;
	}

	@Override
	public int bfreeModify(BoardDTO boardDTO) {// 수정
		return SqlSessionTemplate.update("bfree.bfreeModify", boardDTO);
	}

	@Override
	public int bfreeDelete(int board_id) {// 삭제
		return SqlSessionTemplate.delete("bfree.bfreeDelete", board_id);
	}

	@Override
	public int bfreeHit(int board_id) {// 조회수업뎃
		return SqlSessionTemplate.update("bfree.bfreeUpdateHit", board_id);
	}

	@Override
	public int bfreeLike(int board_id) {// 추천
		return SqlSessionTemplate.update("bfree.bfreeUpdateLike", board_id);
	}

	@Override
	public int bfreeBad(int board_id) {// 시러요
		return SqlSessionTemplate.update("bfree.bfreeUpdateBad", board_id);
	}

	@Override
	public int bCfreeWrite(BcommentDTO bcommentDTO) {// 코멘트쓰기
		return SqlSessionTemplate.insert("bfree.bcfreeWrite", bcommentDTO);
	}

	@Override
	public int bCfreeDelete(int bcomment_id) {// 코멘삭제
		return SqlSessionTemplate.delete("bfree.bcfreeDelete", bcomment_id);
	}

	@Override
	public int bfreeUpdateCountco(int board_id) {// 코멘수업뎃
		return SqlSessionTemplate.update("bfree.bfreeUpdateCountco", board_id);
	}

}
