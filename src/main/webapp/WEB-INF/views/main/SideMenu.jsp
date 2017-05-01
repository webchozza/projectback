<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE HTML>
<html>
<head>
<script src="https://code.jquery.com/jquery-2.2.1.js"></script>
<title>사이드 메뉴</title>
<!--  
class="notibox"
<ul>
<li>회원님의 <a href="">게시글</a>에 댓글이 달렸습니다.</li>
</ul> -->
<script type="text/javascript">
$(document).ready(function(){
	$("#notich").val("N");
});

function notipop(){
	
	var ch = $("#notich").val();
	var session_id = 1;
	
	if(ch == "N"){
		$.ajax({
			url: "/dokky/notilist.do",
			dataType: "json",
			cache: false,
			data: {session_id: session_id},
			success: function(data){
				$("#notibox").html("");
				$("#notibox").append('<div class="notibox"><div>');
				$(".notibox").addClass("nb");
				$("#notich").val("Y");
				
				//noti
				var str = "<ul> \n";
				
				//comment
				var pren = "회원님의 ";
				var postn = "글에 댓글이 등록되었습니다.";
				
				//follow => new board
				var pref = " 님이 새 ";
				var postf = "을 등록하셨습니다.";
			
				//follow
				$.each(data, function(key,value){
					if(value.noti_kinds == "comment"){
						alert('1');
					str += "<li>"+pren+"<a href='"+value.list.noti_url+"'>"+value.list.noti_subject+"</a>"+postn+"</li>\n"; 
					} else if(value.noti == null) {
					str += "<li>"+value.noti_id+pref+"<a href='"+value.noti_url+"'>게시글</a>"+postf+"</li>\n"; 
					}
					
				});
				str += "</ul>";
				
				$(".notibox").html(str);
			},
			error: function(data){
				var e = data.status;
				alert(e);
			},
			
			});
		}
	
	if(ch == "Y"){
		$("#notibox").html("");
		$("#notich").val("N");
	}
}


</script>
<style>
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
	padding-right: 50px;
}

</style>
</head>
<body>
	<!-- 사이드바 -->
	<div id="sidebar">
		<div class="inner">
			<!-- 서치 -->
			<section id="search" class="alt">
				<form method="post" action="/dokky/AllSearchList.do" style="width:85%;">
					<input type="text" name="soundsearch" id="soundsearch" placeholder="Search All" />
					<input type="submit" name="ss" id="soundsubmit"/>
				</form>
			</section>
			<c:if test="${sessionScope.member_email eq null}">
			<!-- 로그인 회원가입등 -->
			<section id="icons">
				<a href="/dokky/loginform.do" class="icon fa-sign-in"> 로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="/dokky/joinform.do" class="icon fa-user-plus"> 회원가입</a>
			
				<!-- 로그인 처리 -->
			</c:if>	
				<ul>
			<c:if test="${sessionScope.member_email ne null}">
					<a href="/dokky/logout.do" class="icon fa-sign-out"> 로그아웃</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:;" class="icon fa-bell" id="noti" onclick="notipop()"> 알림</a>
					<input type="hidden" id="notich"/>
					<div id="notibox" ></div>
			</c:if>				
					<c:if test="${sessionScope.member_email}=='admin'">
					<br />
					<a href="/dokky/MemberList.do" class="icon fa-cogs">관리자 페이지</a>
					안녕하세요! 
					${sessionScope.member_name}님
					<br />
					</c:if>
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
				</ul>
			</nav>
		</div>
	</div>
	
	
</body>
</html>