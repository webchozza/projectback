package first.com.controller.bcode;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import first.com.model.BcommentDTO;
import first.com.model.BfileDTO;
import first.com.model.BoardDTO;

@Resource(name="bcode")
public interface BcodeService{
	
	// 1.오픈소스 게시판 리스트(메인에서 클릭할 경우)
	List<BoardDTO> bcodeList(Map map) throws Exception;
	
	// 2.상세보기
	BoardDTO bcodeDetail(int board_id) throws Exception;
	BfileDTO bcodeDetailfile(int board_id) throws Exception;
	
	// 2.1.상세보기 클릭시 조회수 증가
	void bcodeInreasehit(int increase) throws Exception;
	
	// 3.글쓰기(작성완료)
	void bcodeInsert(BoardDTO dTO) throws Exception;
	
	// 4.수정(폼)
	BoardDTO bcodeUpdateform(int board_id) throws Exception;
	
	// 4.1수정(작성완료)
	void bcodeUpdate(BoardDTO dTO) throws Exception;
	
	// 5.정렬은 BcodeController에만 존재함
	

	// 6.삭제하기 void bcodeDelete(BoardDTO dTO) throws Exception;

	
	// 7.업로드
	void bcodeUpload(BfileDTO dto) throws Exception;
	
	// 8.다운로드(BcodeController)
	
	// 9.검색
	List<BoardDTO> bcodeSearch(Map map) throws Exception;
	
	// 10.댓글 리스트
	List<BcommentDTO> bcodeComment(int board_id) throws Exception;
	
	// 11.댓글 쓰기
	void bcodeCommentinsert(BcommentDTO dTO) throws Exception;
	
	// 12.추천 및 스크랩
	void bcodeInreaselike(int like) throws Exception;
	
	// 13.삭제
	void bcodeDelete(int delete) throws Exception;
	
	// 14.업로드 파일 삭제
	void bcodeUploaddelete(int uploaddelete) throws Exception;
	
	
}