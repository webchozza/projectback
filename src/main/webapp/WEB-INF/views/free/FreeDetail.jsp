<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp"%>
<!DOCTYPE HTML>

<html>

<head>
<title>DOKKY</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="assets/css/main.css" />
<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
</head>
<script type="text/javascript">
	function gosubmit1() {
		if (frm.bcomment_content.value == ""||frm.bcomment_content.value == null) {
			alert("댓글을 입력해주세요.");
			frm.bcomment_content.focus();
			return false;
		}
	}
	
	function insertScrap(){
		
		var member_id = ${sessionScope.member_id};
		var board_id = ${bfreeDetail.board_id}; 

		$.ajax({
			url:"/dokky/ScrapInsert.do",
			type: "get",
			dataType: "json",
			data: {board_id: board_id, member_id: member_id},
			success: function(data){
					scrapcheck(data);
				}
				//여기에 스크랩 버튼 클릭 안되게 하는 로직 처리
				//팔로우처럼 체크값을 이용해 해당 글의 스크랩 버튼이 안눌리게 하는 함수를 작성하여 data를 인자로 넘긴다.(상세보기 컨트롤러에서도 체크값 넘겨야 함)
		});
	}
	
	function scrapcheck(checkValue){
		//스크랩하지 않은 글이면 클릭 가능
		var strA = '<a href="javascript:;" style="font-size: 30px" class="icon fa-bookmark" onclick="insertScrap()"></a>';
			strA += '<h2 style="color: #f56a6a;">0</h2>';
		//스크랩한 글이면 클릭 불가능
		var strDiv = '<div style="font-size: 30px; color: #f56a6a;" class="icon fa-bookmark"></div>';
			strDiv += '<h2 style="color: #f56a6a;">0</h2>';
			
		if(checkValue == 1){
		$("#scrapbutton").html(strDiv);
		} else {
		$("#scrapbutton").html(strA);
		}
	}	
	
	$(document).ready(function(){
		scrapcheck("${scrapCheck}");
	});
</script>
<body>
	<h4>Community</h4>

	<!-- 바디 -->
	<section id="banner">
		<div class="content">
			<div class="table-wrapper">
				<div class="table-wrapper">
					<table class="alt">
						<colgroup>
							<col width="85%" />
							<col width="15%" />
						</colgroup>
						<tbody>
							<tr>
								<td colspan="2"><strong><a href="#">${bfreeDetail.board_nickname }</a></strong>
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
									</center>
							</tr>
						</tbody>

					</table>

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
										href="bfreedeletecomment.do?bcomment_id=${clist.bcomment_id }&board_id=${bfreeDetail.board_id}&currentPage=${currentPage}"
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
											<input type="text" name="member_id" placeholder="멤버번호"><input
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
</body>
</html>