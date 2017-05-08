<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<title>CheckModify</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
</head>


<body>
	<br />
	<br />
	<div id="wrapper">
		<div id="main">
			<div class="inner">
				<h3>회원정보</h3>
				
				<a href="/dokky/checkmodify.do?member_email=${sessionScope.member_email}"id="notice"> 정보수정</a> 
				
				<a href="/dokky/checkmodify.do?member_email=${sessionScope.member_email}" class="icon fa-cog" id="notice"> 탈퇴</a> 	
					</div>
			</div>
			
		</div>
</body>
</html>