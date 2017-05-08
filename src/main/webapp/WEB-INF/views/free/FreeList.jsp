<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>DOKKY</title>
<style>
input[name=ss] {
	display: none;
}
</style>
</head>
<body>
	<h4>Community</h4>

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
							<td colspan="2"><a href="bfreelist.do?sort=">최신순</a>  <a
								href="bfreelist.do?sort=like">추천순</a>  <a
								href="bfreelist.do?sort=comment">댓글순</a>  <a href="#">스크랩순</a>  <a
								href="bfreelist.do?sort=hit">조회순</a></td>
							<td colspan="2" align="right"><c:if
									test="${sessionScope.member_email ne null}">
									<a href="bfreewriteform.do" class="button special"><i
										class="icon fa-pencil"> 새 글 쓰기</i></a>
								</c:if></td>


						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${bfreelist }">
							<c:url var="detailURL" value="bfreedetail.do">
								<c:param name="board_id" value="${list.board_id }" />
								<c:param name="currentPage" value="${currentPage }" />
							</c:url>
							<tr>
								<td><a href="${detailURL }">${list.board_title }</a></td>
								<td><a class="icon fa-comment">${list.board_comment_count }</a>&nbsp;&nbsp;&nbsp;
									<a class="icon fa-thumbs-up">${list.board_like }</a>&nbsp;&nbsp;&nbsp;
									<a class="icon fa-eye">${list.board_hit }</a></td>
								<td><a href="MemberPage.do?member_id=${list.member_id }">${list.board_nickname }</a></td>
								<td><fmt:formatDate value="${list.board_date }"
										pattern="yyyy.MM.dd" /></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>

			<div class="paging" align="center">${pagingHtml}</div>
			<br>



			<div class="row uniform" align="center">
				<form method="post" action="bfreelist.do">
				<input type="hidden" name="sort" value="${sort}">
					<div class="select-wrapper" style="width: 200px; display: inherit;"
						align="center">
						<select name="n" id="demo-category">
							<option value="1">제목</option>
							<option value="2">내용</option>
							<option value="3">작성자</option>
						</select>
					</div>
					<section id="search" class="alt"
						style="width: 200px; display: inherit;">

						<input type="text" name="search" id="search" placeholder="Search"
							width="100px" /> <input type="submit" name="ss" />

					</section>
				</form>
			</div>
		</div>
	</section>

</body>
</html>