<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>회원 관리</title>
<script>
function paging(path, i, search, n) {
	
	stoploop();
	
	$('#area').load(path, {
		currentPage : i,
		search : search,
		n : n,
		ap : 'AjaxPaging'
	});
}

function sch() {
	
	stoploop();
	
	var form = document.getElementById('searchform');
	var path = ${path};
	var i = form.i.value;
	var search = form.search.value;
	var n = form.n.value;
	
	$('#area').load(path, {
		currentPage : i,
		search : search,
		n : n,
		ap : 'AjaxSearch'
	});
}

function MemberCheck(i){
	
	stoploop();
	
	if(i == 'all'){
		$('#area').load('/dokky/MemberList.do', {ap:'AjaxArrange'});
	}
	if(i == 'on'){
		$('#area').load('/dokky/MemberList.do', {ap:'AjaxArrange', ch:1});
	}
	if(i == 'out'){
		$('#area').load('/dokky/MemberList.do', {ap:'AjaxArrange', ch:0});
	}
}

	//멤버리스트 화면 로딩시 한번만 동작함(이후엔 #area영역에 호출된 MemberListPage의 setTimeout함수가 동작함)
var loop = setTimeout(function(){
	var form = document.getElementById('searchform');
	var path = ${path};
	var i = form.i.value;
	var search = form.search.value;
	var n = $("#demo-category").val();
	
	$("#area").load(path,{
		currentPage: i, 
		search: search, 
		n: n, 
		ap: 'AjaxMemberCheck'
		});
	setTimeout(loop, 3000);
}, 3000);

function stoploop(){
	console.log("멈춰");
	clearTimeout(loop);
}
</script>
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
	width: 20%;
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
		<input type="text" id="searchvalue" name="search" value="${search}"/> <input type="button" value="검색" onclick="sch()"/>
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
					<td width="10%" align="center"><b>접속</b></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="member" items="${memberlist}">
					<tr>
						<td align="center">${member.member_email}</td>
						<td align="center">${member.member_name}</td>
						<td align="center"><fmt:formatDate
								value="${member.member_date}" pattern="yyyy.MM.dd" /></td>
						<td align="center">여기에 등급</td>
						<td align="center">
						<a href="/dokky/AdminModifyForm.do?id=${member.member_id}">수정</a>&nbsp;&nbsp;&nbsp;
							<a href="/dokky/MemberDelete.do?member_id=${member.member_id}"
							onclick="return deleteMember()">탈퇴</a></td>
						<c:if test="${member.member_ch eq 0}">
							<td align="center"><img src="/dokky/resources/images/chu.jpg" style="width: 17%; height: 35%;"></td>
						</c:if>
						<c:if test="${member.member_ch eq 1}">
							<td align="center"><img src="/dokky/resources/images/ch.jpg" style="width: 17%; height: 35%;"></td>
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
</body>
</html>