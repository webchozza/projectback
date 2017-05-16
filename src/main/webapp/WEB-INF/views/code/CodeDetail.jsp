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
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="assets/css/main.css" />
<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
</head>
<script type="text/javascript">
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

		var f = document.frn;
		f.name_from.value;
		f.method = "post";
		f.action = "/dokky/messagewriteform.do";
		f.submit();
	}

	$(document).ready(function() {
		scrapcheck("${scrapCheck}");
	});
</script>

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
									<td colspan="2"><strong><a href="/dokky/MemberPage.do?member_id=${detail.member_id }&session_id=${sessionScope.member_id}">${detail.board_nickname }</a></strong>
									<a href="javascript:gosubmit1_message()" class="icon fa-envelope">쪽지</a>
									<br>
									<i><fmt:formatDate value="${detail.board_date}" pattern="yyyy.MM.dd" /><!-- 여기에 작성일 --></i></td>
								</tr>
								<tr>
								    <td colspan="2"><strong><a href="/dokky/bcodedownload.do?board_id=<c:out value="${detail2.board_id}"/>">${detail2.bfile_src}<!-- 여기에 업로드파일 --></a></strong>
								</tr>
								
								<tr>
									<!-- 여기에 글제목 -->
									<td><h2>${detail.board_title }</h2>
										<hr class="major" /> 
									
									<!-- 여기에 글내용 -->
									<p>${detail.board_content}</p>
									<div align="right">
									    <a href="/dokky/bcodewrite.do?board_id=<c:out value="${detail.board_id}"/>">수정</a>
										<a href="#" class="button small">삭제</a>
									</div>
									</td>
									
									<td><center>
											<a href="bcodedetail.do?board_id=${detail.board_id}&board_like=${detail.board_id}" style="font-size: 30px" class="icon fa-thumbs-up"><br>${detail.board_like}<!-- 여기에추천수 --></a><br>
											<a href="#" style="font-size: 30px" class="icon fa-bookmark"><br>10<!-- 여기에스크랩수 --></a>
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
								<td colspan="2">댓글<!-- 코멘트수 -->&nbsp;&nbsp;&nbsp;
							</tr>
							<c:forEach var="detail3" items="${detail3 }">
								<tr>
									<td colspan="2"><strong><a href="MemberPage.do?member_id=${detail3.member_id }">${detail3.member_name}</a></strong>
										<i><fmt:formatDate value="${detail3.bcomment_date }" pattern="yyyy.MM.dd hh:mm" /></i>&nbsp;&nbsp;&nbsp; 
										<c:if test="${sessionScope.member_email ne null}">
											<c:if test="${sessionScope.member_id==detail.member_id}">

												<a href="bcodedetail.do?bcomment_id=${detail3.bcomment_id }&board_id=${detail.board_id}&currentPage=${currentPage}"
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
												<a href="bcodedetail.do?board_id=${detail.board_id }&currentPage=${currentPage}"
													class="button">수정</a>
												<a href="bfreedelete.do?board_id=${detail.board_id }&currentPage=${currentPage}"
													class="button" onclick="return deleteconfirm()">삭제</a>
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
	</div>
	</div>

	<!-- 슬라이드바 -->
	<div id="sidebar">
		<div class="inner">

			<!-- 서치 -->
			<section id="search" class="alt">
				<form method="post" action="#">
					<input type="text" name="query" id="query" placeholder="Search" />
				</form>
			</section>

			<!-- 로그인 회원가입등 -->
			<section id="icons">
				<a href="#" class="icon fa-sign-in"> 로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" class="icon fa-user-plus"> 회원가입</a>
				<!-- 					로그인했으면
					<ul>
						<a href="#" class="icon fa-sign-out">
							로그아웃</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#" class="icon fa-bell">
							알림</a>
					</ul> -->
			</section>

			<!-- 메뉴 -->
			<nav id="menu">
				<ul>
					<li><a href="index.html">메인</a></li>
					<li><a href="generic.html">Q&A게시판</a></li>
					<li><span class="opener">구인구직</span>
						<ul>
							<li><a href="#">구인</a></li>
							<li><a href="#">구직</a></li>
						</ul></li>
					<li><a href="elements.html">자유게시판</a></li>
					<li><a href="elements.html">오픈소스</a></li>
				</ul>
			</nav>
		</div>
	</div>
</body>
</html>