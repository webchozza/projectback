<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>

<html>

<head>
<title>DOKKY</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />

</head>
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

function recommendcheck(checkValue){
	//추천하지 않은 글이면 클릭 가능
	var strA = '<a href="/dokky/bcodedetail.do?board_id=${detail.board_id}&currentPage=${currentPage}&session_id=${sessionScope.member_id}&board_like=${detail.board_id}" style="font-size: 30px" class="icon fa-thumbs-up"></a>';
		strA += '<h2 style="color: #7f888f;">${detail.board_like}</h2>';
	//추천한 글이면 클릭 불가능
	var strDiv = '<div style="font-size: 30px; color: #f56a6a;" class="icon fa-thumbs-up"></div>';
		strDiv += '<h2 style="color: #f56a6a;">${detail.board_like}</h2>';
		
	var strDivNo = '<div style="font-size: 30px; color: #7f888f;" class="icon fa-thumbs-up"></div>';
		strDivNo += '<h2 style="color: #7f888f;">${detail.board_like}</h2>';
		
	if(checkValue == -1){
		$("#recommendbutton").html(strDivNo);
	} else if(checkValue > 0){
		$("#recommendbutton").html(strDiv);
	} else {
		$("#recommendbutton").html(strA);
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
		if (confirm("정말 삭제하시겠습니까?") == false) {
			return false;
		}
	}
	function gosubmit1_message() {

		var f = document.frn;
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
				$("#tags").append('<a class="tagcode" href=taglist.do?tag='+urlencode(sep[i])+'&sort=>'+sep[i]+'</a>');
			}else{
				$("#tags").append('<a class="tagcode" href=taglist.do?tag='+urlencode(sep[i])+'&sort=>'+sep[i]+'</a>&nbsp; ');
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
		recommendcheck("${recommendCheck}");
	});

	$(document).ready(function() {
		viewTags("${detail.board_tag}");
	});
</script>
<style>
.fa-bookmark {
	color: #7f888f;
}
.fa-thumbs-up{
	color: #7f888f;
}

.tagcode {
	background: rgba(230, 235, 237, 0.25);
	border-radius: 0.375em;
	border: solid 1px rgba(210, 215, 217, 0.75);
	font-family: Malgun Gothic;
	font-size: 0.9em;
	margin: 0 0.25em;
	padding: 0.25em 0.65em;
	color: #7f888f;
	
}
</style>
<body>
	<h4>Open Source</h4>

	<!-- 바디 -->
	<section id="banner">
		<div class="content">
			<div class="table-wrapper">
				<div class="table-wrapper">
					<table class="alt">
						<colgroup>
							<col width="80%" />
							<col width="20%" />
						</colgroup>
						<tbody>
						
								<tr>
								<td colspan="2">작성자 :&nbsp;&nbsp;<strong><a href="/dokky/MemberPage.do?member_id=${detail.member_id }&session_id=${sessionScope.member_id}">${detail.board_nickname }</a></strong>
								<a href="javascript:gosubmit1_message()" class="icon fa-envelope">쪽지</a>
								<br>
								작성일 :&nbsp;&nbsp;<fmt:formatDate value="${detail.board_date}" pattern="yyyy.MM.dd" /><!-- 여기에 작성일 --></td>
								</tr>
								<tr>
								<td colspan="2">첨부 파일 :&nbsp;&nbsp;<strong><a href="/dokky/bcodedownload.do?board_id=<c:out value="${detail2.board_id}"/>">${detail2.bfile_src}<!-- 여기에 업로드파일 --></a></strong>
								</tr>
								
								  
								    
								    
								<tr>
									<!-- 여기에 글제목 -->
									<td><h3>제목 :&nbsp;&nbsp;${detail.board_title }</h3>
										<hr class="major" /> 

									<!-- 여기에 글내용 -->
									<p>${detail.board_content}</p>
									<div align="right">
									   
									</div>
									</td>
									
									
									<td><center>
											<div id="recommendbutton"></div>
											<div id="scrapbutton"></div>
										</center>
								</tr>
								<tr>
									<td colspan="2"><div id="tags"></div></td>
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
								<td colspan="2">댓글<!-- 코멘트수 -->&nbsp;&nbsp;&nbsp;
							</tr>
							<c:forEach var="detail3" items="${detail3 }">
								<tr>
									<td colspan="2"><strong><a href="MemberPage.do?member_id=${detail3.member_id }">${detail3.member_name}</a></strong>
										<i><fmt:formatDate value="${detail3.bcomment_date }" pattern="yyyy.MM.dd hh:mm" /></i>&nbsp;&nbsp;&nbsp; 
										<c:if test="${sessionScope.member_email ne null}">
											<c:if test="${sessionScope.member_id==detail.member_id}">

												<a href="/dokky/bcodedeletecomment.do?board_id=${detail3.board_id}&session_id=${sessionScope.member_id}&bcomment_id=${detail3.bcomment_id}"
													class="icon fa-trash"
													style="font-size: 14px; color: #7f888f"
													onclick="return deleteconfirm()"></a>
											</c:if>
										</c:if>
										<p>${detail3.bcomment_content }</p></td>
								</tr>
							</c:forEach>


							<c:if test="${sessionScope.member_email ne null}">
								<form name="frm" action="bcodedetail.do"
									onsubmit="return gosubmit1()">
									<tr>
										<td><textarea cols="20" rows="3" name="bcomment_content"
												placeholder="댓글을 입력해주세요."></textarea></td>
										<td valign="middle"><center>
												<input type="hidden" name="member_id"
													value="${sessionScope.member_id}"> <input
													type="hidden" name="board_id"
													value="${detail.board_id}"><input
													type="hidden" name=currentPage value="${currentPage}"><input
													type="submit" value="작성" class="button special">
													<input type="hidden" name="session_id" value="${sessionScope.member_id}">
											</center></td>
									</tr>
								</form>
							</c:if>
							<c:if test="${sessionScope.member_email eq null}">
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
											<c:if test="${sessionScope.member_id==detail.member_id }">
												<a href="bcodewrite.do?board_id=${detail.board_id }"
													class="button">수정</a>
										<a href="/dokky/bcodedelete.do?board_id=<c:out value="${detail.board_id}"/>&session_id=${sessionScope.member_id}" onclick="return deleteconfirm()" class="button">삭제</a>
											</c:if>
										</c:if>
										<a href="bcodelist.do?currentPage=${currentPage }" class="button special">목록</a>
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
		<input type="hidden" id="board_id" value="${detail.board_id}" />
		<input type="hidden" id="session_id" value="${sessionScope.member_id}" />
	</form>
</body>
</html>