package first.com.controller.bcode;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import first.com.model.BcommentDTO;
import first.com.model.BfileDTO;
import first.com.model.BoardDTO;

@Resource(name = "bcodedao")
public interface AbstractDAO {
	// 1.오픈소스 게시판 리스트
	public List<BoardDTO> list(Map map) throws Exception;

	// 2.상세보기
	public BoardDTO detail(int board_id) throws Exception;
	public BfileDTO detailfile(int board_id) throws Exception;
	
	// 2.1.상세보기 클릭시 조회수 증가
	public void increamenthit(int increase) throws Exception;

	// 3.글쓰기(작성완료)
	public void insert(BoardDTO dTO) throws Exception;
	
	// 4.수정(폼)
	public BoardDTO updateform(int update2) throws Exception;
	
	// 4.1 수정(작성완료)
	public void update(BoardDTO dTO) throws Exception;
	
	// 5.정렬은 BcodeController에만 존재함
	
	// 6.삭제
	public void delete(BoardDTO dTO) throws Exception;
	
	// 7.업로드
	public void upload(BfileDTO dTO) throws Exception;
	
	// 8.다운로드(BcodeController)
	
	// 9.검색
	public List<BoardDTO> search(Map map) throws Exception;
	
	// 10.댓글 리스트
	public List<BcommentDTO> comment(int board_id) throws Exception;
	
	// 11.댓글 쓰기
	public void commentinsert(BcommentDTO dTO) throws Exception;
	
	// 12.추천 및 스크랩
	public void increamentlike(int like) throws Exception;
	
	// 13.삭제
	public void delete(int delete) throws Exception;
	
	// 14.업로드 파일 삭제
	public void uploaddelete(int uploaddelete) throws Exception;
	
}