package first.com.service;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.controller.bqna.BqnaListDTO;
import first.com.dao.BqnaDAO;
import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;

@Service
public class BqnaService implements BqnaDAO {
	
	@Resource
	private SqlSessionTemplate SST;
	
	@Override
	public List<BoardDTO> bqnaList(BqnaListDTO bqnaListDTO) {// qna 리스트
		return SST.selectList("bqna.bqnaList", bqnaListDTO);
	}

	@Override
	public BoardDTO bqnaDetail(int id) {// qna 상세보기
		return SST.selectOne("bqna.bqnaDetail", id);
	}

	@Override
	public String bqnaWriteForm() {//글쓰기폼
		return null;
	}

	@Override
	public int bqnaWrite(BoardDTO boardDTO) {//qna 글쓰기
		return SST.insert("bqna.bqnaWrite", boardDTO);
	}

	@Override
	public BoardDTO bqnaModifyForm(BoardDTO boardDTO) {//수정폼
		return boardDTO;
	}

	@Override
	public int bqnaModify(BoardDTO boardDTO) {//수정
		return SST.update("bqna.bqnaModify", boardDTO);
	}

	@Override
	public int bqnaDelete(int board_id) {//삭제
		return SST.delete("bqna.bqnaDelete", board_id);
	}

	@Override
	public String bqnaRecommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int bCqnaWrite(BcommentDTO bcommentDTO) {// 코멘트쓰기
		return SST.insert("bqna.bcqnaWrite", bcommentDTO);
	}

	@Override
	public String bCqnaDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardDTO> bqnaSearch0(String search) {
		// TODO Auto-generated method stub
		return SST.selectList("bqna.bqnaSearchTitle", "%"+search+"%");
	}

	@Override
	public List<BoardDTO> bqnaSearch1(String search) {
		// TODO Auto-generated method stub
		return SST.selectList("bqna.bqnaSearchContent", "%"+search+"%");
	}

	@Override
	public List<BoardDTO> bqnaSearch2(String search) {
		// TODO Auto-generated method stub
		return SST.selectList("bqna.bqnaSearchMember", "%"+search+"%");
	}

	@Override
	public BoardDTO bqnaUpdateHit(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int bqnaLike(int board_id) {//추천
		return SST.update("bqna.bqnaUpdateLike", board_id);
	}

}
