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
		if (frm.subject.value == "") {
			alert("제목을 입력해주세요");
			return false;
		}
		if (frm.message.value == "") {
			alert("글내용을 입력해주세요");
			return false;
		}
		if (frm.category.value == "") {
			alert("카테고리을 선택해주세요");
			return false;
		}
		var f = document.frm;
		f.method = "post";
		f.action = "bqnawrite.do";
		f.submit();
	}
</script>
</head>
<div id="wrapper" >
	<div id="main" align="center">
		 <div class="inner">
			<h3>Q&A</h3>
			<form name="frm">
				<div class="6u 12u$(xsmall)" style="width:300pt;">
					<input type="text" name="subject" id="subject" value=""
						style="width:300pt;" placeholder="제목" />
				</div>
				<!-- Break -->
				<br />
				<div class="12u$">
					<div class="select-wrapper">
						<select name="category" style="width:300pt;" id="category">
							<option value="">- Category -</option>
							<option value="1">알아서하삼</option>
							<option value="2">알아서하삼</option>
							<option value="3">알아서하삼</option>
							<option value="4">알아서하삼</option>
						</select>
					</div>
				</div>
				<br />
				<!-- Break -->
				<div class="12u$">
					<textarea name="message" id="message" style="width:300pt;height: 400px;"
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