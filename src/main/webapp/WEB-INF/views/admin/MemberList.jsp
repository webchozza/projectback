<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>회원 관리</title>
<script src="${pageContext.request.contextPath}/resources/assets/js/admin/memberlist.js"></script>
</head>
<style>
input[type="text"] {
	-moz-appearance: none;
	-webkit-appearance: none;
	-ms-appearance: none;
	appearance: none;
	background: #ffffff;
	border-radius: 0.375em;
	border: none;
	border: solid 1px rgba(210, 215, 217, 0.75);
	color: inherit;
	display: inline;
	outline: 0;
	padding: 0 1em;
	text-decoration: none;
	width: 100%;
}

.select-wrapper {
	text-decoration: none;
	display: inline-block;
	position: relative;
	width: 10%;
}

</style>
<body>
	<h4>Member Information Management</h4>
	
	<form id="searchform" method="post">
	<input type="hidden" name="i" id="i" value="${i}"/>
		<div class="select-wrapper">
			<select name="n" id="demo-category">
				<option value="0" <c:if test="${n eq 0}">selected</c:if>>이메일</option>
				<option value="1" <c:if test="${n eq 1}">selected</c:if>>닉네임</option>
			</select>
		</div>
		<input type="text" id="searchvalue" name="search" value="${search}"  style="width:20%"/> <input type="button" value="검색" onclick="sch()"/>
	</form>
	
		<a href="javascript:;" onclick='MemberCheck("on")'>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;접속 회원&nbsp;&nbsp;|&nbsp;&nbsp;</a> 
			<a href="javascript:;" onclick='MemberCheck("out")'>미접속 회원&nbsp;&nbsp;|&nbsp;&nbsp;</a> 
			<a href="javascript:;" onclick='MemberCheck("all")'>전체 회원&nbsp;&nbsp;|&nbsp;&nbsp;</a>
	<div id="area">
	<div class="table-wrapper">
		<table>
			<thead>
				<tr>
					<td width="15%" align="center"><b>이메일</b></td>
					<td width="10%" align="center"><b>닉네임</b></td>
					<td width="10%" align="center"><b>가입일</b></td>
					<td width="10%" align="center"><b>등급</b></td>
					<td width="10%" align="center"><b>관리</b></td>
					<td width="10%" align="left"><b>접속</b></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="member" items="${memberlist}">
					<tr>
						<td align="center">${member.member_email}</td>
						<td align="center">${member.member_name}</td>
						<td align="center"><fmt:formatDate
								value="${member.member_date}" pattern="yyyy.MM.dd" /></td>
						<td align="center"><c:if test="${sessionScope.admin eq null}">회원</c:if> 
						<c:if test="${sessionScope.admin ne null}">관리자</c:if></td><!-- 멤버칼럼에 관리자 체크 하는 로직하나 만들자 -->
						<td align="center">
						<a href="/dokky/AdminModifyForm.do?id=${member.member_id}">수정</a>&nbsp;&nbsp;&nbsp;
							<a href="/dokky/MemberDelete.do?member_id=${member.member_id}"
							onclick="return deleteMember()">탈퇴</a></td>
						<c:if test="${member.member_ch eq 0}">
							<td align="left"><img src="/dokky/resources/images/chu.jpg" style="width: 25px; height: 22px;"></td>
						</c:if>
						<c:if test="${member.member_ch eq 1}">
							<td align="left"><img src="/dokky/resources/images/ch.jpg" style="width: 25px; height: 22px;"></td>
						</c:if>
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
<form name="valueform">
<input type="hidden" id="member_id" value="${member_id}"/>
<input type="hidden" id="path" value="${path}"/>
<input type="hidden" id="session_id" value="${sessionScope.member_id}"/>
</form>
</body>
</html>