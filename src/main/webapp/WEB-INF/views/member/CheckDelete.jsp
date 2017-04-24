<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<title>CheckDelete</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<!--input="button" 누락확인과  submit하기   -->
<script type="text/javascript">
	function gosubmit1() {

		if (frm.email.value == "") {
			alert("패스워드을 입력해주세요.");
			return false;
		}

		var f = document.frm;
		f.method = "post";
		f.action = "deleteform.do";
		f.submit();
	}
	
</script>
</head>


<body>
<br/>
<br/>
	<div id="wrapper">
		<div id="main" align="center">
			<div class="inner">
				<h3>회원탈퇴</h3>
				<form name="frm">
					<!--비밀번호 입력창  -->
					<div class="6u 12u$(xsmall)" style="float: inherit;">
						<input type="text" style="width: 300pt;" name="password" id="password" value="" placeholder="Password" /> 
						<br /> 
						<!--버튼 -->
						<input type="button" value="탈퇴하기" style="width: 300pt;"
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