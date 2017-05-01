package first.com.service;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.BqnaDAO;
import first.com.model.BoardDTO;

@Service
public class BqnaService implements BqnaDAO {
	
	@Resource
	private SqlSessionTemplate SST;
	
	@Override
	public List<BoardDTO> bqnaList() {
		// qna ¸®½ºÆ®
		return SST.selectList("bqna.bqnaList");
	}

	@Override
	public String bqnaDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String bqnaWriteForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String bqnaWrite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String bqnaModifyForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String bqnaModify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String bqnaDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String bqnaRecommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String bCqnaWrite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String bCqnaDelete() {
		// TODO Auto-generated method stub
		return null;
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

}
