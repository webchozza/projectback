package first.com.controller.bcode;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import first.com.model.BcommentDTO;
import first.com.model.BfileDTO;
import first.com.model.BoardDTO;

@Resource(name = "bcodedao")
public interface AbstractDAO {
	// 1.�삤�뵂�냼�뒪 寃뚯떆�뙋 由ъ뒪�듃
	public List<BoardDTO> list(Map map) throws Exception;

	// 2.�긽�꽭蹂닿린
	public BoardDTO detail(int board_id) throws Exception;
	public BfileDTO detailfile(int board_id) throws Exception;
	
	// 2.1.�긽�꽭蹂닿린 �겢由��떆 議고쉶�닔 利앷�
	public void increamenthit(int increase) throws Exception;

	// 3.湲��벐湲�(�옉�꽦�셿猷�)
	public void insert(BoardDTO dTO) throws Exception;
	
	// 4.�닔�젙(�뤌)
	public BoardDTO updateform(int update2) throws Exception;
	
	// 4.1 �닔�젙(�옉�꽦�셿猷�)
	public void update(BoardDTO dTO) throws Exception;
	
	// 5.�젙�젹�� BcodeController�뿉留� 議댁옱�븿
	
	// 6.�궘�젣
	public void delete(BoardDTO dTO) throws Exception;
	
	// 7.�뾽濡쒕뱶
	public void upload(BfileDTO dTO) throws Exception;
	
	// 8.�떎�슫濡쒕뱶(BcodeController)
	
	// 9.寃��깋
	public List<BoardDTO> search(Map map) throws Exception;
	
	// 10.�뙎湲� 由ъ뒪�듃
	public List<BcommentDTO> comment(int board_id) throws Exception;
	
	// 11.�뙎湲� �벐湲�
	public void commentinsert(BcommentDTO dTO) throws Exception;
	
	// 12.異붿쿇 諛� �뒪�겕�옪
	public void increamentlike(int like) throws Exception;
	
	// 13.�궘�젣
	public void delete(int delete) throws Exception;
	
	// 14.�뾽濡쒕뱶 �뙆�씪 �궘�젣
	public void uploaddelete(int uploaddelete) throws Exception;
	
	public void delco(int bcomment_id) throws Exception;
	
}