<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE HTML>
<html>
<head>
<script src="https://code.jquery.com/jquery-2.2.1.js"></script>
<title>사이드 메뉴</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#notich").val("N");
});

function notipop(){
	
	var ch = $("#notich").val();
	var session_id = $("#session_id").val();
	
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
				$("#notibutton").attr("class","icon fa-bell alramon");
				$("#notich").val("Y");
				
				var str = "<ul> \n";
				
				$.each(data, function(key,value){
					
					var deletestr = "<a href='javascript:;' onclick='return notidelete("+value.noti_id+")'>";
				    deletestr += "<img src=\"/dokky/resources/images/x.jpg\" style=\"width: 5%; height: 5%;\"/></a>";
						
					if(value.noti_kinds == "comment"){
					str += "<li>회원님의 글 <a href='"+value.noti_url+"&session_id=${sessionScope.member_id}'>"+value.noti_subject;
					str += "</a>에 댓글이 등록되었습니다&nbsp;"+deletestr+"</li>\n"; 
					} else if(value.noti_kinds == "follow_NewBoard") {
					str += "<li><a href='/dokky/MemberPage.do?member_id="+value.sender_id+"&session_id=${sessionScope.member_id}'>"+value.sender_name;
					str += "</a>님이 새로운 <a href='"+value.noti_url+"&session_id=${sessionScope.member_id}'>글</a>을 작성했습니다&nbsp;"+deletestr+"</li>\n"; 
					} else if(value.noti_kinds == "follow_comment"){
					str += "<li><a href='/dokky/MemberPage.do?member_id="+value.sender_id+"&session_id=${sessionScope.member_id}'>"+value.sender_name;
					str += "</a>님이 새로운 <a href='"+value.noti_url+"&session_id=${sessionScope.member_id}'>댓글</a>을 등록했습니다&nbsp;"+deletestr+"</li>\n";
					}
				});
				str += "</ul>";
				
				$(".notibox").html(str);
			}
		});
	}
	
	if(ch == "Y"){
		$("#notibutton").attr("class","icon fa-bell alram");
		$("#notibox").html("");
		$("#notich").val("N");
	}
}

function notidelete(noti_id){
	
	if(!confirm("알림을 삭제하시겠습니까?")){ return false; }
	$.ajax({
		url:"/dokky/notiDelete.do",
		type: "post",
		data: {noti_id: noti_id},
		success: function(){
			$("#notich").val("N");
			notipop();
		}
	});
}

setInterval(function(){
	$.ajax({
		url: "/dokky/notiCount.do",
		type: "post",
		dataType: "json",
		data: {session_id : '${sessionScope.member_id}'},
		success: function(data){
			if(data == 0){
				$("#notiarea").html("");
			}else{
			var str = '<span class="fa fa-plus" style="color: #f56a6a;">&nbsp;'+data+'</span>';
			$("#notiarea").html("");
			$("#notiarea").html(str);
			}
		}
	});
}, 3000);
</script>
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
			<c:if test="${sessionScope.member_email ne null}">
			<section id="icons">
				<ul>
					<a href="/dokky/logout.do?member_id=${sessionScope.member_id}" class="icon fa-sign-out" style="color: #3d4449;"> 로그아웃</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					
					<a href="javascript:;" id="notibutton" class="icon fa-bell alram" id="noti" onclick="notipop()"> 알림</a>&nbsp;
					<div id="notiarea" style="display:inline-block;"></div>
					<input type="hidden" id="notich"/>
					
					<div id="notibox" ></div>
					
					<c:if test="${sessionScope.member_email}=='admin'">
					<a href="/dokky/MemberList.do" class="icon fa-cogs">관리자 페이지</a>
					</c:if>
					
				</ul>
			</section>
				<div style="padding-left: 5%;">안녕하세요! <a href="/dokky/MemberPage.do?member_id=${sessionScope.member_id}">${sessionScope.member_name}</a>님</div>
			</c:if>				
			
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
					<li><a href="/dokky/bfreelist.do">자유게시판</a></li>
					<li><a href="elements.html">오픈소스</a></li>
				</ul>
			</nav>
		</div>
	</div>
</body>
<form name="valueform">
<input type="hidden" id="session_id" value="${sessionScope.member_id}"/>
</form>
</html>