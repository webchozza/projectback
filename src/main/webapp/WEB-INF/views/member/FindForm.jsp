<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<title>FindForm</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<!--input="button" 누락확인과  submit하기   -->
<script type="text/javascript">
	function gosubmit1() {

		if (frm.email.value == "") {
			alert("이메일을 입력해주세요.");
			return false;
		}
		if(((frm.email.value.indexOf('@'))<=0)&&(frm.email.value.indexOf('.')<=0))
		{
			alert("정상적인 email이 아닙니다.");
			frm.email.value="";
			frm.email.focus();
			return;
		}
		var f = document.frm;
		f.method = "post";
		f.action = "findpw.do";
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
				<h3>비밀번호 찾기</h3>
				<form name="frm">
					<!--이메일 입력창  -->
					<div class="6u 12u$(xsmall)" style="float: inherit;">
						<input type="text" style="width: 300pt;" name="email" id="email"
							value="" placeholder="Email" /> <br /> 
						<!--홈 버튼 -->
						<input type="button" value="확인" style="width: 300pt;"
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