<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	<h4>MESSAGE</h4>

	<!-- 바디 -->
	<section id="banner">
		<div class="content">
			<div class="table-wrapper">
				<table>
					<colgroup>
						<col width="20%" />
						<col width="20%" />
						<col width="28%" />
						<col width="10%" />
						<col width="15%" />
					</colgroup>
					<tbody>
						<a href="/dokky/massagelist.do">전체쪽지함</a>|<a href="/dokky/massagelist.do?n=1">보낸쪽지함</a>|<a href="/dokky/massagelist.do?n=2">받은쪽지함</a>
						<br/>
						전체쪽지 : ${fn:length(totalList)} | 읽은 않은 쪽지 : ${fn:length(messageNotRead)}
						<c:forEach items="${messageList}" var="list">

							<tr>
								<td>${list.message_id}</td>
								<td><a
									href="/dokky/messagecontent.do?message_id=${list.message_id}">${list.message_subject}</a></td>
								<td>보낸이&nbsp;<a href="/dokky/MemberPage.do?member_id=${sessionScope.member_id}">${list.name_from}</a> |&nbsp;&nbsp;받은이&nbsp;<a href="/dokky/MemberPage.do?member_id=${sessionScope.member_id}">${list.name_to}</a></td>
								<td></td>
								<td><fmt:formatDate value="${list.message_date}"
										pattern="yyyy.MM.dd" /></td>
								<td><a
									href="/dokky/messagedelete.do?message_id=${list.message_id}">삭제</a></td>

							</tr>
						</c:forEach>
						<c:if test="${messageList.size()==0}">
							<tr>
								<td>쪽지함이 비어 있습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<div align="center">
					<form method="post" action="/dokky/massagelist.do">
						<input type="text" name="search" id="search" placeholder="Search"
							style="width:300pt;" /><input type="submit" style="width:300pt;" name="searchKeyWord" class="button special" value="검색" />
						
					</form>
			</div>
		</div>
	</section>

</body>
</html>