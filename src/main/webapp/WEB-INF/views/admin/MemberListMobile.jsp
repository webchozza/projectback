<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>회원 관리</title>
<script src="${pageContext.request.contextPath}/resources/assets/js/admin/memberlistmobile.js?v=3"></script>
</head>
<style>
.select-wrapper {
	text-decoration: none;
	display: inline-block;
	position: relative;
	width: 10%;
}

</style>
<body>
	<div class="table-wrapper">
				<c:forEach var="member" items="${memberlist}">
				<c:if test="${member.member_admin ne 2}">
				<div style="border: 1px solid #3d4449; max-width:33%; min-width:290px; display:inline-block; margin:5px; ">
				<span style="border-right: 1px solid gold; color:#f56a6a;">이메일</span>
				<b>${member.member_email}</b><br>
				<span style="border-right: 1px solid gold; color:#f56a6a;">닉네임</span>
				<b>${member.member_name}</b><br>
				<span style="border-right: 1px solid gold; color:#f56a6a;">가입일</span>
				<b><fmt:formatDate value="${member.member_date}" pattern="yyyy.MM.dd" /></b><br>
				<span style="border-right: 1px solid gold; color:#f56a6a;">&nbsp;&nbsp;&nbsp;등급</span>
				<c:if test="${member.member_admin eq 0}"><b>회원</b></c:if>
				<c:if test="${member.member_admin eq 1}"><b>관리자</b></c:if><br>
				<span style="border-right: 1px solid gold; color:#f56a6a;">&nbsp;&nbsp;&nbsp;관리</span>
				<a href="javascript:;" onclick='memberModifyForm("${member.member_id}")'><b>수정</b></a>&nbsp;&nbsp;|
				<a href="javascript:;" onclick='return deleteMember("${member.member_id}")'><b>탈퇴</b></a><br>
				<span style="border-right: 1px solid gold; color:#f56a6a;">&nbsp;&nbsp;&nbsp;접속</span>
						<c:if test="${member.member_ch eq 0}">
							<td width="7%" align="center"><img src="/dokky/resources/images/chu.jpg" style="width: 25px; height: 22px;"></td>
						</c:if>
						<c:if test="${member.member_ch eq 1}">
							<td width="7%" align="center"><img src="/dokky/resources/images/ch.jpg" style="width: 25px; height: 22px;"></td>
						</c:if>
				</div>
					</c:if>
				</c:forEach>
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
</body>
</html>