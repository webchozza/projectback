<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page isELIgnored="false"%>
<html>
<head>
<script>
//함수를 호출할 때 return을 붙이고 function에서 return false를 주면 이벤트가 끊김(즉 서브밋일 떄 주면 서브밋이 중단됨)
/* function filecheck(event){
	var filecheck = $("#filea").val();
	var f = document.frm;
	
	if(filecheck==""){
		event.preventdefault();
		f.method = "post";
		f.action = "/dokky/bcodeinsert.do";
		f.submit();//이렇게해서 만약에 값이 안넘어가면 f.board_id = f.board_id; 이런식으로 값설정하고 넘긴다.
		//오킹!
		
		
	}
} */
</script>
<title>WriteForm</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />

</head>
<body>
	<div id="wrapper">
		<div id="main" align="center">
			<div class="inner">
				<h3>Open Source</h3>
				<form name="frm" action="/dokky/bcodeinsert.do" method="post" enctype="multipart/form-data">
				   <c:if test="${updateform.board_id ne null}">
				     <input type="hidden" name="board_id" id="board_id" value="${updateform.board_id}">
				   </c:if>
    				<input type="hidden" id="member_id" name="member_id" value="${sessionScope.member_id}">
				       <input type="hidden" id="board_nickname" name="board_nickname" value="${sessionScope.member_name}">

					<!-- BGROUP_ID -->
					<input type="hidden" name="bgroup_id" id="bgroup_id" value="1" />
					<!-- BOARD_TITLE -->
					<div class="6u 12u$(xsmall)" style="width: 400pt;">
						<input type="text" name="board_title" id="board_title" value="" style="width: 400pt;" placeholder="[글제목] <c:out value="${updateform.board_title}"/>" />
					</div>
					<br />
					<!-- BOARD_CONTENT -->
					<div class="12u$">
						<textarea name="board_content" id="board_content" style="width: 500pt; height: 400px;" placeholder="[메시지] <c:out value="${updateform.board_content}"/> " rows="6"></textarea>
					</div>
					<!-- Break -->
					<div class="12u$">
					  <input type="file" name="file" id="file" style="width:300pt;">
					  <br/>
					  <input type="submit" style="width: 300pt;" value="글쓰기" class="button special">
					</div>
				</form>

			</div>
		</div>
	</div>
</body>
<!-- CATEGORY -->
<br />
<!--         <div class="12u$">
					<div class="select-wrapper">
						<select name="category" style="width:400pt;" id="category">
							<option value="">- Category -</option>
							<option value="1">JAVA</option>
							<option value="2">SQL</option>
							<option value="3">AJAX</option>
							<option value="4">ETC</option>
						</select>
					</div>
				</div> -->
</html>