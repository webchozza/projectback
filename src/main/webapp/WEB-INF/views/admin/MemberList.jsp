<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	<form action="#" method="post">
			<div class="select-wrapper">
				<select name="demo-category" id="demo-category">
					<option value="">이메일</option>
					<option value="1">닉네임</option>
					<option value="1">가입일</option>
				</select>
			</div>
		<input type="text" name="search" /> <input type="submit" value="검색" />
		<a href="#">&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;접속
			회원&nbsp;&nbsp;|&nbsp;&nbsp;</a> <a href="#">미접속
			회원&nbsp;&nbsp;|&nbsp;&nbsp;</a>
	</form>
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
				<tr>
					<td align="center">여기에 이메일</td>
					<td align="center">여기에 닉네임</td>
					<td align="center">여기에 가입일</td>
					<td align="center">여기에 등급</td>
					<td align="center"><a href="#">수정</a>&nbsp;&nbsp;&nbsp;<a
						href="#">차단</a>&nbsp;&nbsp;&nbsp;<a href="#">탈퇴</a></td>
					<td align="center"><img src="">접속 여부에 따라 해당 이미지 노출</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>