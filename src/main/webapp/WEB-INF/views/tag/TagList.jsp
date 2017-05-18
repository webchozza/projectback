<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>DOKKY</title>
</head>
<body>
	<h4>[${tag }]로 태그된 글</h4>

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
							<td colspan="2"><a href="taglist.do?tag=${tag }&sort=">최신순</a>
								<a href="taglist.do?tag=${tag }&sort=like">추천순</a> <a
								href="taglist.do?tag=${tag }&sort=comment">댓글순</a> <a 
								href="taglist.do?tag=${tag }&sort=scrap">스크랩순</a>
								<a href="taglist.do?tag=${tag }&sort=hit">조회순</a></td>
							<td colspan="2" align="right"></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${taglist }">
							<c:if test="${list.bgroup_id eq 1}"> <!-- qna -->
								<c:url var="detailURL" value="/bqnadetail.do">
									<c:param name="board_id" value="${list.board_id }" />
									<c:param name="currentPage" value="1" />
									<c:param name="session_id" value="${sessionScope.member_id}" />
								</c:url>
							</c:if>
							<c:if test="${list.bgroup_id eq 2}"> <!-- 자유 -->
								<c:url var="detailURL" value="/bfreedetail.do">
									<c:param name="board_id" value="${list.board_id }" />
									<c:param name="currentPage" value="1" />
									<c:param name="session_id" value="${sessionScope.member_id}" />
								</c:url>
							</c:if>
							<c:if test="${list.bgroup_id eq 3||board.bgroup_id eq 4}"> <!-- 구인구직 -->
								<c:url var="detailURL" value="/bfreedetail.do">
									<c:param name="board_id" value="${list.board_id }" />
									<c:param name="currentPage" value="1" />
									<c:param name="session_id" value="${sessionScope.member_id}" />
								</c:url>
							</c:if>
							<c:if test="${list.bgroup_id eq 5}"> <!-- 오픈소스 -->
								<c:url var="detailURL" value="/bfreedetail.do">
									<c:param name="board_id" value="${list.board_id }" />
									<c:param name="currentPage" value="1" />
									<c:param name="session_id" value="${sessionScope.member_id}" />
								</c:url>
							</c:if>
							
							<tr>
								<td><a href="${detailURL }">${list.board_title }</a></td>
								<td><a class="icon fa-comment">${list.board_comment_count }</a>&nbsp;&nbsp;&nbsp;
									<a class="icon fa-thumbs-up">${list.board_like }</a>&nbsp;&nbsp;&nbsp;
									<a class="icon fa-eye">${list.board_hit }</a></td>
								<td><a
									href="/dokky/MemberPage.do?member_id=${list.member_id }&session_id=${sessionScope.member_id}">${list.board_nickname }</a></td>
								<td><fmt:formatDate value="${list.board_date }"
										pattern="yyyy.MM.dd" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="paging" align="center">${pagingHtml}</div>
			<br>
		</div>
	</section>
</body>
</html>