<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-2.2.1.js"></script>
<script>
function paging(path, i, search, n) {
	
	var member_id = ${member_id};
	
	$('#movearea').load(path, {
		member_id : member_id,
		currentPage : i,
		n : n,
		search : search,
		ap : 'AjaxPaging'
	});
}

function sch() {

	var form = document.getElementById('searchform');
	var i = form.i.value;
	var member_id = ${member_id};
	var search = form.search.value;

	$('#area').load("/dokky/MemberPage.do", {
		member_id : member_id,
		currentPage : i,
		search : search,
		ap : 'AjaxSearch'
	});
}

function scrap(){

	var form = document.getElementById('searchform');
	var i = form.i.value;
	var search = form.search.value;
	var member_id = ${member_id};

	$('#movearea').load("/dokky/ScrapList.do", {
		member_id : member_id,
		currentPage : i,
		search : search,
		ap : 'AjaxScrap'
	});
}

function memberpage(){
	
	var form = document.getElementById('searchform');
	var i = form.i.value;
	var search = form.search.value;
	var member_id = ${member_id};

	$('#movearea').load("/dokky/MemberPage.do", {
		member_id : member_id,
		currentPage : i,
		search : search,
		ap : 'AjaxMemberPage'
	});
}

function followclick(){
	
	var plus = document.getElementById("plus").value;

	if(plus == "0"){
	$("#plusFollow").html("");
	$("#plusFollow").html('<a href="#" onclick="followclick()"><h4 style="color: #f56a6a;">팔로우 해제</h4></a>');
	$("#plus").val("1");
	} 
	
	if(plus == "1"){
	$("#plusFollow").html("");
	$("#plusFollow").html('<a href="#" onclick="followclick()"><h4 style="color: #f56a6a;">+팔로우</h4></a>');
	$("#plus").val("0");
	}
	
	return true;
}

function followcheck(){
	
	var me = '${me}';
	var check = '${followCheck.followCheck}';
	
	if(me == "me"){
		$("#me").html("");
	} else if(check != null){//멤버페이지를 불러올 때 팔로우여부 체크값을 보내서 조건을 준다음 plus의 값을 설정함
	$("#plus").val("0");
	$("#plusFollow").html("");
	$("#plusFollow").html('<a href="#" onclick="followclick()"><h4 style="color: #f56a6a;">+팔로우</h4></a>');
	} else if(check == null){
	$("#plus").val("1");
	$("#plusFollow").html("");
	$("#plusFollow").html('<a href="#" onclick="followclick()"><h4 style="color: #f56a6a;">팔로우 해제</h4></a>');
	}
}
</script>
<title>회원 정보 보기</title>
<style>
#b{
  color: #3d4449;
  text-decoration:none !important
}
</style>
</head>
<body onload="followcheck()">
	<h4>회원 정보</h4>
	
	<span class="image fit"><h2><b>여기에 회원의 닉네임</b></h2></span>
	<div class="box alt">
		<div class="row 50% uniform" style="background-color: #f5f6f7; width:60%; display: inline-block;">
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center;"><h3><b>팔로잉</b></h3></span>
			</div>
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center;"><h3><b>팔로워</b></h3></span>
			</div>
			<!-- Break -->
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center; font-size: 30px;">${followCount.following_count}</span>
			</div>
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center; font-size: 30px;">${followCount.follower_count}</span>
			</div>
		</div>
		<input type="hidden" id="plus" value=""/>
		<div class="row 100% uniform" style="background-color: #f5f6f7; width:30%; display: inline-block; max-width:30%; max-height:30%;">
		<div class="4u" style="width: 90%;">
		<div id="me" style="height: 93px;"><span id="plusFollow" class="image fit fa fa-user-plus" style="text-align: center; font-size: 25px; color: #f56a6a;">
		<a href="/dokky/AddFollow.do?member_id=${member_id}" onclick="return followclick()"><h4 style="color: #f56a6a;">+팔로우</h4></a></span></div>
		</div>
		</div>
	</div>
	<div id="movearea">
	<div class="table-wrapper">
	<div style="max-width: 15%; text-align:center; display: inline-block;" id="boardarea"><h3><a href="javascript:;" onclick="memberpage()">게시물</a></h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">개수</div>
	<div style="max-width: 15%; text-align:center; display: inline-block;" id="scraparea"><h3><a href="javascript:;" onclick="scrap()">스크랩</a></h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">개수</div>
	<div style="max-width: 15%; text-align:center; display: inline-block;" id="scraparea"><h3><a href="javascript:;" onclick="follow()">팔로우</a></h3></div>
	<div style="width: 10%; text-align:left; display: inline-block;">개수</div>
	<hr style="width: 70%; align: left;">
		<table>
					<thead>
						<tr>
						<td><section id="search" class="alt" style="width: 400px">
					<form action="javascript:;" method="post" id="searchform" >
					<input type="hidden" name="member_id" id="member_id" value="${member_id}"/>
					<input type="hidden" name="i" id="i" value="${i}"/>
					<input type="hidden" name="path" id="path" value="${path}"/>
						<input type="text" name="search" id="search" placeholder="Search"/>
							<input type="submit" name="ss" onclick="sch()"/>
					</form>
				</section></td>
						</tr>
					</thead>
			<tbody>
			<span id="move"><c:forEach var="board" items="${list}"></span>
			<tr>
					<td width="30%"><a href="#" id="b">${board.board_title}</a></td>
					<td width="10%"><a class="icon fa-comment">${board.board_comment_count}</a>&nbsp;&nbsp;&nbsp; 
					<a class="icon fa-thumbs-up">${board.board_like}</a>&nbsp;&nbsp;&nbsp; 
					<a class="icon fa-eye">${board.board_hit}</a></td>
	 				<td width="10%" align="center"><a href="#" id="b">${board.member_name}</a></td>
					<td width="5%" align="center"><fmt:formatDate value="${board.board_date}" pattern="yyyy.MM.dd" /></td>
					<td width="5%"><a href=""><img src="/dokky/resources/images/x.jpg" style="width: 20%; height: 5%;"/></a></td>
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
			.hc { width:10%; left:0; right:0; margin-left:50%; margin-right:auto; }
			.vc { height:3%; top: 0; bottom:0; margin-top:auto; margin-bottom:auto; }
			</style>
			<!--ㅡㅡㅡㅡㅡ paging ㅡㅡㅡㅡㅡ-->
	</div>
			<br/><br/><br/>
</body>
</html>