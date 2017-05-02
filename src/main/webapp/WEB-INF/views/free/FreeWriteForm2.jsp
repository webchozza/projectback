<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<title>WriteForm</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!--input="button" 누락확인과  submit하기   -->
<script type="text/javascript">
	function gosubmit1() {
		if (frm.board_title.value == "") {
			alert("제목을 입력해주세요");
			return false;
		}
		if (frm.board_content.value == "") {
			alert("글내용을 입력해주세요");
			return false;
		}
		var f = document.frm;
		f.method = "post";
		f.action = "bfreewrite.do";
		f.submit();
	}
</script>
</head>
<div id="wrapper">
	<div id="main" align="center">
		<div class="inner">
			<h3>Community</h3>
			<form name="frm">
			
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
						<input type="button" style="width: 300pt;" value="글쓰기"
							class="button special" onclick="javascript:gosubmit1()">
					</ul>
				</div>
			</form>
		</div>
	</div>
</div>
</html>