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
		if (frm.message_subject.value == "") {
			alert("제목을 입력해주세요");
			return false;
		}
		if (frm.message_content.value == "") {
			alert("글내용을 입력해주세요");
			return false;
		}
		var f = document.frm;
		f.name_to.value;
		f.name_from.value;
		f.message_subject.value = f.message_subject.value;
		f.message_content.value = f.message_content.value;
		f.method = "post";
		f.action = "/dokky/messagewrite.do";
		f.submit();
	}
</script>
</head>
<div id="wrapper" >
	<div id="main" align="center">
		 <div class="inner">
			<h3>MESSAGE</h3>
			<form name="frm">
			 <input type="hidden" id="name_from" name="name_from" value="${name_from}">
			 <input type="hidden" id="name_to" name="name_to" value="${name_to}">
				<div class="6u 12u$(xsmall)" style="width:300pt;">
					<input type="text" name="message_subject" id="message_subject" value=""
						style="width:300pt;" placeholder="제목" />
				</div>
				<!-- Break -->
				<br />
				<br />
				<!-- Break -->
				<div class="12u$">
					<textarea name="message_content" id="message_content" style="width:300pt;height: 400px;"
						placeholder="Enter your message" rows="6"></textarea>
				</div>
				<!-- Break -->
				<div class="12u$">
					<ul class="actions" style="float:inherit;">
						<input  type="button" style="width:300pt;" value="글쓰기" class="button special" onclick="javascript:gosubmit1()">
					</ul>

				</div>
			</form>
		 </div>
	</div>
</div>
</html>