<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script>
	$(document).ready(
			function() {
				$(window).scroll(
						function() {
							$(this).scrollTop() > 185 ? $(".float_sidebar")
									.fadeIn() : $(".float_sidebar").fadeOut()
						})
			});
</script>
</head>
<body>
	<style>
#page-wrapper {
width: 180px;
	float: right;
	padding-left: 250px;
}

#sidebar-wrapper {
	display: none;
	float: right;
	position: fixed;
	width: 220px;
	height: 375px;
	background: #f37720;
	overflow-x: hidden;
	overflow-y: auto;
	-webkit-border-radius:30px;
	-moz-border-radius:30px;

}

#page-content-wrapper {
	width: 180px;
	padding: 20px;
}
/* 사이드바 스타일 */
.sidebar-nav {
	width: 220px;
	margin: 0;
	padding: 0;
	list-style: none;
}

.sidebar-nav li {
	text-indent: 1.5em;
	line-height: 60px;
}

.sidebar-nav li a {
	display: block;
	text-decoration: none;
	font-size: 1.2em;
	color: #FFF;
}

.sidebar-nav li a:hover {
	color: #fff;
}

.sidebar-nav>.sidebar-brand {
	font-size: 1.3em;
	line-height: 3em;
}
</style>
	<div id="page-wrapper">
		<!-- 사이드바 -->
		<div id="sidebar-wrapper" class="float_sidebar">
			<ul class="sidebar-nav">
				<li class="sidebar-brand">
				<li><a href="ListItem.action">여행 패키지</a></li>
				<li><a href="ListAir.action">항공권 </a></li>
				<li><a href="ListHotel.action">호텔/리조트</a></li>
				<li><a href="ListReview.action">후기 보러가기</a></li>
				<li><a href="MyPageForm.action">마이페이지</a></li>
				<li><a href="ServiceForm.action">고객센터</a></li>
			</ul>
		</div>
		<!-- /사이드바 -->

		<!-- 본문 -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<h1></h1>
				<p></p>
			</div>
		</div>
		<!-- /본문 -->
	</div>
</body>
</html>