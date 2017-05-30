package first.com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	public List<BcommentDTO> bcqnaList(int board_id) {//댓글리스트
		return SST.selectList("bqna.bcqnaList", board_id);
	}

	@Override
	public int bCqnaWrite(BcommentDTO bcommentDTO) {//댓글쓰기
		return SST.insert("bqna.bcqnaWrite", bcommentDTO);
	}

	@Override
	public int bCqnaDelete(int board_id) {//댓글삭제
		return SST.delete("bqna.bcqnaDelete", board_id);
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
	public int bqnaUpdateHit(int board_id) {//조회수
		return SST.update("bqna.bqnaUpdateHit", board_id);
	}

	@Override
	public int bqnaLike(int board_id) {//추천
		return SST.update("bqna.bqnaUpdateLike", board_id);
	}

	@Override
	public int bqnaUpdateCountco(int board_id) {//댓글수 업데이트
		return SST.update("bqna.bqnaUpdateCountco", board_id);
	}
	
	@Override
	public void answerChoice(Map map){//답변채택
		int check = SST.selectOne("bqna.answerconfirmcheck", map);
		if(check==0){
		SST.insert("bqna.bqnaAnswerChoice", map);
		}
	}
	
	@Override
	public int bqnaAnswerCheck(int board_id){
		return SST.selectOne("bqna.bqnaAnswerCheck", board_id);
	}

	@Override
	public int bqnaAnswerDelete(int bcomment_id){
		return SST.delete("bqna.bqnaAnswerDelete", bcomment_id);
	}
}
