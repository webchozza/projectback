<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page session="false"%> 
<% HttpSession session = request.getSession(); 
if (session!=null) 
{ session.invalidate(); } %>
<html>
<head>
<title>FindEmail</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<!--input="button" 누락확인과  submit하기   -->
<script type="text/javascript">
function gosubmit1(){
	var f = document.frm;
	f.method = "post";
	f.action = "/dokky/loginform.do";
	f.submit();
}
</script>
</head>


<body>
<br/><br/>
	<div id="wrapper">
		<div id="main" align="center">
			<div class="inner">
	<!-- ModifySuccess -->
			<form name="frm">			
			<h3>회원정보 수정</h3>
					<div class="box" style="width:400pt;">
					감사합니다 !<br/>
					회원님의 정보가 수정되었습니다.<br/>
					변경하신 패스워드로 다시 로그인을 해주세요 ^^
					<br/>
				<input  type="button" style="width:300pt;" value="로그인" class="button special" onclick="javascript:gosubmit1()">
					</div>
				</form>
				</div>		
		   </div>
		 </div> 
	</body>
</html>