<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<script src="${pageContext.request.contextPath}/resources/assets/js/free/freedetail.js"></script>
<title>DOKKY</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
<script type="text/javascript">
	function gosubmit1() {
		if (frm.bcomment_content.value == ""||frm.bcomment_content.value == null) {
			alert("댓글을 입력해주세요.");
			frm.bcomment_content.focus();
			return false;
		}
	}

	function gosubmit1() {

		var f = document.frn;
		f.name_from.value;
		f.method = "post";
		f.action = "/dokky/messagewriteform.do";
		f.submit();
	}
	
	$(document).ready(function(){
		scrapcheck("${scrapCheck}");
	});
</script>
<style>
.fa-bookmark{
color: #7f888f; 
}
</style>
</head>
<body>
	<h4>Community</h4>

	<!-- 바디 -->
	<section id="banner">
		<div class="content">
			<div class="table-wrapper">
				<div class="table-wrapper">
				<form name="frn">
				<input type="hidden" name="name_from" id="name_from" value="${bfreeDetail.board_nickname}">
					<table class="alt">
						<colgroup>
							<col width="85%" />
							<col width="15%" />
						</colgroup>
						
						<tbody>
						
							<tr>
								<td colspan="2"><strong><a href="/dokky/MemberPage.do?member_id=${bfreeDetail.member_id}&session_id=${sessionScope.member_id}">${bfreeDetail.board_nickname}</a></strong>
									<br> <i><fmt:formatDate
											value="${bfreeDetail.board_date }" pattern="yyyy.MM.dd hh:mm" />
								</i></td>
							</tr>
							<tr>
								<td><h2>${bfreeDetail.board_title }</h2>
									<hr class="major" />

									<p>${bfreeDetail.board_content }</p></td>
								<td><center>
										<a href="#" style="font-size: 30px" class="icon fa-thumbs-up"></a><br><h2 style="color: #f56a6a;">${bfreeDetail.board_like}</h2><br>
										<div id="scrapbutton"></div>
										<input type="button" style="font-size: 30px" value="쪽지" class="button special" onclick="javascript:gosubmit1()">
									</center>
							</tr>
							
						</tbody>

					</table>
					</form>
					<table class="alt">
						<colgroup>
							<col width="85%" />
							<col width="15%" />
						</colgroup>
						<tbody>
							<tr>
								<td colspan="2">댓글 ${bfreeDetail.board_comment_count }&nbsp;&nbsp;&nbsp;
							</tr>
							<c:forEach var="clist" items="${bcfreeList }">
								<tr>
									<td colspan="2"><strong><a href="#">${clist.member_id }<!-- 수정필요 --></a></strong>
										<i><fmt:formatDate value="${clist.bcomment_date }" pattern="yyyy.MM.dd hh:mm" /></i>&nbsp;&nbsp;&nbsp;<a
										href="bfreedeletecomment.do?bcomment_id=${clist.bcomment_id }&board_id=${bfreeDetail.board_id}&currentPage=${currentPage}&session_id=${sessionScope.member_id}"
										class="icon fa-trash" style="font-size: 14px; color: #7f888f"></a>
										<p>${clist.bcomment_content }</p></td>
								</tr>
							</c:forEach>
							<tr height="50px">
								<td valign="middle" colspan="2"><center>
										<a href="#">로그인</a>을 하시면 댓글을 등록할 수 있습니다.
									</center></td>
							</tr>
							<form name="frm" action="bfreewritecomment.do" onsubmit="return gosubmit1()">
								<tr>
									<td><textarea cols="20" rows="3" name="bcomment_content"
											placeholder="댓글을 입력해주세요."></textarea></td>
									<td valign="middle"><center>
											<input type="hidden" name="member_id" value="${sessionScope.member_id}"><input
												type="hidden" name="board_id"
												value="${bfreeDetail.board_id}"><input type="hidden"
												name=currentPage value="${currentPage}"><input
												type="submit" value="작성" class="button special">
										</center></td>
								</tr>
							</form>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2" align="right">
									<div align="right">
										<a href="bfreemodifyform.do?board_id=${bfreeDetail.board_id }&currentPage=${currentPage}" class="button">수정</a> <a
											href="bfreedelete.do?board_id=${bfreeDetail.board_id }&currentPage=${currentPage}"
											class="button">삭제</a> <a
											href="bfreelist.do?currentPage=${currentPage }"
											class="button special">목록</a>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>

				</div>

			</div>
		</div>
	</section>
<form name="valueform">
<input type="hidden" id="board_id" value="${bfreeDetail.board_id}"/>
<input type="hidden" id="session_id" value="${sessionScope.member_id}"/>
</form>
</body>
</html>