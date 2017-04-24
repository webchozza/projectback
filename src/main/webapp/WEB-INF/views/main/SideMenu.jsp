<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>사이드 메뉴</title>
<script>
	var auto_refresh = setTimeout(function() {
		$('#noticearea').load('요청 url');//url에 알람목록불러오는 컨트롤러의 RequestMapping url 삽입, 받은 응답을 id가 noticearea인 영역에 보여준다
		setTimeout(auto_refresh, 3000);//재귀 함수(3초후에 콜백함수 실행, 콜백함수내부에 재귀함수로 인해 다시 3초 후에 동일 함수 실행(반복))
	}, 3000);
</script>
<style>
</style>
</head>
<body>
	<!-- 사이드바 -->
	<div id="sidebar">
		<div class="inner">

			<!-- 서치 -->
			<section id="search" class="alt">
				<form method="post" action="#">
					<input type="text" name="query" id="query" placeholder="Search" />
				</form>
			</section>

			<!-- 로그인 회원가입등 -->
			<section id="icons">
				<a href="#" class="icon fa-sign-in"> 로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="icon fa-user-plus"> 회원가입</a>
				<!-- 로그인 처리 -->
				<ul>
					<br /> 로그인했으면
					<a href="#" class="icon fa-sign-out"> 로그아웃</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="icon fa-bell" id="notice"> 알림</a>
					
					<br />
					<br /> 관리자이면
					<a href="/dokky/ml.do" class="icon fa-cogs">관리자 페이지</a>
				</ul>
			</section>

			<!-- 메뉴 -->
			<nav id="menu">
				<ul>
					<li><a href="index.html">메인</a></li>
					<li><a href="generic.html">Q&A게시판</a></li>
					<li><span class="opener">구인구직</span>
						<ul>
							<li><a href="#">구인</a></li>
							<li><a href="#">구직</a></li>
						</ul></li>
					<li><a href="elements.html">자유게시판</a></li>
					<li><a href="elements.html">오픈소스</a></li>
					<li><a href="/dokky/soundtest.do">음성인식 검색 테스트</a></li>
				</ul>
			</nav>
		</div>
	</div>
</body>
</html>