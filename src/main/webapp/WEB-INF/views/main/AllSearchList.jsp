<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<script>
function paging(path, i, search, n) {
	$('#area').load(path, {
		currentPage : i,
		search : search,
		n : n,
		ap : 'AjaxPaging'
	});
}

function sch() {
	var form = document.getElementById('searchform');
	var path = form.path.value;
	var i = form.i.value;
	var search = form.soundsearch.value;
	
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
	<h4>Search All</h4>

	<!-- 바디 -->
	<section id="banner">
		<div class="content">
			<div class="table-wrapper">
				<table>
					<colgroup>
						<col width="58%" />
						<col width="22%" />
						<col width="10%" />
						<col width="10%" />
					</colgroup>
					<thead>
						<tr>
							<td colspan="2">
							<a href="#">최신순</a> 
							<a href="#">추천순</a> 
							<a href="#">댓글순</a> 
							<a href="#">스크랩순</a> 
							<a href="#">조회순</a></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="board" items="${allSearchList}">
						<tr>
							<td><a href="#" id="b">${board.board_title}</a></td>
							<td><a class="icon fa-comment">${board.board_comment_count}</a>&nbsp;&nbsp;&nbsp; 
							<a class="icon fa-thumbs-up">${board.board_like}</a>&nbsp;&nbsp;&nbsp; 
							<a class="icon fa-eye">${board.board_hit}</a></td>
							<td><a href="#" id="b">${board.board_nickname}</a></td>
							<td><fmt:formatDate value="${board.board_date}" pattern="yyyy.MM.dd" /></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div align="center">
				<section id="search" class="alt" style="width: 400px">
					<form method="post" action="#">
						<input type="text" name="AllSearch" id="AllSearch" placeholder="Search All"
							width="100px" />
							<input type="submit" name="ss"/>
					</form>
				</section>
			</div>
		</div>
	</section>
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