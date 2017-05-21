<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE HTML>
<html>
<head>
<script src="${pageContext.request.contextPath}/resources/assets/js/main/sidemenu.js?v=2"></script>
<script>

$(document).ready(function(){
	$("#notich").val("N");
});

</script>
<title>사이드 메뉴</title>
<style>
.alram{ color: #3d4449; }
.alramon{ color: #f56a6a; }
.nb {
	-ms-overflow-style: none;
	border: solid 1px rgba(210, 215, 217, 0.75);
	background : #ffffff;
	border-color: #FF5A5A;
	width: 100%;
	height: 150px;
	overflow-x: hidden;
	overflow-y: auto;
	-webkit-border-radius:30px;
	-moz-border-radius:30px;
	padding-top: 1.0em;
	padding-bottom: 1.0em;
	padding-rigth: 1.2em;
	padding-left: 1.3em;
}
::-webkit-scrollbar {
display:none;
}

input[name=ss] {
display:none;
}

#sidebar>.inner>.alt {
	background-color: #eff1f2;
	border-bottom: 0;
	margin: -2.22222em 0 4.44444em -2.22222em;
	padding: 2.22222em;
	width: calc(100% + 3.8em);
}
.fa-envelope-open:before {
	content: "\f2b6"
	}
.fa-cog:before {
	content: "\f013" 
}


</style>

</head>
<body>
	<!-- 사이드바 -->
	<div id="sidebar">
		<div class="inner">
			<!-- 서치 -->
			<section id="search" class="alt">
			<center><a href="/dokky/main.do"><img src="/dokky/resources/images/logo3.png" width="140px"></a>
				</center>
				<br/>
				<form method="post" action="/dokky/AllSearchList.do" style="width:85%;">
					<input type="text" name="soundsearch" id="soundsearch" placeholder="Search All" />
					<input type="submit" name="ss" id="soundsubmit"/>
				</form>

			</section>

			<!-- 로그인 회원가입등 -->
			<c:if test="${sessionScope.member_email eq null}">
			<section id="icons">
				<ul>
				<a href="/dokky/loginform.do" class="icon fa-sign-in"> 로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;

				<a href="/dokky/joinform.do" class="icon fa-user-plus"> 회원가입</a>
						</ul>
			</section>
			</c:if>	
			
			
				<!-- 로그인 처리 -->
			<c:if test="${sessionScope.member_email ne null }">

			<section id="icons">
				<ul>
					<a href="/dokky/checkmodify.do?member_email=${sessionScope.member_email}" class="icon fa-cog" id="notice"> 회원정보</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="/dokky/massagelist.do" class="fa-envelope-open" id="notice"> 쪽지함</a>&nbsp;<br>
					<a href="/dokky/logout.do?member_id=${sessionScope.member_id}" class="icon fa-sign-out" style="color: #3d4449;"> 로그아웃</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:;" id="notibutton" class="icon fa-bell alram" id="noti" onclick="notipop()"> 알림</a>&nbsp;
					<div id="notiarea" style="display:inline-block;"></div>
					<input type="hidden" id="notich"/>
					
					<div id="notibox" ></div>
					
					<c:if test="${sessionScope.member_email eq 'admin@'}">
					<br/>
					<a href="javascript:;" onclick="movememberlist()" class="icon fa-cogs">관리자 페이지</a>
					</c:if>
					
				</ul>
			</section>
				<div style="padding-left: 5%;">안녕하세요! <a href="/dokky/MemberPage.do?member_id=${sessionScope.member_id}">${sessionScope.member_name}</a>님</div>
			</c:if>				
			
			<!-- 메뉴 -->
			<nav id="menu">
				<ul>
					<li><a href="index.html">메인</a></li>
					<li><a href="/dokky/bqnalist.do">Q&A게시판</a></li>
					<li><span class="opener">구인구직</span>
						<ul>
							<li><a href="#">구인</a></li>
							<li><a href="#">구직</a></li>
						</ul></li>
					<li><a href="/dokky/bfreelist.do">자유게시판</a></li>
					<li><a href="/dokky/bcodelist.do">오픈소스</a></li>
				</ul>
			</nav>
		</div>
	</div>
</body>
<form name="valueform">
<input type="hidden" id="session_id" value="${sessionScope.member_id}"/>
</form>
</html>