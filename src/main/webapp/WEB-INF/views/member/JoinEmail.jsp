<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html>
<head>
<title>JoinEmail</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<!--input="button" 누락확인과  submit하기   -->
<script type="text/javascript">
function gosubmit1(){
	var f = document.frm;
	f.method = "post";
	f.action = "join.do";
	f.submit();
}
</script>
</head>


<body>
	<div id="wrapper">
		<div id="main" align="center">
			<div class="inner">
	<!-- JoinEmail -->			
			<h3>회원가입</h3>
					<div class="box" style="width:400pt;">
					감사합니다 !<br/>
					회원님의 이메일로 회원가입 인증 메일이 발송되었습니다.  <br/>
					메일을 확인해 주세요. ^^
					<br/>
				<input  type="button" style="width:300pt;" value="메인화면" class="button special" onclick="javascript:gosubmit1()">
					</div>
				</div>		
		   </div>
		 </div> 
	</body>
</html>