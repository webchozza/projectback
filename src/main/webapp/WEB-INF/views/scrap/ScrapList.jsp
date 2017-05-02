<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<script>
function sch() {
	var form = document.getElementById('searchform');
	var path = form.path.value;
	var i = form.i.value;
	var search = form.search.value;
	
	$('#area').load(path, {
		currentPage : i,
		search : search,
		ap : 'AjaxSearch'
	});
}
</script>
<title>DOKKY</title>
<style>
input[name=ss] {
display:none;
}

a {
border-bottom-color: #FFFFFF;
	color: #f56a6a;
	text-decoration:none !important
}

#b{
  color: #3d4449;
  text-decoration:none !important
}
</style>
</head>
<body>
<div id="area">
	<h4>스크랩</h4>
	<span class="image fit"><h2><b>여기에 회원의 닉네임</b></h2></span>
	<div class="box alt">
		<div class="row 50% uniform" style="background-color: #f5f6f7; width:60%; display: inline-block;">
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center;"><h3>팔로잉</h3></span>
			</div>
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center;"><h3>팔로워</h3></span>
			</div>
			<!-- Break -->
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center; font-size: 30px;">0</span>
			</div>
			<div class="4u" style="width: 50%;">
				<span class="image fit" style="text-align: center; font-size: 30px;">1</span>
			</div>
		</div>
		<input type="hidden" id="plus" value=""/>
		<div class="row 100% uniform" style="background-color: #f5f6f7; width:30%; display: inline-block; max-width:30%; max-height:30%;">
		<div class="4u" style="width: 90%;">
		<span id="plusFollow" class="image fit fa fa-user-plus" style="text-align: center; font-size: 25px; color: #f56a6a;">
		<a href="javascript:;" onclick="followclick()"><h4 style="color: #f56a6a;">+팔로우</h4></a></span>
		</div>
		</div>
		</div>
			<!-- 바디 -->
			<div class="table-wrapper">
			<div style="max-width: 15%; text-align:center; display: inline-block;"><a href="/dokky/MemberPage.do?member_id=${member_id}">게시물</a></div>
			<div style="width: 10%; text-align:left; display: inline-block;">개수</div>
			<div style="max-width: 15%; text-align:center; display: inline-block;"><span style="color: #f56a6a;">스크랩</span></div>
			<div style="width: 10%; text-align:left; display: inline-block;">개수</div>
			<hr style="width: 70%; align: left;">
				<table>
					<thead>
						<tr>
						<td><section id="search" class="alt" style="width: 400px">
					<form action="javascript:;" method="post" id="searchform" >
					<input type="hidden" name="i" id="i" value="${i}"/>
					<input type="hidden" name="path" id="path" value="${path}"/>
						<input type="text" name="search" id="search" placeholder="Search" width="100px" />
							<input type="submit" name="ss" onclick="sch()"/>
					</form>
				</section></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="board" items="${board}">
					<tr>
							<td width="30%"><a href="#" id="b">${board.board_title}</a></td>
							<td width="10%"><a class="icon fa-comment">${board.board_comment_count}</a>&nbsp;&nbsp;&nbsp; <a
								class="icon fa-thumbs-up">${board.board_like}</a>&nbsp;&nbsp;&nbsp; <a
								class="icon fa-eye">${board.board_hit}</a></td>
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
			<br/><br/><br/>
</div>
</body>
</html>