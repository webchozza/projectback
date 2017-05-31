<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Body</title>
</head>
<body>
	<!-- 바디 -->
	<section id="banner">
		<div class="content">
			<section>
				<div class="posts">
					<!-- Q&A -->
					<article>
						<h3>Community</h3>
						<div class="table-wrapper" style="height: 650px;">
							<table>
								<c:forEach var="colist" items="${main.colist}">
										<c:url var="detailurl" value="/bfreedetail.do">
											<c:param name="board_id" value="${colist.BOARD_ID }" />
											<c:param name="currentPage" value="1" />
											<c:param name="session_id" value="${sessionScope.member_id}" />
										</c:url>
									<tr>
										<td><a href="${detailurl}" style="color:#505052;">${colist.BOARD_TITLE}</a></td>
										<td><a href="/dokky/MemberPage.do?member_id=${colist.MEMBER_ID}&session_id=${sessionScope.member_id}">${colist.BOARD_NICKNAME}</a></td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<ul class="actions">
							<li><a href="/dokky/bfreelist.do" class="button">더 보기</a></li>
						</ul>
					</article>

					<!-- 구인구직 -->
					<article>
						<h3>Q&A</h3>
						<div class="table-wrapper">
							<table>
								<c:forEach var="qnlist" items="${main.qnlist}">
										<c:url var="detailurl" value="/bqnadetail.do">
											<c:param name="board_id" value="${qnlist.BOARD_ID }" />
											<c:param name="currentPage" value="1" />
											<c:param name="session_id" value="${sessionScope.member_id}" />
										</c:url>
									<tr>
										<td><a href="${detailurl}" style="color:#505052;">${qnlist.BOARD_TITLE}</a></td>
										<td><a href="/dokky/MemberPage.do?member_id=${qnlist.MEMBER_ID}&session_id=${sessionScope.member_id}">${qnlist.BOARD_NICKNAME}</a></td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<ul class="actions">
							<li><a href="/dokky/bqnalist.do" class="button">더 보기</a></li>
						</ul>
						<h3>OpenSource</h3>
						<div class="table-wrapper" style="height: 275px;">
							<table>
								<c:forEach var="oslist" items="${main.oslist}">
										<c:url var="detailurl" value="/bcodedetail.do">
											<c:param name="board_id" value="${oslist.BOARD_ID }" />
											<c:param name="currentPage" value="1" />
											<c:param name="session_id" value="${sessionScope.member_id}" />
										</c:url>
									<tr>
										<td><a href="${detailurl}" style="color:#505052;">${oslist.BOARD_TITLE}</a></td>
										<td><a href="/dokky/MemberPage.do?member_id=${oslist.MEMBER_ID}&session_id=${sessionScope.member_id}">${oslist.BOARD_NICKNAME}</a></td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<ul class="actions">
							<li><a href="/dokky/bcodelist.do" class="button">더 보기</a></li>
						</ul>
					</article>

				</div>
			</section>

		</div>
	</section>
</body>
</html>