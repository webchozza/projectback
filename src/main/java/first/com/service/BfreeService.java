package first.com.service;

import java.util.List;
import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.BfreeDAO;
import first.com.model.BcommentDTO;
import first.com.model.BoardDTO;

//주석확인
@Service
public class BfreeService implements BfreeDAO {

	@Resource
	private SqlSessionTemplate SqlSessionTemplate;
	
	@Override
	public List<BoardDTO> bfreeList() {
		// 由ъ뒪�듃 �굹�삤寃뚰븯源�
		return SqlSessionTemplate.selectList("bfree.bfreeList");
	}

	@Override
	public BoardDTO bfreeDetail(int board_id) {
		// �긽�꽭蹂닿린
		return SqlSessionTemplate.selectOne("bfree.bfreeSelect",board_id);
	}
	
	@Override
	public List<BcommentDTO> bcfreeList(int board_id){
		//�뙎湲�媛��졇�삤湲�
		return SqlSessionTemplate.selectList("bfree.bcfreeList", board_id);
	}

	@Override
	public String bfreeWriteForm() {
		// 湲��벐湲고뤌媛�湲�
		return null;
	}

	@Override
	public int bfreeWrite(BoardDTO boardDTO) {
		// 湲��벐源�
		return SqlSessionTemplate.insert("bfree.bfreeWrite", boardDTO);
	}

	@Override
	public BoardDTO bfreeModifyForm(BoardDTO boardDTO) {
		// 湲��닔�젙�뤌
		return boardDTO;
	}

	@Override
	public int bfreeModify(BoardDTO boardDTO) {
		// �닔�젙�븯湲�
		return SqlSessionTemplate.update("bfree.bfreeModify", boardDTO);
	}

	@Override
	public int bfreeDelete(int board_id) {
		// 湲��궘�젣
		return SqlSessionTemplate.delete("bfree.bfreeDelete", board_id);
	}
	
	@Override
	public int bfreeHit(int board_id){
		//議고쉶�닔 �삱由ш린
		return SqlSessionTemplate.update("bfree.bfreeUpdateHit", board_id);
	}

	@Override
	public int bfreeRecommand(int board_id) {
		// 異붿쿇�닔�삱由ш린
		return SqlSessionTemplate.update("bfree.bfreeUpdateHit", board_id);
	}
	

	@Override
	public int bfreeLike(int board_id) {
		// 醫뗭븘�슂
		return SqlSessionTemplate.update("bfree.bfreeUpdateLike", board_id);
	}

	@Override
	public int bfreeBad(int board_id) {
		// �떕�뼱�슂
		return SqlSessionTemplate.update("bfree.bfreeUpdateBad", board_id);
	}	

	@Override
	public int bCfreeWrite(BcommentDTO bcommentDTO) {
		// �뙎湲��벐湲�
		return SqlSessionTemplate.insert("bfree.bcfreeWrite", bcommentDTO);
	}

	@Override
	public int bCfreeDelete(int bcomment_id) {
		// �뙎湲��궘�젣
		return SqlSessionTemplate.delete("bfree.bcfreeDelete", bcomment_id);
	}

	@Override
	public int bfreeUpdateCountco(int board_id) {
		// �뙎湲��벐怨좊굹�꽌 �뙎湲��닔�뾽�뜲�씠�듃
		return SqlSessionTemplate.update("bfree.bfreeUpdateCountco", board_id);
	}

	@Override
	public List<BoardDTO> bfreeSearch0(String search) {
		// �젣紐⑹꽌移�
		return SqlSessionTemplate.selectList("bfree.bfreeSearchTitle", "%"+search+"%");
	}

	@Override
	public List<BoardDTO> bfreeSearch1(String search) {
		// �궡�슜�꽌移�
		return SqlSessionTemplate.selectList("bfree.bfreeSearchContent", "%"+search+"%");
	}

	@Override
	public List<BoardDTO> bfreeSearch2(String search) {
		// �옉�꽦�옄�꽌移�
		return SqlSessionTemplate.selectList("bfree.bfreeSearchMember", "%"+search+"%");
	}
	
	
}
