<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="${pageContext.request.contextPath}/resources/assets/js/memberpage/memberpage.js"></script>
<title>회원 정보 보기</title>
<style>
#b{
  color: #3d4449;
  text-decoration:none !important
}
</style>
<script>
//페이지가 로딩될 때 함수실행(팔로우한 사람인지 아닌지 구분하는 값을 인자로 넘겨줌)
$(document).ready(function(){
	followcheck("${followCheck}");
});
</script>
</head>
<body>
	<h4>회원 정보</h4>
	
	<span class="image fit"><h2><b>${followCount.member_name}</b></h2></span>
	<div class="box alt">
		<div class="row 50% uniform" style="background-color: #f5f6f7; width:60%; display: inline-block;">
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center;"><h3 class="icon fa-hand-paper-o" style="color: #f56a6a;"><b>팔로잉</b></h3></span>
			</div>
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center;"><h3 class="icon fa-hand-o-up" style="color: #f56a6a;"><b>팔로워</b></h3></span>
			</div>
			<!-- Break -->
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center; font-size: 30px;">${followCount.following_count}</span>
			</div>
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center; font-size: 30px;">${followCount.follower_count}</span>
			</div>
		</div>
		<div class="row 100% uniform" style="background-color: #f5f6f7; width:30%; display: inline-block; max-width:30%; max-height:30%;">
		<div class="4u" style="width: 90%;">
		<div id="me" style="height: 93px;"><span id="plusFollow" class="image fit fa fa-user-plus" style="text-align: center; font-size: 25px; color: #f56a6a;">
		<a href="javascript:;" onclick='followclick("add")'><h4 style="color: #f56a6a;">+팔로우</h4></a></span></div>
		</div>
		</div>
	</div>
	<div id="movearea">
	<div class="table-wrapper">
	<c:if test="${myCount eq null}">
	<div style="max-width: 15%; text-align:center; display: inline-block;" id="boardarea"><h3 style="color: #f56a6a;">게시물</h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">0</div>
	<div style="max-width: 15%; text-align:center; display: inline-block;" id="scraparea"><h3><a href="javascript:;" onclick="scrap()">스크랩</a></h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">0</div>
	<div style="max-width: 15%; text-align:center; display: inline-block;" id="scraparea"><h3><a href="javascript:;" id="a" onclick="follow()">팔로우</a></h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">0</div>
	</c:if>
	<c:if test="${myCount ne null}">
	<div style="max-width: 15%; text-align:center; display: inline-block;" id="boardarea"><h3 style="color: #f56a6a;">게시물</h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">${myCount.MYBOARDCOUNT}</div>
	<div style="max-width: 15%; text-align:center; display: inline-block;" id="scraparea"><h3><a href="javascript:;" onclick="scrap()">스크랩</a></h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">${myCount.MYSCRAPCOUNT}</div>
	<div style="max-width: 15%; text-align:center; display: inline-block;" id="scraparea"><h3><a href="javascript:;" id="a" onclick="follow()">팔로우</a></h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">${myCount.MYFOLLOWCOUNT}</div>
	</c:if>
	<hr style="width: 70%; align: left;">
		<table>
					<thead>
						<tr>
						<td><section id="search" class="alt" style="width: 400px">
					<form action="javascript:;" method="post" id="searchform" >
					<input type="hidden" name="i" id="i" value="${i}"/>
						<input type="text" name="search" id="search" placeholder="Search"/>
							<input type="submit" name="ss" onclick="sch()"/>
					</form>
				</section></td>
						</tr>
					</thead>
			<tbody>
			<c:forEach var="board" items="${list}">
			<c:if test="${board.bgroup_id eq 1}">
				<c:url var="detailurl" value="#"/><!-- qna -->
			</c:if>
			<c:if test="${board.bgroup_id eq 2}">
				<c:url var="detailurl" value="/bfreedetail.do">
					<c:param name="board_id" value="${board.board_id }"/>
					<c:param name="currentPage" value="1"/>
		    		<c:param name="session_id" value="${sessionScope.member_id}"/>
				</c:url>
			</c:if>
			<c:if test="${board.bgroup_id eq 3 || board.bgroup_id eq 4}">
				<c:url var="detailurl" value="#"/><!-- 구인구직 -->
			</c:if>
			<c:if test="${board.bgroup_id eq 5}">
				<c:url var="detailurl" value="#"/><!-- 오픈소스 -->
			</c:if>
			<tr>
					<td width="30%"><a href="${detailurl}" id="b">${board.board_title}</a></td>
					<td width="10%"><a class="icon fa-comment">${board.board_comment_count}</a>&nbsp;&nbsp;&nbsp; 
					<a class="icon fa-thumbs-up">${board.board_like}</a>&nbsp;&nbsp;&nbsp; 
					<a class="icon fa-eye">${board.board_hit}</a></td>
	 				<td width="10%" align="center"><div id="b">${board.member_name}</div></td>
					<td width="5%" align="center"><fmt:formatDate value="${board.board_date}" pattern="yyyy.MM.dd" /></td>
					<td width="5%">
					<c:if test="${board.member_id eq sessionScope.member_id}">
					<a href="javascript:;" onclick='return deleteMyBoard("${board.board_id}")'><img src="/dokky/resources/images/x.jpg" style="width: 20%; height: 5%;"/></a>
					</c:if>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
			</div>
			<!--ㅡㅡㅡㅡㅡ paging ㅡㅡㅡㅡㅡ-->
			<div><p id="cm" class="hc vc">${page}</p></div>
			<style>
			div { position:relative; } 
			#cm { position:absolute; } 
			.hc { width:20%; left:0; right:0; margin-left:50%; margin-right:50%; }
			.vc { height:3%; top: 0; bottom:0; margin-top:auto; margin-bottom:auto; }
			</style>
			<!--ㅡㅡㅡㅡㅡ paging ㅡㅡㅡㅡㅡ-->
	</div>
			<br/><br/><br/>
<form name="valueform">
<input type="hidden" id="member_id" value="${member_id}"/>
<input type="hidden" id="path" value="${path}"/>
<input type="hidden" id="session_id" value="${sessionScope.member_id}"/>
</form>
</body>
</html>