$(document).ready(function(){

	var url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
	var url2 = "&APPID=4337d8683878691978c865a2c5127fe8";
	
	
	$.ajax({
		url:"selectWeatherCount.do",
		type: "post",
		dataType:"json",
		success: function(data){
			var count = data;
			if(count == 7 || count > 7){count = 0; }
			if(count == 0){weather(url1+"Seoul"+url2);}
			if(count == 1){weather(url1+"Incheon"+url2);}
			if(count == 2){weather(url1+"Busan"+url2);}
			if(count == 3){weather(url1+"Daejeon"+url2);}
			if(count == 4){weather(url1+"Daegu"+url2);}
			if(count == 5){weather(url1+"Ulsan"+url2);}
			if(count == 6){weather(url1+"Jeju"+url2);}
			
			interval(count);
		}
	});
});

function interval(count){
	
	var url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
	var url2 = "&APPID=4337d8683878691978c865a2c5127fe8";
	
	setInterval(function(){
		if(count == 7 || count > 7 ){ count = 0; }
		if(count == 0){ var region = "Seoul";  }
		if(count == 1){ var region = "Incheon"; }
		if(count == 2){ var region = "Busan";  }
		if(count == 3){ var region = "Daejeon";  }
		if(count == 4){ var region = "Daegu";  }
		if(count == 5){ var region = "Ulsan; "; }
		if(count == 6){ var region = "Jeju; "; }
		count++;

		var url = url1+region+url2;

		$.ajax({
			url:"addWeatherCount.do",
			type:"post",
			data:{count:count}
		});
		
		weather(url);
	},5000);
}

function weather(url){
	$.ajax({
		url: url,
		dataType: "json",
		success: function(data){
			var name = data.name;
			var temp_max = Math.round(data.main.temp_max - 273.15);
			var temp_min = Math.round(data.main.temp_min - 273.15);
			var humidity = data.main.humidity;
			var str = weatherHtml(data.weather[0].main, name, temp_max, temp_min, humidity);
			
			$("#weather").html(str);
		}
		
	});
	}
	
	function weatherHtml(main, name, temp_max, temp_min, humidity){
		if(name == "Seoul"){ var n = "서울"; }
		if(name == "Incheon"){ var n = "인천"; }
		if(name == "Busan"){ var n = "부산"; }
		if(name == "Daejeon"){ var n = "대전"; }
		if(name == "Daegu"){ var n = "대구"; }
		if(name == "Ulsan"){ var n = "울산"; }
		if(name.indexOf("Cheju") != -1 || name.indexOf("Jeju") != -1){ var n = "제주"; }
		
		if(main == "Haze" || main == "Mist"){
			var main = '<img src="resources/images/Haze.jpg" style="width:65px;">';
		}else if(main == "Clear"){
			var main = '<img src="resources/images/Clear.jpg" style="width:65px;">';
		}else if(main == "Clouds"){
			var main = '<img src="resources/images/Clouds.jpg" style="width:65px;">';
		}else if(main == "Rain"){
			var main = '<img src="resources/images/Rain.jpg" style="width:65px;">';
		}else if(main == "Thunderstorm"){
			var main = '<img src="resources/images/thunder.png" style="width:65px;">';
		}else if(main == "Drizzle"){
			var main = '<img src="resources/images/drizzle.png" style="width:60px;">';
		}

		var str = '<div style="display: inline-block;"><b style="font-size:25px;">&nbsp;'+n+'</b><br>'+main+'</div>'+'<div style="display: inline-block;"><img src="resources/images/Humidity.jpg" style="width:10px;"><b>  '+humidity+'</b>%<br>';
		str += '<img src="resources/images/redthermometer.jpg" style="width:10px; height=3px;"><b> '+temp_max+'</b>℃<br>';
		str += '<img src="resources/images/bluethermometer.jpg" style="width:10px; height=3px;"><b> '+temp_min+'</b>℃</div>';

		return str;
	}