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

	// 1.�삤�뵂�냼�뒪 寃뚯떆�뙋 由ъ뒪�듃
	@SuppressWarnings("rawtypes")
	@Override
	public List<BoardDTO> list(Map map) throws Exception {
		return sqlSession.selectList("bcode.list",map);
	}

	// 2.�긽�꽭蹂닿린
	@Override
	public BoardDTO detail(int board_id) throws Exception {
		return sqlSession.selectOne("bcode.detail", board_id);
	}
	@Override
	public BfileDTO detailfile(int board_id) throws Exception{
		return sqlSession.selectOne("bcode.detailfile",board_id);
	}

	// 2.1�긽�꽭蹂닿린 �겢由��뒪 議고쉶�닔 利앷�
	@Override
	public void increamenthit(int increase) throws Exception {
		sqlSession.update("bcode.increamenthit",increase);
	}

	// 3.湲��벐湲�(�옉�꽦�셿猷�)
	@Override  
	public void insert(BoardDTO dTO) throws Exception {
		sqlSession.insert("bcode.insert", dTO);
	}
	
	// 4.�닔�젙(�뤌)
	@Override
	public BoardDTO updateform(int update2) throws Exception{
		return sqlSession.selectOne("bcode.updateform", update2);
	}
	// 4.1�닔�젙(�옉�꽦�셿猷�)
	@Override 
	public void update(BoardDTO dTO) throws Exception{
		sqlSession.update("bcode.update", dTO); 
	}

	// 5.�젙�젹�� BcodeController�뿉留� 議댁옱�븿
	
	// 6.�궘�젣
	@Override 
	public void delete(BoardDTO dTO) throws Exception{
		sqlSession.delete("bcode.delete", dTO);
	}
	
	// 7.�뾽濡쒕뱶
	@Override
	public void upload(BfileDTO dTO) throws Exception{
		sqlSession.insert("bcode.upload", dTO);
	}
	
	// 8.�떎�슫濡쒕뱶(BcodeController)
	
	// 9.寃��깋
	@Override
	public List<BoardDTO> search(Map map) throws Exception {
		return sqlSession.selectList("bcode.search",map);
	}
	// 10. �뙎湲� 由ъ뒪�듃
	@Override
	public List<BcommentDTO> comment(int board_id) throws Exception{
		return sqlSession.selectList("bcode.comment", board_id);
	}
	// 11.�뙎湲� �옉�꽦
	@Override
	public void commentinsert(BcommentDTO dTO) throws Exception{
		sqlSession.insert("bcode.commentwrite", dTO);
	}
	// 12.異붿쿇 諛� �뒪�겕�옪
	@Override
	public void increamentlike(int like) throws Exception{
		sqlSession.update("bcode.increasementlike", like);
	}

}
