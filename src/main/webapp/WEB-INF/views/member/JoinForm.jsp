<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>JoinForm</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!--비밀번호 확인  -->
<script>
 $(function(){
  $('#password').keyup(function(){
   $('font[name=check]').text('');
  }); //#password.keyup

  $('#chpass').keyup(function(){
   if($('#password').val()!=$('#chpass').val()){
    $('font[name=check]').text('');
    $('font[name=check]').html("비밀번호가 일치하지 않습니다.");
   }else{
    $('font[name=check]').text('');
    $('font[name=check]').html("비밀번호가 일치합니다.");
   }
  }); //#chpass.keyup
 });
 </script>
<!--input="button" 누락확인과  submit하기   -->
<script type="text/javascript">
	function gosubmit1(){
	
	
	if(frm.name.value == "") {
		alert("이름을 입력해 주세요");
		return false;
	}
	if(frm.email.value == "") {
		alert("email을 입력해 주세요");
		return false;
	}
	if(frm.password.value == "") {
		alert("비밀번호를 입력해 주세요");
		return false;
	}
	if(frm.chpass.value == "") {
		alert("비밀번호를 확인해주세요");
		return false;
	}
	if(frm.password.value!=frm.chpass.value){
		alert("비밀번호가 일치하지 않습니다.");
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
	f.action = "email.do";
	f.submit();
} 
function gosubmit2(){
	var f = document.frm;
	f.method = "post";
	f.action = "main.do";
	f.submit();
}
</script>
</head>

<body>
	<div id="wrapper">
		<div id="main" align="center"> 
		<div class="inner">
<!-- Join Form -->
					
	<h3>회원가입</h3>
		<form name="frm">
	
		<div class="row uniform" style="float:inherit;">
				<div class="6u 12u$(xsmall)">				
			
					<input type="text"  name="name" id="name" value="" placeholder="닉네임"/>
					<br/>
					<input type="text"  name="email" id="email" value="" placeholder="DOKKY에서 사용할 이메일을 입력해주세요">
					<ul class="actions"style="float:left;">
					<li><a href="#"class="button small special">이메일 중복확인</a></li>
					</ul>
					<input type="password" style="width:500;" name="password" id="password" value="" placeholder="비밀번호" />
					<br/>
					<input type="password" style="width:500;" name="chpass" id="chpass" value="" placeholder="비밀번호 확인" />
					<font name="check" size="2" style="float:left" color="pink"></font>
					<br/>
					<ul class="actions">
						<input type="button" value="가입" class="button special" onclick="javascript:gosubmit1()">
						<input type="button" value="메인으로 가기" class="button special" onclick="javascript:gosubmit2()">
					</ul>
					
				</div>
								
		   </div> 
		</form>
	
	</div>
</div> 
 	 </div> 
	</body>
</html>