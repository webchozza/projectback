package first.com.service;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import first.com.dao.TagDAO;
import first.com.model.BoardDTO;
import first.com.model.TagDTO;

@Service
public class TagService implements TagDAO{
	
	@Resource
	private SqlSessionTemplate SqlSessionTemplate;
	
	@Override
	public List<BoardDTO> tagList(TagDTO tagDTO) {
		return SqlSessionTemplate.selectList("main.allSearchTag", tagDTO);
	}

	public String insertTag(String tag,int bgroup_id){
		
		if(tag==null)
			tag="";
		
		String taglist = tag.replaceAll(" ", "");
				
		while (taglist.contains(",,")) {
			taglist = taglist.replaceAll(",,", ",");
		}
		if (taglist==null|| taglist.trim().isEmpty()||taglist.trim().equals(",")) {
			taglist = "";
		} else {
			if (taglist.charAt(taglist.length() - 1) == ',') {
				taglist=taglist.substring(0, taglist.length() - 1);
			}
			if (taglist.charAt(0) == ',') {
				taglist=taglist.substring(1, taglist.length());
			}
		}
		
		if (bgroup_id==1) {
			if(taglist==""||taglist.equals("오픈소스"))
				taglist="오픈소스";
			else
				taglist="오픈소스," + taglist;
		}
		else if (bgroup_id==2) {
			if(taglist==""||taglist.equals("Community"))
				taglist="Community";
			else
				taglist="Community," + taglist;
		}
		else if (bgroup_id==3) {
			if(taglist==""||taglist.equals("구인·구직"))
				taglist="구인·구직";
			else
				taglist="구인·구직," + taglist;
		}
		else if (bgroup_id==4) {
			if(taglist==""||taglist.equals("Q&A"))
				taglist="Q&A";
			else
				taglist="Q&A," + taglist;
		}
/*		else if (bgroup_id==5) {
			if(taglist==""||taglist.equals("구직"))
				taglist="구직";
			else
				taglist="구직," + tag;
		}*/
		
		return taglist;
	}
	
	public String modifyFormView(String tag,int bgroup_id){

		if(tag==null)
			tag="";

		String taglist= tag;

		if (bgroup_id==1) {
			taglist = taglist.replaceAll("오픈소스,", "");
			if (taglist.equals("오픈소스")) {
				taglist="";
			}
		}
		else if (bgroup_id==2) {
			taglist = taglist.replaceAll("Community,", "");
			if (taglist.equals("Community")) {
				taglist="";
			}
		}
		else if (bgroup_id==3) {
			taglist = taglist.replaceAll("구인·구직,", "");
			if (taglist.equals("구인·구직")) {
				taglist="";
			}
		}
		else if (bgroup_id==4) {
			taglist = taglist.replaceAll("Q&A,", "");
			if (taglist.equals("Q&A")) {
				taglist="";
			}
		}		
		return taglist;
	}
	
}
