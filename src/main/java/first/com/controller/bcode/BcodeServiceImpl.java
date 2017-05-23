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

	// 1.오픈소스 게시판 리스트(메인에서 클릭할 경우)
	public List<BoardDTO> bcodeList(Map map) throws Exception{
		return bcodedao.list(map);
	}
	// 2.상세보기
	public BoardDTO bcodeDetail(int board_id) throws Exception{
		return bcodedao.detail(board_id);
	}
	public BfileDTO bcodeDetailfile(int board_id) throws Exception{
		return bcodedao.detailfile(board_id);
	}
	// 2.1.상세보기 클릭시 조회수 증가
	public void bcodeInreasehit(int increase) throws Exception{
		bcodedao.increamenthit(increase);
	}
	// 3.글쓰기(작성완료)
	public void bcodeInsert(BoardDTO dTO) throws Exception{
		bcodedao.insert(dTO);
	}
	// 4.수정(폼)
	public BoardDTO bcodeUpdateform(int update2) throws Exception{
		return bcodedao.updateform(update2);
	}
	// 4.1수정(작성완료)
	public void bcodeUpdate(BoardDTO dTO) throws Exception{
		bcodedao.update(dTO);
	}
	
	// 5.정렬은 BcodeController에만 존재함
	
	// 6.삭제하기

	// 7.업로드
	public void bcodeUpload(BfileDTO dTO) throws Exception{
		bcodedao.upload(dTO);
	}
	
	// 8.다운로드
	
	// 9.검색
	public List<BoardDTO> bcodeSearch(Map map) throws Exception{
		return bcodedao.search(map);
	}
	// 10.댓글 리스트
	public List<BcommentDTO> bcodeComment(int board_id) throws Exception{
		return bcodedao.comment(board_id);
	}
	// 11.댓글 쓰기
	public void bcodeCommentinsert(BcommentDTO dTO) throws Exception{
		bcodedao.commentinsert(dTO);
	}
	// 12.추천 및 스크랩
	public void bcodeInreaselike(int like) throws Exception{
		bcodedao.increamentlike(like);
	}
	// 13.삭제
	public void bcodeDelete(int delete) throws Exception{
		bcodedao.delete(delete);
	}
	// 14.업로드 파일 삭제
	public void bcodeUploaddelete(int uploaddelete) throws Exception{
		bcodedao.uploaddelete(uploaddelete);
	}
}
