<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>DOKKY</title>
<script type="text/javascript">
function insertScrap(){
	
	var member_id = $("#session_id").val();
	var board_id = $("#board_id").val(); 
	
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
	strA += '<h2 style="color: #7f888f;">스크랩</h2>';
//스크랩한 글이면 클릭 불가능
var strDiv = '<div style="font-size: 30px; color: #f56a6a;" class="icon fa-bookmark"></div>';
	strDiv += '<h2 style="color: #f56a6a;">스크랩</h2>';
	
var strDivNo = '<div style="font-size: 30px; color: #7f888f;" class="icon fa-bookmark"></div>';
	strDivNo += '<h2 style="color: #7f888f;">스크랩</h2>';
	
if(checkValue == -1){
	$("#scrapbutton").html(strDivNo);
} else if(checkValue > 0){
	$("#scrapbutton").html(strDiv);
} else {
	$("#scrapbutton").html(strA);
}
}

	function gosubmit1() {
		if (frm.bcomment_content.value == ""
				|| frm.bcomment_content.value == null) {
			alert("댓글을 입력해주세요.");
			frm.bcomment_content.focus();
			return false;
		}
	}

	function deleteconfirm() {
		var del
		del = confirm("정말 삭제하시겠습니까?")
		if (del == true) {
			return true;
		} else {
			return false;
		}
	}
	function gosubmit1_message() {

		var f = document.note;
		f.name_from.value;
		f.method = "post";
		f.action = "/dokky/messagewriteform.do";
		f.submit();
	}
	
	function viewTags(str) {
		var sep = str.split(",");
		$("#tags").append("<i class='icon fa-tags'></i>");
		for (i=0; i<sep.length; i++) {
			if(i==(sep.length-1)){
				$("#tags").append('<a href=taglist.do?tag='+urlencode(sep[i])+'&sort=>'+sep[i]+'</a>');
			}else{
				$("#tags").append('<a href=taglist.do?tag='+urlencode(sep[i])+'&sort=>'+sep[i]+'</a>, ');
			}
		}
	}
	
	function urlencode(str) {
	    str = (str + '').toString();
	    return encodeURIComponent(str)
	        .replace(/!/g, '%21')
	        .replace(/'/g, '%27')
	        .replace(/\(/g, '%28')
	        .replace(/\)/g, '%29')
	        .replace(/\*/g, '%2A')
	        .replace(/%20/g, '+');
	}

	$(document).ready(function() {
		scrapcheck("${scrapCheck}");
	});

	$(document).ready(function() {
		viewTags("${board_tag}");
	});
</script>

<style>
.fa-bookmark {
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
					<table class="alt">
						<colgroup>
							<col width="85%" />
							<col width="15%" />
						</colgroup>
						<tbody>
							<tr>
								<td colspan="2"><strong><a
										href="/dokky/MemberPage.do?member_id=${bfreeDetail.member_id }&session_id=${sessionScope.member_id}">${bfreeDetail.board_nickname }</a></strong>
									<a href="javascript:gosubmit1_message()"
									class="icon fa-envelope">쪽지</a> <br> <i><fmt:formatDate
											value="${bfreeDetail.board_date }" pattern="yyyy.MM.dd hh:mm" />
								</i></td>
							</tr>
							<tr>
								<td><h2>${bfreeDetail.board_title }</h2>
									<hr class="major" />
									<p>${bfreeDetail.board_content }</p></td>
								<td><center>
										<a
											href="bfreerecommend.do?board_id=${bfreeDetail.board_id }&currentPage=${currentPage}"
											style="font-size: 30px" class="icon fa-thumbs-up"><br>${bfreeDetail.board_like }</a><br>
										<div id="scrapbutton"></div>
									</center>
							</tr>
							<tr>
								<td colspan="2"><div id="tags"></div>
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
									<td colspan="2"><strong><a
											href="MemberPage.do?member_id=${clist.member_id }">${clist.member_name }</a></strong>
										<i><fmt:formatDate value="${clist.bcomment_date }"
												pattern="yyyy.MM.dd hh:mm" /></i>&nbsp;&nbsp;&nbsp; <c:if
											test="${sessionScope.member_email ne null}">
											<c:if test="${sessionScope.member_id==clist.member_id}">

												<a
													href="bfreedeletecomment.do?bcomment_id=${clist.bcomment_id }&board_id=${bfreeDetail.board_id}&currentPage=${currentPage}"
													class="icon fa-trash"
													style="font-size: 14px; color: #7f888f"
													onclick="return deleteconfirm()"></a>
											</c:if>
										</c:if>
										<p>${clist.bcomment_content }</p></td>
								</tr>
							</c:forEach>


							<c:if test="${sessionScope.member_email ne null}">
								<form name="frm" action="bfreewritecomment.do"
									onsubmit="return gosubmit1()">
									<tr>
										<td><textarea cols="20" rows="3" name="bcomment_content"
												placeholder="댓글을 입력해주세요."></textarea></td>
										<td valign="middle"><center>
												<input type="hidden" name="member_id"
													value="${sessionScope.member_id}"> <input
													type="hidden" name="board_id"
													value="${bfreeDetail.board_id}"><input
													type="hidden" name=currentPage value="${currentPage}"><input
													type="submit" value="작성" class="button special">
											</center></td>
									</tr>
								</form>
							</c:if>
							<c:if test="!${sessionScope.member_email ne null}">
								<tr height="50px">
									<td valign="middle" colspan="2"><center>
											<a href="loginform.do">로그인</a>을 하시면 댓글을 등록할 수 있습니다.
										</center></td>
								</tr>
							</c:if>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2" align="right">
									<div align="right">
										<c:if test="${sessionScope.member_email ne null}">
											<c:if
												test="${sessionScope.member_id==bfreeDetail.member_id }">
												<a
													href="bfreemodifyform.do?board_id=${bfreeDetail.board_id }&currentPage=${currentPage}"
													class="button">수정</a>
												<a
													href="bfreedelete.do?board_id=${bfreeDetail.board_id }&currentPage=${currentPage}"
													class="button" onclick="return deleteconfirm()">삭제</a>
											</c:if>
										</c:if>
										<a href='#' onclick='history.go(-1); return false;'
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
	<form name="note">
		<input type="hidden" name="name_from" id="name_from"
			value="${bfreeDetail.board_nickname}">
	</form>

	<form name="valueform">
		<input type="hidden" id="board_id" value="${bfreeDetail.board_id}" />
		<input type="hidden" id="session_id" value="${sessionScope.member_id}" />
	</form>
</body>
</html>