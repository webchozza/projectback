<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<h4>Scrap</h4>

	<!-- 바디 -->
	<section id="banner">
		<div class="content">
			<div class="table-wrapper">
				<table>
					<thead>
						<tr>
						<td><section id="search" class="alt" style="width: 400px">
					<form method="post" action="/dokky/ee.do">
						<input type="text" name="search" id="search" placeholder="Search"
							width="100px" />
							<input type="submit" name="ss"/>
					</form>
				</section></td>
						</tr>
					</thead>
					<tbody>
					<tr>
							<td><a href="#">여기에 게시글 제목</a></td>
							<td><a class="icon fa-comment">3</a>&nbsp;&nbsp;&nbsp; <a
								class="icon fa-thumbs-up">10</a>&nbsp;&nbsp;&nbsp; <a
								class="icon fa-eye">10</a></td>
							<td><a href="#">작성자 닉네임</a></td>
							<td>작성일자</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</section>

</body>
</html>