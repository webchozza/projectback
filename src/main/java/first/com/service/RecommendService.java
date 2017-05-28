package first.com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.RecommendDAO;

@Service
@Resource(name="recommendSerivce")
public class RecommendService implements RecommendDAO {
	
	@Resource
	private SqlSessionTemplate sqlSessionTemplate;


	@Override
	public void addRecommend(Map<String, Object> map) {
		sqlSessionTemplate.insert("recommend.insert",map);
	}

	@Override
	public int recommendCheck(Map<String, Object> map) {
		return sqlSessionTemplate.selectOne("recommend.check", map);
	}

	//recommend board
	@Override
	public List<Map<String, Object>> recommendList(Map<String, Object> map) {

		List<Map<String, Object>> alllist = sqlSessionTemplate.selectList("recommend.alllist");
		List<Map<String, Object>> countlist = new ArrayList<Map<String, Object>>();

		int num = 0;
		//두개를 비교해서 member_id는 다르고 board_id가 똑같은 개수를 골라내서 개수가 가장 많은 애들을 찾는다aa
		for(int i=0;i<alllist.size();i++){
			for(Map.Entry<String, Object> entry : alllist.get(i).entrySet()){
				if(!String.valueOf(entry.getValue()).equals(String.valueOf(map.get("member_id")))){
					map.put("compare_member_id", entry.getValue());
					Map<String, Object> compare = sqlSessionTemplate.selectOne("recommend.similarity",map);
					countlist.add(num, compare);
					num++;
					}
				}
			}
		
		List<Map<String, Object>> recommend_list = new ArrayList<Map<String, Object>>();
		while(recommend_list.isEmpty()){
			if(countlist.isEmpty()){ return recommend_list; }
			
			Map<String, Object> compare = new HashMap<String, Object>();
			
			double similarity = 0;
			int del = 0;
			for(int i=0; i<countlist.size();i++){
				if(similarity != 0){
					if(similarity < Double.parseDouble(String.valueOf(countlist.get(i).get("SIMILARITY")))){
						similarity = Double.parseDouble(String.valueOf(countlist.get(i).get("SIMILARITY")));
						compare = countlist.get(i);
						del = i;
					}else{
						continue;
					}
				}else if(similarity == 0){ 
					similarity = Double.parseDouble(String.valueOf(countlist.get(i).get("SIMILARITY"))); 
					compare = countlist.get(i);
					del = i;
				}
			}
		//현재 회원의 추천 및 스크랩을 비교하여 취향이 가장 유사한 사람 (현재회원이 추천이나 스크랩안한 목록을 보여준다)
			map.put("compare_member_id", compare.get("MEMBER_ID"));
		//bgroup_id도 꺼내와야 함. scrap테이블에서도 갯수를 구해야함. 만약 유사도가 완전히 동일한 때는 그 다음 유사도를 가진 회원에게서 추천목록이 뽑히도록 로직 수정 요망
		//게시글 제목도 꺼내와야 한다!
			recommend_list = sqlSessionTemplate.selectList("recommend.recommendlist",map);
			if(recommend_list.isEmpty()){ countlist.remove(del); }
		}
		//ajax 작성할 때 list가 null이 아닐 때 << 조건문 꼭 작성
		return recommend_list;
	}
}
