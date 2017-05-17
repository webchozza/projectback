<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<title>JoinForm</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script> 
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript"
	src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>
<script>
	$(function() {

		var siteKey = "6Ld2OCEUAAAAAI2bxjB941hYFH3QgpCU4hOYbcwm";//Site key
		var div = "recaptcha";
		Recaptcha.create(siteKey, div, {
			
		});
		$("#recaptchaCheck")
				.click(
				function() {

				var challenge = Recaptcha.get_challenge();
				var response = Recaptcha.get_response();
			    var host = $(location).attr('host');

							$.ajax({
								type : "POST",
								url : "/dokky/validateRecaptcha.do",
								async : false,
								data : {
								host : host,
								challenge : challenge,
								response : response
										},
								success : function(data) {
								if (data == "Y") {
								document.getElementById("checkResult").value = "Y";
								alert("인증이 완료되었습니다.")
											} else {
								document.getElementById("checkResult").value = "N";
								alert("인증에 실패하였습니다. 다시 시도해 주세요")
								/* Recaptcha.reload(); */
											}
										}
									});

						});

	});
</script>
<!--비밀번호 확인  -->
<script>
	$(function() {
		$('#member_pw').keyup(function() {
			$('font[name=check]').text('');
		}); //#password.keyup

		$('#chpass').keyup(function() {
			if ($('#member_pw').val() != $('#chpass').val()) {
				$('font[name=check]').text('');
				$('font[name=check]').html("비밀번호가 일치하지 않습니다.");
			} else {
				$('font[name=check]').text('');
				$('font[name=check]').html("비밀번호가 일치합니다.");
			}
		}); //#chpass.keyup
	});
</script>
<!--input="button" 누락확인과  submit하기   -->
<script type="text/javascript">
	function gosubmit1() {

		if (frm.member_name.value == "") {
			alert("이름을 입력해 주세요");
			return false;
		}
		if (frm.member_email.value == "") {
			alert("email을 입력해 주세요");
			return false;
		}
		if (frm.member_pw.value == "") {
			alert("비밀번호를 입력해 주세요");
			return false;
		}
		if (frm.chpass.value == "") {
			alert("비밀번호를 확인해주세요");
			return false;
		}
		if (frm.member_pw.value != frm.chpass.value) {
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
		if ($("#member_email").val() == '') {
			alert('이메일을 입력해주세요.');
			return;
		}
			
		if ($("#checkResult").val() == 'N') {
			alert('인증 확인해주세요.');
			return;
		}
		var f = document.frm;
		f.method = "POST";
		f.member_name.value = f.member_name.value;
		f.member_email.value = f.member_email.value;
		f.member_pw.value = f.member_pw.value;

		f.action = "/dokky/sendemail.do";
		f.submit();
	}
	function gosubmit2() {
		var f = document.frm;
		f.method = "post";
		f.action = "/dokky/main.do";
		f.submit();
	}
	function gosubmit3() {
		var prmName = $('#member_name').val();
		$.ajax({
			type : 'GET',
			data : {
				member_name : prmName
			},
			dataType : 'text',
			url : '/dokky/checkname.do',
			success : function(rData, textStatus, xhr) {
				var chkRst = rData;
				if (chkRst == "true") {
					alert("등록 가능 합니다.");
					$("#nameChk").val('true');
				} else {
					alert("중복 되어 있습니다.");
					$("#nameChk").val('false');
				}
			}
		});
	}
	function gosubmit4() {
		if (((frm.member_email.value.indexOf('@')) <= 0)
				&& (frm.member_email.value.indexOf('.') <= 0)) {
			alert("정상적인 email이 아닙니다.");
			frm.member_email.value = "";
			frm.member_email.focus();
			return;
		}
		var prmEmail = $('#member_email').val();

		if ($("#member_email").val() == '') {
			alert('이메일을 입력해주세요.');
			return;
		}

		$.ajax({
			type : 'GET',
			data : {
				member_email : prmEmail
			},
			dataType : 'text',
			url : '/dokky/checkemail.do',
			success : function(rData, textStatus, xhr) {
				var chkRst = rData;
				if (chkRst == "true") {
					alert("등록 가능 합니다.");
					$("#emailChk").val('true');
				} else {
					alert("중복 되어 있습니다.");
					$("#emailChk").val('false');
				}
			}
		});
	}

	function len_chk() {

		var frm = document.frm.member_pw;

		if (frm.value.length > 10) {

			alert("비밀번호는 숫자 10자리 미만으로 제한합니다.");
			frm.value = frm.value.substring(0, 10);
			frm.focus();
		}
	}
	function len_chkk() {

		var frm = document.frm.chpass;

		if (frm.value.length > 10) {

			msg = "비밀번호는 6 ~ 16 자리로 입력해주세요.";
			frm.value = frm.value.substring(0, 10);
			frm.focus();
		}

		return msg;
	}

</script>
<!--사진 보이기  -->
<script type="text/javascript">
        $(function() {
            $("#imgInp").on('change', function(){
                readURL(this);
            });
        });

        function readURL(input) {
            if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                    $('#blah').attr('src', e.target.result);
                }

              reader.readAsDataURL(input.files[0]);
            }
        }


    </script>
