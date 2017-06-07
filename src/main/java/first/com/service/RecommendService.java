package first.com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.RecommendDAO;
import first.com.model.BoardDTO;
import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;
import kr.co.shineware.util.common.model.Pair;

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

		//���絵 ���� ���� ����Ʈ ����
		List<HashMap<String, Object>> countlist = new ArrayList<HashMap<String, Object>>();
		
		//��õ �� ��ũ�� ���̺� �����ϴ� ȸ������ id���� �����´�.
		List<HashMap<String, Object>> alllist = sqlSessionTemplate.selectList("recommend.alllist");

		int num = 0;
		for(int i=0;i<alllist.size();i++){
			for(HashMap.Entry<String, Object> entry : alllist.get(i).entrySet()){
				if(!String.valueOf(entry.getValue()).equals(String.valueOf(map.get("member_id")))){
					map.put("compare_member_id", entry.getValue());
					
					//������ ������ id(�������� ȸ���� id�� ����)�� �̿��� ���� ���� ȸ������ (��õ �� ��ũ�� ��������)������ ���Ͽ� ���絵�� ���Ѵ�.
					HashMap<String, Object> compare = sqlSessionTemplate.selectOne("recommend.similarity",map);
					if(compare.containsKey("SIMILARITY")){
						countlist.add(num, compare);
						num++;
					}
				}
			}
		}

		//��õ ���� ���� ����Ʈ ��ü ����
		List<HashMap<String, Object>> recommend_list = new ArrayList<HashMap<String, Object>>();
		
		//���絵�� ���� ���� ȸ�����Լ� ��õ �Խñ��� �̾ƿ��� �ݺ��� ����
		while(recommend_list.isEmpty()){
			
			//���� ���̻� ���絵�� �ٸ� ȸ���� �������� �ʴ´ٸ� �� ����Ʈ ��ȯ
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
					compare = countlist.get(i);
					del = i;
				}
			}

			//���� ȸ���� ��õ �� ��ũ���� ���Ͽ� ������ ���� ������ ��� (����ȸ���� ��õ�̳� ��ũ������ ����� �����ش�)
			map.put("compare_member_id", compare.get("MEMBER_ID"));

			//�ڻ��� ���絵�� 1�� ���� ����� ȸ�����Լ� ��õ ���� �̾ƿ´�.
			recommend_list = sqlSessionTemplate.selectList("recommend.recommendlist",map);

			//���� �ڻ��� ���絵���� 1�̶��(��õ, ��ũ���� �Խñ��� ������ �����ϴٸ�(recommend_list�� ���� ������ ����.) ����Ʈ���� �ش� ���絵�� ȸ���� ����
			//��, ���� �ݺ��� 2��°�� ���� ���絵�� ���� ȸ�����Լ� ��õ ���� �������� ���ؼ�.
			if(recommend_list.isEmpty()){ countlist.remove(del); }
		}

		//�̾ƿ� ��õ �Խñ� ��� Ȥ�� (���絵�� ���� 1�̶��) �� ����Ʈ ��ü ��ȯ
		return recommend_list;
	}

	//�˻��� �˻�� �������� ���絵�� ���� ��õ �� ��������
	@Override
	public List<HashMap<String, Object>> recommendSearch(List<String> list) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search_list", list);

		//�˻�� ���� �� �Խñ��� �󵵼��� ���ͷ� ���� ���� ����Ʈ
		List<HashMap<String, Object>> searchlist = sqlSessionTemplate.selectList("recommend.recommendsearch",map);
		//�� �Խñ��� ���Ͽ� ���絵�� ���� ������ board_id 6���� ������ ����Ʈ
		List<HashMap<String, Object>> comparelist = new ArrayList<HashMap<String, Object>>();
		//�� ����Ʈ�� board_id�� �信�� ������ �������� ������ ���� ����Ʈ
		List<HashMap<String, Object>> recommendlist = new ArrayList<HashMap<String, Object>>();

		map.put("searchlist", searchlist);

		if(!searchlist.isEmpty()){
		comparelist = sqlSessionTemplate.selectList("recommend.similaritysearch", map);
		}

		if(comparelist.isEmpty()){
			recommendlist = sqlSessionTemplate.selectList("recommend.basiclist", map);
		}else if(!comparelist.isEmpty()){
			map.put("recosearchboard", comparelist);
			recommendlist = sqlSessionTemplate.selectList("recommend.recosearchboard", map);
		}

		return recommendlist;
	}

	@Override
	public List<HashMap<String, Object>> SimilarBoard(Map<String, Object> map) {
		
		BoardDTO board = sqlSessionTemplate.selectOne("recommend.selectboard",map);

		//���¼� �м��� ��ü ����
		Komoran komoran = new Komoran("C:\\komoran\\models-full");
		//���絵 ��꿡 ����� ���Ͱ��� ������ ����Ʈ
		List<HashMap<String, Object>> similarlist = new ArrayList<HashMap<String, Object>>();
		//���絵 ��� �� ���絵 ���� ������ 6���� �Խñ� ������ ���� ����Ʈ
		List<HashMap<String, Object>> similarboardlist = new ArrayList<HashMap<String, Object>>();

		String str = board.getBoard_content()+board.getBoard_title();
		
		str = str.replaceAll("p", "").replaceAll(",", "").replaceAll("=", "").replaceAll(";", "").replaceAll("font", "")
				.replaceAll("\\&", "").replaceAll("span", "").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("[0-9]", "")
				.replaceAll("nbsp", "").replaceAll(":", "").replaceAll("==", "").replaceAll("\\+", "").replaceAll("\"", "")
				.replaceAll("\\<", "").replaceAll("\\>", "").replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("\\*", "")
				.replaceAll("\\-", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("margin", "").replaceAll("bottom", "")
				.replaceAll("rgb", "").replaceAll("style", "").replaceAll("box", "").replaceAll("color", "").replaceAll("height", "")
				.replaceAll("width", "").replaceAll("background", "").replaceAll("gothic", "").replaceAll("border", "").replaceAll("\\?", "")
				.replaceAll("255", "").replaceAll("size", "").replaceAll("sizing", "").replaceAll("line", "").replaceAll("/", "")
				.replaceAll("arial", "").replaceAll("img","").replaceAll("src", "").replaceAll("\\!", "").replaceAll("\\~", "").replaceAll("\\?", "");

		List<List<Pair<String, String>>> result = komoran.analyzeWithoutSpace(str);
		List<String> list = new ArrayList<String>();
		int num = 0;
		for(List<Pair<String, String>> repeat : result ){
			for(int i=0; i<repeat.size();i++){
				if(repeat.get(i).getSecond().equals("MAG")||repeat.get(i).getSecond().equals("NNG")
						||repeat.get(i).getSecond().equals("NNP")){
					if(repeat.get(i).getFirst().length() != 1){
						System.out.println(repeat.get(i));
						list.add(num, repeat.get(i).getFirst());
						num++;
					}
				}
			}
		}
		//������ ���¼Ҹ� ����Ʈ���·� �ʰ�ü�� ����
		map.put("search_list", list);
		//���¼Ҹ� �����ϴ� �Խñ��� ���絵 ������ ����� ���Ͱ��� ���Ѵ�
		if(!list.isEmpty()){
			similarlist = sqlSessionTemplate.selectList("recommend.recommendview",map);
		}

		if(!similarlist.isEmpty()){
		map.put("similarboardlist", similarlist);
			similarboardlist = sqlSessionTemplate.selectList("recommend.similarboard", map);
		}
		
		System.out.println("��� �Ϸ�");
		return similarboardlist;
	}
}
