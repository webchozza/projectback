<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<title>CheckModify</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<!--input="button" 누락확인과  submit하기   -->
<script type="text/javascript">
	function gosubmit1() {

		if (frm.member_pw.value == "") {
			alert("패스워드을 입력해주세요.");
			return false;
		}

		var f = document.frm;
		f.member_email.value = f.member_email.value;
		f.member_pw.value = f.member_pw.value;
		f.method = "post";
		f.action = "modifyform.do";
		f.submit();
	}
</script>
</head>


<body>
	<br />
	<br />
	<div id="wrapper">
		<div id="main" align="center">
			<div class="inner">
				<h3>회원정보수정</h3>
				<form name="frm">
					<!--비밀번호 입력창  -->
					<input type="hidden" name="member_email" value="${member_email}">
					<div class="6u 12u$(xsmall)" style="float: inherit;">
						<input type="text" style="width: 300pt;" name="member_pw" id="member_pw" value="" placeholder="Password" /> 
						<br />
						<!--버튼 -->
						<input type="button" value="다음" style="width: 300pt;"
							class="button special" onclick="javascript:gosubmit1()">
						<br />
						<hr style="margin: 0 auto; width: 300pt;">
					</div>
			</div>
			</form>
		</div>
	</div>
</body>
</html>