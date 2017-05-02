package first.com.service;

import java.util.List;
import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.BfreeDAO;
import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;


@Service
public class BfreeService implements BfreeDAO {

	@Resource
	private SqlSessionTemplate SqlSessionTemplate;
	
	@Override
	public List<BoardDTO> bfreeList() {
		// 리스트 나오게하깅
		return SqlSessionTemplate.selectList("bfree.bfreeList");
	}

	@Override
	public BoardDTO bfreeDetail(int board_id) {
		// 상세보기
		return SqlSessionTemplate.selectOne("bfree.bfreeSelect",board_id);
	}
	
	@Override
	public List<BcommentDTO> bcfreeList(int board_id){
		//댓글가져오기
		return SqlSessionTemplate.selectList("bfree.bcfreeList", board_id);
	}

	@Override
	public String bfreeWriteForm() {
		// 글쓰기폼가기
		return null;
	}

	@Override
	public int bfreeWrite(BoardDTO boardDTO) {
		// 글쓰깅
		return SqlSessionTemplate.insert("bfree.bfreeWrite", boardDTO);
	}

	@Override
	public BoardDTO bfreeModifyForm(BoardDTO boardDTO) {
		// 글수정폼
		return boardDTO;
	}

	@Override
	public int bfreeModify(BoardDTO boardDTO) {
		// 수정하기
		return SqlSessionTemplate.update("bfree.bfreeModify", boardDTO);
	}

	@Override
	public int bfreeDelete(int board_id) {
		// 글삭제
		return SqlSessionTemplate.delete("bfree.bfreeDelete", board_id);
	}
	
	@Override
	public int bfreeHit(int board_id){
		//조회수 올리기
		return SqlSessionTemplate.update("bfree.bfreeUpdateHit", board_id);
	}

	@Override
	public int bfreeRecommand(int board_id) {
		// 추천수올리기
		return SqlSessionTemplate.update("bfree.bfreeUpdateHit", board_id);
	}
	

	@Override
	public int bfreeLike(int board_id) {
		// 좋아요
		return SqlSessionTemplate.update("bfree.bfreeUpdateLike", board_id);
	}

	@Override
	public int bfreeBad(int board_id) {
		// 싫어요
		return SqlSessionTemplate.update("bfree.bfreeUpdateBad", board_id);
	}	

	@Override
	public int bCfreeWrite(BcommentDTO bcommentDTO) {
		// 댓글쓰기
		return SqlSessionTemplate.insert("bfree.bcfreeWrite", bcommentDTO);
	}

	@Override
	public int bCfreeDelete(int bcomment_id) {
		// 댓글삭제
		return SqlSessionTemplate.delete("bfree.bcfreeDelete", bcomment_id);
	}

	@Override
	public int bfreeUpdateCountco(int board_id) {
		// 댓글쓰고나서 댓글수업데이트
		return SqlSessionTemplate.update("bfree.bfreeUpdateCountco", board_id);
	}

	@Override
	public List<BoardDTO> bfreeSearch0(String search) {
		// 제목서치
		return SqlSessionTemplate.selectList("bfree.bfreeSearchTitle", "%"+search+"%");
	}

	@Override
	public List<BoardDTO> bfreeSearch1(String search) {
		// 내용서치
		return SqlSessionTemplate.selectList("bfree.bfreeSearchContent", "%"+search+"%");
	}

	@Override
	public List<BoardDTO> bfreeSearch2(String search) {
		// 작성자서치
		return SqlSessionTemplate.selectList("bfree.bfreeSearchMember", "%"+search+"%");
	}
	
	
}
