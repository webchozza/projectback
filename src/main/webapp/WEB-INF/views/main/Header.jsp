<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<script src="${pageContext.request.contextPath}/resources/assets/js/main/header.js"></script>
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
	<header id="header">
		<a href="/dokky/main.do" style="max-width: 10%;"><h2>DOKKY</h2></a>
		<button name="bb" id="btn-mic" class="off"></button>
			<input type="hidden" id="micCheck" value="off"/>
			<span id="soundInput"></span>
			<a href="javascript:;" onclick="micOn()" id="micImg" class="icon fa-microphone-slash" style="width:50%; height:50%;">음성 검색</a>
	</header>
</body>
</html>