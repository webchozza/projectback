<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<script src="${pageContext.request.contextPath}/resources/assets/js/main/header.js"></script>
<script src="https://code.jquery.com/jquery-2.2.1.js"></script>
<script>
$(document).ready(function(){
	
	var count = 1;

	var url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
	var url2 = "&APPID=4337d8683878691978c865a2c5127fe8";
	
	weather(url1+"Seoul"+url2);
	
	setInterval(function(){
		if(count == 7){ count = 0; }
		if(count == 0){ var region = "Seoul";  }
		if(count == 1){ var region = "Incheon"; }
		if(count == 2){ var region = "Busan";  }
		if(count == 3){ var region = "Daejeon";  }
		if(count == 4){ var region = "Daegu";  }
		if(count == 5){ var region = "Ulsan; "}
		if(count == 6){ var region = "Jeju; "}
		count++;
		console.log(region);
		var url = url1+region+url2;
		console.log(url);
		weather(url);
	},5000);
});

function weather(url){
	$.ajax({
		url: url,
		dataType: "json",
		success: function(data){
			var name = data.name;
			var temp_max = Math.round(data.main.temp_max - 273.15);
			var temp_min = Math.round(data.main.temp_min - 273.15);
			var humidity = data.main.humidity;
			if(data.weather[0].main == "Haze"){
				$("#weather").html("<h2>안개</h2><br>"+name+"<br>최고 기온: "+temp_max+"<br>최저 기온: "+temp_min+"<br>습도: "+humidity);
			}else if(data.weather[0].main == "Clear"){
				$("#weather").html("<h2>맑음</h2><br>"+name+"<br>최고 기온: "+temp_max+"<br>최저 기온: "+temp_min+"<br>습도: "+humidity);
			}else if(data.weather[0].main == "Clouds"){
				$("#weather").html("<h2>흐림</h2><br>"+name+"<br>최고 기온:"+temp_max+"<br>최저 기온:"+temp_min+"<br>습도: "+humidity);
			}else if(data.weather[0].main == "Rain"){
				$("#weather").html("<h2>우천</h2><br>"+name+"<br>최고 기온:"+temp_max+"<br>최저 기온:"+temp_min+"<br>습도: "+humidity);
			}
		}
		
	});
	}
</script>


<title>Header</title>
<style>
.fa-microphone{
text-align: right;
font-size: 30px;
}

.fa-microphone-slash{
text-align: right;
font-size: 30px;
}

button[name=bb]{
display:none;
}

input[name=ss] {
display:none;
}
</style>
</head>
<body>
	<!-- 헤더 (윗부분)-->
	<div style="text-align:right;" id="weather">
	</div>
	<header id="header">
		<a href="/dokky/main.do" style="max-width: 10%;"><h2>DOKKY</h2></a>
		<button name="bb" id="btn-mic" class="off"></button>
			<input type="hidden" id="micCheck" value="off"/>
			<span id="soundInput"></span>
			<a href="javascript:;" onclick="micOn()" id="micImg" class="icon fa-microphone-slash" style="max-width:50%; height:50%; font-size:2em;">음성 검색</a>
	</header>
</body>
</html>