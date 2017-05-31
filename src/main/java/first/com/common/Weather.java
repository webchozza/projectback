package first.com.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import first.com.dao.WeatherDAO;

@Controller
public class Weather {
	
	@Autowired
	private WeatherDAO weather;
	
	@RequestMapping("/addWeatherCount.do")
	@ResponseBody
	public void addCount(@RequestParam(value="count") int count){
		weather.addCount(count);
	}
	
	@RequestMapping("/selectWeatherCount.do")
	@ResponseBody
	public int selectCount(){
		return weather.selectCount();
	}

}
