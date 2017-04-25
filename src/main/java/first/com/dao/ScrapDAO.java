package first.com.dao;

import first.com.model.MemberDTO;
import first.com.model.ScrapDTO;

public interface ScrapDAO {
	
	public ScrapDTO scrapList(MemberDTO md);
	
	public String deleteScrap();

}
