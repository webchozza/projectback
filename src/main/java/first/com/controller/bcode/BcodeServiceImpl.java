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

	// 1.�삤�뵂�냼�뒪 寃뚯떆�뙋 由ъ뒪�듃(硫붿씤�뿉�꽌 �겢由��븷 寃쎌슦)
	public List<BoardDTO> bcodeList(Map map) throws Exception{
		return bcodedao.list(map);
	}
	// 2.�긽�꽭蹂닿린
	public BoardDTO bcodeDetail(int board_id) throws Exception{
		return bcodedao.detail(board_id);
	}
	public BfileDTO bcodeDetailfile(int board_id) throws Exception{
		return bcodedao.detailfile(board_id);
	}
	// 2.1.�긽�꽭蹂닿린 �겢由��떆 議고쉶�닔 利앷�
	public void bcodeInreasehit(int increase) throws Exception{
		bcodedao.increamenthit(increase);
	}
	// 3.湲��벐湲�(�옉�꽦�셿猷�)
	public void bcodeInsert(BoardDTO dTO) throws Exception{
		bcodedao.insert(dTO);
	}
	// 4.�닔�젙(�뤌)
	public BoardDTO bcodeUpdateform(int update2) throws Exception{
		return bcodedao.updateform(update2);
	}
	// 4.1�닔�젙(�옉�꽦�셿猷�)
	public void bcodeUpdate(BoardDTO dTO) throws Exception{
		bcodedao.update(dTO);
	}
	
	// 5.�젙�젹�� BcodeController�뿉留� 議댁옱�븿
	
	// 6.�궘�젣�븯湲�

	// 7.�뾽濡쒕뱶
	public void bcodeUpload(BfileDTO dTO) throws Exception{
		bcodedao.upload(dTO);
	}
	
	// 8.�떎�슫濡쒕뱶
	
	// 9.寃��깋
	public List<BoardDTO> bcodeSearch(Map map) throws Exception{
		return bcodedao.search(map);
	}
	// 10.�뙎湲� 由ъ뒪�듃
	public List<BcommentDTO> bcodeComment(int board_id) throws Exception{
		return bcodedao.comment(board_id);
	}
	// 11.�뙎湲� �벐湲�
	public void bcodeCommentinsert(BcommentDTO dTO) throws Exception{
		bcodedao.commentinsert(dTO);
	}
	// 12.異붿쿇 諛� �뒪�겕�옪
	public void bcodeInreaselike(int like) throws Exception{
		bcodedao.increamentlike(like);
	}
	// 13.�궘�젣
	public void bcodeDelete(int delete) throws Exception{
		bcodedao.delete(delete);
	}
	// 14.�뾽濡쒕뱶 �뙆�씪 �궘�젣
	public void bcodeUploaddelete(int uploaddelete) throws Exception{
		bcodedao.uploaddelete(uploaddelete);
	}
	@Override
	public void delco(int bcomment_id) throws Exception {
		bcodedao.delco(bcomment_id);
	}
	
}
