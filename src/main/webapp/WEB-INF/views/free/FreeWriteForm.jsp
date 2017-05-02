<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<title>WriteForm</title>
<link rel="stylesheet" href="assets/css/main.css" />
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	function gosubmit1() {
		if (frm.board_title.value == ""||frm.board_title.value == null) {
			alert("제목을 입력해주세요");
			frm.board_title.focus();
			return false;
		}
		if (frm.board_content.value == ""||frm.board_content.value == null) {
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
			<form name="frm" action="bfreewrite.do" onsubmit="return gosubmit1()">

				<div class="6u 12u$(xsmall)" style="width: 300pt;">
					<input type="text" name="member_id" id="subject" value=""
						style="width: 300pt;" placeholder="아이디" />
				</div>

				<div class="6u 12u$(xsmall)" style="width: 300pt;">
					<input type="text" name="board_nickname" id="subject" value=""
						style="width: 300pt;" placeholder="닉네임" />
				</div>

				<div class="6u 12u$(xsmall)" style="width: 300pt;">
					<input type="text" name="board_title" id="subject" value=""
						style="width: 300pt;" placeholder="제목" />
				</div>
				<div class="12u$">
					<textarea name="board_content" id="message"
						style="width: 300pt; height: 400px;" placeholder="내용을 적어주세요"
						rows="6"></textarea>
				</div>
				<div class="12u$">
					<ul class="actions" style="float: inherit;">
						<input type="submit" style="width: 300pt;" value="글쓰기"
							class="button special">
					</ul>
				</div>
			</form>
		</div>
	</div>
</div>
</html>