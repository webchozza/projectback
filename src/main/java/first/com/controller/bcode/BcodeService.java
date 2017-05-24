package first.com.controller.bcode;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import first.com.model.BcommentDTO;
import first.com.model.BfileDTO;
import first.com.model.BoardDTO;

@Resource(name="bcode")
public interface BcodeService{
	
	// 1.�삤�뵂�냼�뒪 寃뚯떆�뙋 由ъ뒪�듃(硫붿씤�뿉�꽌 �겢由��븷 寃쎌슦)
	List<BoardDTO> bcodeList(Map map) throws Exception;
	
	// 2.�긽�꽭蹂닿린
	BoardDTO bcodeDetail(int board_id) throws Exception;
	BfileDTO bcodeDetailfile(int board_id) throws Exception;
	
	// 2.1.�긽�꽭蹂닿린 �겢由��떆 議고쉶�닔 利앷�
	void bcodeInreasehit(int increase) throws Exception;
	
	// 3.湲��벐湲�(�옉�꽦�셿猷�)
	void bcodeInsert(BoardDTO dTO) throws Exception;
	
	// 4.�닔�젙(�뤌)
	BoardDTO bcodeUpdateform(int board_id) throws Exception;
	
	// 4.1�닔�젙(�옉�꽦�셿猷�)
	void bcodeUpdate(BoardDTO dTO) throws Exception;
	
	// 5.�젙�젹�� BcodeController�뿉留� 議댁옱�븿
	

	// 6.�궘�젣�븯湲� void bcodeDelete(BoardDTO dTO) throws Exception;

	
	// 7.�뾽濡쒕뱶
	void bcodeUpload(BfileDTO dto) throws Exception;
	
	// 8.�떎�슫濡쒕뱶(BcodeController)
	
	// 9.寃��깋
	List<BoardDTO> bcodeSearch(Map map) throws Exception;
	
	// 10.�뙎湲� 由ъ뒪�듃
	List<BcommentDTO> bcodeComment(int board_id) throws Exception;
	
	// 11.�뙎湲� �벐湲�
	void bcodeCommentinsert(BcommentDTO dTO) throws Exception;
	
	// 12.異붿쿇 諛� �뒪�겕�옪
	void bcodeInreaselike(int like) throws Exception;
	
	// 13.�궘�젣
	void bcodeDelete(int delete) throws Exception;
	
	// 14.�뾽濡쒕뱶 �뙆�씪 �궘�젣
	void bcodeUploaddelete(int uploaddelete) throws Exception;
	
	void delco(int bcomment_id) throws Exception;
	
}