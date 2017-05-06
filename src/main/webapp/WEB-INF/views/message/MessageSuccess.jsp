<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
	f.action = "/dokky/massagelist.do";
	f.submit();
}
</script>
</head>


<body>
<br/><br/>
	<div id="wrapper">
		<div id="main" align="center">
			<div class="inner">
	<!-- Message Success -->
			<form name="frm">			
			<h3>MESSAGE</h3>
					<div class="box" style="width:400pt;">
					회원님의 쪽지가  성공적으로 전송되었습니다..<br/>
					<br/>
				<input  type="button" style="width:300pt;" value="목록으로 이동" class="button special" onclick="javascript:gosubmit1()">
					</div>
				</form>
				</div>		
		   </div>
		 </div> 
	</body>
</html>