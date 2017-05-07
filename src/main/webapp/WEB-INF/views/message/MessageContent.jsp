<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>DOKKY</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<script type="text/javascript">
	function gosubmit1() {

		var f = document.frm;
		f.name_from.value;
		f.method = "post";
		f.action = "/dokky/messagewriteform.do";
		f.submit();
	}
</script>
</head>
<body>
	<h4>MESSAGE</h4>
	<section id="banner">
		<div class="content" align="center">
			<div class="table-wrapper">
				<div class="table-wrapper" style="float: inherit;">
					<form name="frm">
						<input type="hidden" name="name_from" id="name_from" value="${message.name_from}">
						<table class="alt">
							<colgroup>
								<col width="100%" />
								
							</colgroup>

							<tbody>
								<tr>
									<td colspan="2">
										<h2>${message.message_subject}</h2> ${message.name_from} <br />
										<fmt:formatDate value="${message.message_date}"
											pattern="yyyy.MM.dd" />
									</td>
								</tr>
								<tr>
									<td>
										<p>
										<H5>${message.message_content}</H5>
										</p>
									</td>

								</tr>

							</tbody>
						</table>
						<center>
							<input type="button" style="width: 300pt;" value="답장"
								class="button special" onclick="javascript:gosubmit1()">
						</center>
					</form>
					<table class="alt">
						<colgroup>
							<col width="85%" />
							<col width="15%" />
						</colgroup>
					</table>

				</div>

			</div>
		</div>
	</section>
</body>
</html>