package first.com.controller.bcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import first.com.model.BcommentDTO;
import first.com.model.BfileDTO;
import first.com.model.BoardDTO;

@Repository
public class BcodeDAO implements AbstractDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	// 1.오픈소스 게시판 리스트
	@SuppressWarnings("rawtypes")
	@Override
	public List<BoardDTO> list(Map map) throws Exception {
		return sqlSession.selectList("bcode.list",map);
	}

	// 2.상세보기
	@Override
	public BoardDTO detail(int board_id) throws Exception {
		return sqlSession.selectOne("bcode.detail", board_id);
	}
	public BfileDTO detailfile(int board_id) throws Exception{
		return sqlSession.selectOne("bcode.detailfile",board_id);
	}

	// 2.1상세보기 클릭스 조회수 증가
	@Override
	public void increamenthit(int increase) throws Exception {
		sqlSession.update("bcode.increamenthit",increase);
	}

	// 3.글쓰기(작성완료)
	@Override  
	public void insert(BoardDTO dTO) throws Exception {
		sqlSession.insert("bcode.insert", dTO);
	}
	
	// 4.수정(폼)
	public BoardDTO updateform(int update2) throws Exception{
		return sqlSession.selectOne("bcode.updateform", update2);
	}
	// 4.1수정(작성완료)
	@Override 
	public void update(BoardDTO dTO) throws Exception{
		sqlSession.update("bcode.update", dTO); 
	}

	// 5.정렬은 BcodeController에만 존재함
	
	// 6.삭제
	@Override 
	public void delete(BoardDTO dTO) throws Exception{
		sqlSession.delete("bcode.delete", dTO);
	}
	
	// 7.업로드
	@Override
	public void upload(BfileDTO dTO) throws Exception{
		sqlSession.insert("bcode.upload", dTO);
	}
	
	// 8.다운로드(BcodeController)
	
	// 9.검색
	public List<BoardDTO> search(Map map) throws Exception {
		return sqlSession.selectList("bcode.search",map);
	}
	// 10. 댓글 리스트
	public List<BcommentDTO> comment(int board_id) throws Exception{
		return sqlSession.selectList("bcode.comment", board_id);
	}
	// 11.댓글 작성
	public void commentinsert(BcommentDTO dTO) throws Exception{
		sqlSession.insert("bcode.commentwrite", dTO);
	}
	// 12.추천 및 스크랩
	public void increamentlike(int like) throws Exception{
		sqlSession.update("bcode.increasementlike", like);
	}

}
