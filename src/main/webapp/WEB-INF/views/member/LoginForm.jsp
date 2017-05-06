<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>LoginForm</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<!--쿠기 저장 -->
<script type="text/javascript">
$(document).ready(function(){
    // 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
    var userInputId = getCookie("userInputId");
    $("input[name='member_email']").val(userInputId); 
     
    if($("input[name='member_email']").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
        $("#rememberEmail").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
    }
     
    $("#rememberEmail").change(function(){ // 체크박스에 변화가 있다면,
        if($("#rememberEmail").is(":checked")){ // ID 저장하기 체크했을 때,
            var userInputId = $("input[name='member_email']").val();
            setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
        }else{ // ID 저장하기 체크 해제 시,
            deleteCookie("userInputId");
        }
    });
     
    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
    $("input[name='member_email']").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
        if($("#rememberEmail").is(":checked")){ // ID 저장하기를 체크한 상태라면,
            var userInputId = $("input[name='member_email']").val();
            setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
        }
    });
});
 
function setCookie(cookieName, value, exdays){
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    document.cookie = cookieName + "=" + cookieValue;
}
 
function deleteCookie(cookieName){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}
 
function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}
</Script>
<!--input="button" 누락확인과  submit하기   -->
<script type="text/javascript">
function gosubmit1(){
	
	if(frm.member_email.value == "") {
		alert("email을 입력해 주세요");
		return false;
	}
	if(frm.member_pw.value == "") {
		alert("비밀번호를 입력해 주세요");
		return false;
	}
	if(((frm.member_email.value.indexOf('@'))<=0)&&(frm.member_email.value.indexOf('.')<=0))
	{
		alert("정상적인 email이 아닙니다.");
		frm.member_email.value="";
		frm.member_email.focus();
		return;
	}
	var f = document.frm;
	f.method = "post";
	f.member_email.value = f.member_email.value;
	f.member_pw.value = f.member_pw.value;
	f.action = "/dokky/login.do";
	f.submit();
}
function gosubmit2(){
	var f = document.frm;
	f.method = "post";
	f.action = "/dokky/joinform.do";
	f.submit();
}
function gosubmit3(){
	var f = document.frm;
	f.method = "post";
	f.action = "/dokky/findpwform.do";
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
					<input type="text" style="width:300pt;" name="member_email" id="member_email" value="" placeholder="Email" />
					<br/>
					<input type="password" style="width:300pt;" name="member_pw" id="member_pw" value="" placeholder="Password" />
			
				<!--로그인 버튼 -->
					<br/>
					<input type="button" value="로그인" style="width:300pt;" class="button special" onclick="javascript:gosubmit1()">
				<!--체크박스 -->
				<br/>
				<div class="6u 12u$(xsmall)" style="float:left">
					<input type="checkbox" id="rememberEmail" name="rememberEmail" >
					<label for="rememberEmail">이메일 저장</label>
				</div> 
				<br/>
				<hr style="margin:0 auto;width:300pt;">					
				<!--helper-->
					<a href="javascript:gosubmit2()" class="pagination">회원가입</a> | <a href="javascript:gosubmit3()">비밀번호찾기</a>
					<br/>
					<br/>
					 <a href="${url}"><img src="/dokky/resources/images/네이버 아이디로 로그인_완성형_Green.PNG" height="50" width="170"></a>
				</div>		
		   </div>
			</form>
		 </div> 
	</div>
	</body>
</html>