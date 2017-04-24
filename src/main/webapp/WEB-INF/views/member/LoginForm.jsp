<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>LoginForm</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<!--input="button" 누락확인과  submit하기   -->
<script type="text/javascript">
function gosubmit1(){
	
	if(frm.email.value == "") {
		alert("email을 입력해 주세요");
		return false;
	}
	if(frm.password.value == "") {
		alert("비밀번호를 입력해 주세요");
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
	f.action = "login.do";
	f.submit();
}
function gosubmit2(){
	var f = document.frm;
	f.method = "post";
	f.action = "joinform.do";
	f.submit();
}
function gosubmit3(){
	var f = document.frm;
	f.method = "post";
	f.action = "findpwform.do";
	f.submit();
}
</script>
</head>


<body>
	<div id="wrapper">
		<div id="main" align="center">
			<div class="inner">
	<!-- Login Form -->			
		<h3>로그인</h3>
				<form name="frm">
				<!--이메일 비밀번호 입력창  -->
				<div class="6u 12u$(xsmall)" style="float:inherit;">
					<input type="text" style="width:300pt;" name="email" id="email" value="" placeholder="Email" />
					<br/>
					<input type="password" style="width:300pt;" name="password" id="password" value="" placeholder="Password" />
			
				<!--로그인 버튼 -->
					<br/>
					<input type="button" value="로그인" style="width:300pt;" class="button special" onclick="javascript:gosubmit1()">
				<!--체크박스 -->
				<br/>
				<div class="6u 12u$(xsmall)" style="float:left">
					<input type="checkbox" id="rememberEmail" name="rememberEmail">
					<label for="rememberEmail">이메일 저장</label>
				</div> 
				<br/>
				<hr style="margin:0 auto;width:300pt;">					
				<!--helper-->
					<a href="javascript:gosubmit2()" class="pagination">회원가입</a> | <a href="javascript:gosubmit3()">비밀번호찾기</a>	
				</div>		
		   </div>
			</form>
		 </div> 
	</div>
	</body>
</html>