</head>

<body>
	<div id="wrapper">
		<div id="main" align="center">
			<div class="inner">
				<!-- Join Form -->

				<h3>회원가입</h3>
				<form name="frm" action="/dokky/sendemail.do" method="post" enctype="multipart/form-data">
					<input type="hidden" id="checkResult" name="checkResult" value="N" />
					<div class="row uniform" style="float: inherit;">
						<div class="6u 12u$(xsmall)" style="float: inherit;">
							<input type="hidden" id="nameChk" value="false" /> 
							<input type="hidden" id="emailChk" value="false" /> 
								<!--사진바로 보기  -->
									<!-- <ul class="actions" style="float: left;">
      							  <img id="blah" src="#" alt=""  width="200px" height="150px"/>
      							  </ul>
      								&nbsp;프로필로 사용할 이미지를 선택해 주세요.
      								<input type='file' id="imgInp" />
      								<br/><br/><br/> -->
								<input type="text" name="member_name" id="member_name" value="${member_name}" placeholder="닉네임" />
							<ul class="actions" style="float: left;">
								<li><input type="button" value="중복확인"
									class="button special" onclick="javascript:gosubmit3()"></li>
							</ul>
							<br /> <input type="text" name="member_email" id="member_email"
								value="${member_email}" placeholder="DOKKY에서 사용할 이메일을 입력해주세요">
							<ul class="actions" style="float: left;">
								<li><input type="button" value="중복확인"
									class="button special" onclick="javascript:gosubmit4()"></li>
							</ul>
							<input type="password" style="width: 500;" name="member_pw"
								id="member_pw" value="" placeholder="비밀번호" onKeyup="len_chk()" />
							<br /> <input type="password" style="width: 500;" name="chpass"
								id="chpass" value="" placeholder="비밀번호 확인" onKeyup="len_chkk()" />
							<font name="check" size="2" style="float: left" color="pink"></font>
							
							<div id="message" style="color: #ff0000;"></div><br/>
							<div class="box">
							<strong>자동등록방지</strong>를 위해  보안절차를 거치고 있습니다.<br/>
							<div id="recaptcha" style="margin-left:-35%; width:50%; heigth:50%;"></div>
							<input id="recaptchaCheck" type="button" class="button special" value="보안문자인증하기" >
							</div>
							<div>
							
							<ul class="actions">
								<input type="button" value="아래 약관을 동의하며 회원가입" class="button special"
									onclick="javascript:gosubmit1()"><br/>
<a href=""onclick="window.open('/dokky/permit1.do','window','location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, width=500,height=700,left=0, top=0, scrollbars=yes');return false">
회원가입약관</a> / <a href=""onclick="window.open('/dokky/permit2.do','window','location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, width=500,height=700,left=0, top=0, scrollbars=yes');return false">
개인정보취급방침</a>
						
							</ul>
							
							</div>

						</div>

					</div>
				</form>

			</div>
		</div>
	</div>
</body>
</html>