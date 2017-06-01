package first.com.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import first.com.dao.WeatherDAO;

@Service
public class WeatherService implements WeatherDAO {
	
	private Map<String, Integer> weathercount = new HashMap<String, Integer>();

	@Override
	public void addCount(int count) {
		weathercount.remove("weathercount");
		weathercount.put("weathercount", count);
	}

	@Override
	public int selectCount() {
		if(weathercount != null && !weathercount.isEmpty()){
				return weathercount.get("weathercount");
		}
		return 0;
	}

	
}
