<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<title>DOKKY</title>
<style>
input[name=ss] {
display:none;
}
</style>
</head>
<body>
	<h4>Q&A</h4>

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
							<td colspan="2"><a href="#">최신순</a> <a href="#">추천순</a> <a
								href="#">댓글순</a> <a href="#">스크랩순</a> <a href="#">조회순</a></td>
							<td colspan="2" align="right"><a href="#"
								class="button special"><i class="icon fa-pencil"> 새 글 쓰기</i></a></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${bqnalist}">
						<tr>
							<td><a href="#">${list.board_title }</a></td>
							<td><a class="icon fa-comment">${list.board_comment_count }</a>&nbsp;&nbsp;&nbsp; <a
								class="icon fa-thumbs-up">${list.board_like }</a>&nbsp;&nbsp;&nbsp; <a
								class="icon fa-eye">${list.board_hit }</a></td>
							<td><a href="#">${list.board_nickname }</a></td>
							<td>${list.board_date }</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div class="paging" aling="center">${paingHtml}</div>
			
			<div align="center">
				<section id="search" class="alt" style="width: 400px">
					<form method="post" action="#">
						<input type="text" name="search" id="search" placeholder="Search"
							width="100px" />
							<input type="submit" name="ss"/>
					</form>
				</section>
			</div>
		</div>
	</section>

</body>
</html>