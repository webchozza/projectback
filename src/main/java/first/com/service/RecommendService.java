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
	public List<HashMap<String, Object>> recommendList(Map<String, Object> map) {

		//유사도 값을 담을 리스트 생성
		List<HashMap<String, Object>> countlist = new ArrayList<HashMap<String, Object>>();
		
		//추천 및 스크랩 테이블에 존재하는 회원들의 id값을 꺼내온다.
		List<HashMap<String, Object>> alllist = sqlSessionTemplate.selectList("recommend.alllist");

		int num = 0;
		for(int i=0;i<alllist.size();i++){
			for(HashMap.Entry<String, Object> entry : alllist.get(i).entrySet()){
				if(!String.valueOf(entry.getValue()).equals(String.valueOf(map.get("member_id")))){
					map.put("compare_member_id", entry.getValue());
					
					//위에서 꺼내온 id(접소중인 회원의 id는 제외)를 이용해 접속 중인 회원과의 (추천 및 스크랩 기준으로)취향을 비교하여 유사도를 구한다.
					HashMap<String, Object> compare = sqlSessionTemplate.selectOne("recommend.similarity",map);
					if(compare.containsKey("SIMILARITY")){
						countlist.add(num, compare);
						num++;
					}
					System.out.println(compare);
					}
				}
			}
		System.out.println(countlist);
		//추천 글을 담을 리스트 객체 생성
		List<HashMap<String, Object>> recommend_list = new ArrayList<HashMap<String, Object>>();
		
		//유사도가 가장 높은 회원에게서 추천 게시글을 뽑아오면 반복문 종료
		while(recommend_list.isEmpty()){
			
			//만약 더이상 유사도가 다른 회원이 존재하지 않는다면 빈 리스트 반환
			if(countlist.isEmpty()){ return recommend_list; }
			
			HashMap<String, Object> compare = new HashMap<String, Object>();
			
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
					System.out.println(similarity+"접속한 회원과 ");
					compare = countlist.get(i);
					System.out.println(compare+"콤페어");
					del = i;
				}
			}

			//현재 회원의 추천 및 스크랩을 비교하여 취향이 가장 유사한 사람 (현재회원이 추천이나 스크랩안한 목록을 보여준다)
			map.put("compare_member_id", compare.get("MEMBER_ID"));

			//코사인 유사도가 1에 가장 가까운 회원에게서 추천 글을 뽑아온다.
			recommend_list = sqlSessionTemplate.selectList("recommend.recommendlist",map);

			//만약 코사인 유사도값이 1이라면(추천, 스크랩한 게시글이 완전히 동일하다면(recommend_list에 담기는 정보가 없다.) 리스트에서 해당 유사도의 회원은 제거
			//즉, 다음 반복시 2번째로 높은 유사도를 가진 회원에게서 추천 글을 가져오기 위해서.
			if(recommend_list.isEmpty()){ countlist.remove(del); }
		}

		//뽑아온 추천 게시글 목록 혹은 (유사도가 전부 1이라면) 빈 리스트 객체 반환
		return recommend_list;
	}

	//검색시 검색어를 기준으로 유사도가 높은 추천 글 가져오기
	@Override
	public List<HashMap<String, Object>> recommendSearch(List<String> list) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search_list", list);
		
		List<HashMap<String, Object>> searchlist = sqlSessionTemplate.selectList("recommend.recommendsearch",map);
		System.out.println(searchlist);
		
		
		List similaritylist = new ArrayList();
		Map<String ,Object> putmap = null;
		for(int i=0;i<searchlist.size();i++){
			putmap = new HashMap<String, Object>();
			putmap.put("board_id", searchlist.get(i).get("BOARD_ID"));
			putmap.put("vector_name",searchlist.get(i).get("VECTOR_NAME"));
			putmap.put("vector_title", searchlist.get(i).get("VECTOR_TITLE"));
			putmap.put("vector_content", searchlist.get(i).get("VECTOR_CONTENT"));
			similaritylist.add(i, putmap);
		}
		map.put("similaritylist", similaritylist);
		
		List similaritylist2 = new ArrayList();
		Map<String ,Object> compare_putmap = null;
		for(int i=0;i<searchlist.size();i++){
			compare_putmap = new HashMap<String, Object>();
			if(i==(searchlist.size()-1)){
				compare_putmap.put("vector_name",searchlist.get(0).get("VECTOR_NAME"));
				compare_putmap.put("vector_title", searchlist.get(0).get("VECTOR_TITLE"));
				compare_putmap.put("vector_content", searchlist.get(0).get("VECTOR_CONTENT"));
			}else{
			compare_putmap.put("vector_name",searchlist.get(i+1).get("VECTOR_NAME"));
			compare_putmap.put("vector_title", searchlist.get(i+1).get("VECTOR_TITLE"));
			compare_putmap.put("vector_content", searchlist.get(i+1).get("VECTOR_CONTENT"));
			}
			similaritylist2.add(i, compare_putmap);
		}
		map.put("comparesimilaritylist", similaritylist2);
		
		List<HashMap<String, Object>> comparelist = sqlSessionTemplate.selectList("recommend.similaritysearch", map);
		System.out.println(comparelist);
		
		map.put("recosearchboard", comparelist);
		List<HashMap<String, Object>> recommendlist = sqlSessionTemplate.selectList("recommend.recosearchboard", map);
	System.out.println(recommendlist);
		return recommendlist;
	}
	
}
