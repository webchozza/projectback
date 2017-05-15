<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp"%>
<html>
<head>
<title>ModifyForm</title>
<link rel="stylesheet" href="assets/css/main.css" />
<script type="text/javascript">
	function gosubmit1() {
		if (frm.board_title.value == "" || frm.board_title.value == null) {
			alert("제목을 입력해주세요");
			frm.board_title.focus();
			return false;
		}
		if (frm.board_content.value == "" || frm.board_content.value == null) {
			alert("글내용을 입력해주세요");
			frm.board_content.focus();
			return false;
		}
	}
</script>
</head>
<div id="wrapper">
	<div id="main" align="center">
		<div class="inner">
			<h3>Community</h3>
			<form name="frm" action="bqnamodify.do"
				onsubmit="return gosubmit1()">

				<div class="6u 12u$(xsmall)" style="width: 300pt;">
					<input type="text" name="board_title" id="subject"
						value="${boardDTO.board_title }" style="width: 300pt;"
						placeholder="제목" />
				</div>
				<div class="12u$">
					<textarea name="board_content" id="message"
						style="width: 300pt; height: 400px;" placeholder="내용을 적어주세요"
						rows="6">${boardDTO.board_content }</textarea>
				</div>
				<div class="6u 12u$(xsmall)" style="width: 300pt;">
					<textarea name="board_tag" id="message" style="width: 300pt;"
						placeholder="태그" rows="1">${boardDTO.board_tag}</textarea>
				</div>
				<div class="12u$">
					<ul class="actions" style="float: inherit;">
						<input type="hidden" name="currentPage" value="${currentPage }">
						<input type="hidden" name="board_id" value="${boardDTO.board_id}">
						<input type="submit" style="width: 300pt;" value="수정"
							class="button special">
					</ul>
				</div>
			</form>
		</div>
	</div>
</div>
</html>