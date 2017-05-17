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
<script>
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

function codelist(line1){
	console.log("통신시작");
	$("#codearea").load("/dokky/bcodelist.do",{line: line1, ap: "AjaxCode"},function(){
		console.log("통신 성공"); });
	}
	
/* 	function example(){
		var line1 = $("#line").val(); */
		
	/* $.ajax({
		url:"/dokky/bcodelist.do",
		type:"post",
		dataType:"json"
		data: {line: line1, ap:"AjaxCode"},
		success: function(data,textStatus, jqXhr)}//data=응답받은 결과의 데이터, textStatus 상태코드, jqXhr xmlhttrequest객체에 jQuery
			//통신 성공했을 때 내용 실행
			var str = "<div class=\"red\">"+data.~~~/+"</div>";
			
			$("#codearea").html(str);
		},
		error: function(data,a,xhr){
			//통신 실패했을 때 실행
		}
	}); */
	
/* 	location.href="";//페이지 이동
	
	var form = document.eongoo;
	form.session_id.value = form.session_id.value;
	form.email.value = ~~~;
	form.method = "post";
	form.action = "".
	form.submit();
	
	<form name="eongoo">
	<input type="hidden" name="session_id" value="${sessionScope.member_id}"/>
	<input type="text" name="email"/>
	</form> */
</script>
<title>DOKKY</title>
<style>
input[name=ss] {
	display: none;
}
</style>
</head>
<body>
<div id="codearea">
	<h4>Open Source</h4>

	<!-- 바디 -->
	<section id="banner">
		<div class="content">
			<div class="table-wrapper">
				<table>
					<colgroup>
						<col width="15%" />
						<col width="40%" />
						<col width="15%" />
						<col width="15%" />
						<col width="10%" />
						<col width="10%" />
					</colgroup>

					<!-- 최신순 댓글순 조회순 추천순 스크랩순 -->
					<thead>
					<c:if
									test="${sessionScope.member_email ne null}">
							  <a href="/dokky/bcodewrite.do" type="submit" class="button special"><i class="icon fa-pencil">글쓰기</i></a></c:if>
						<tr>
							<td colspan="7">
							<a href='javascript:;' onclick='codelist("1")'>최신순</a>
							<a href='javascript:;' onclick='codelist("2")'>댓글순</a>
							<a href='javascript:;' onclick='codelist("3")'>조회순</a> 
							<a href='javascript:;' onclick='codelist("4")'>추천순</a>
							<a href='javascript:;' onclick='codelist("5")'>스크랩순</a>
							</td>
						</tr>
					</thead>
					<!-- 최신순 댓글순 조회순 추천순 스크랩순 끝 -->

					<tbody>
						<tr>
							<td width="10%" align="center" style="min-width:60px;">번호</td>
							<td width="15%" align="center" style="min-width:60px;">제목</td>
							<td width="10%" align="center" style="min-width:60px;">글쓴이</td>
							<td width="10%" align="center" style="min-width:60px;">날짜</td>
							<td width="10%" align="center" style="min-width:60px;">조회</td>
							<td width="10%" align="center" style="min-width:60px;">추천</td>
						</tr>

						<!-- 여기서부터 forEach var들어가야되 -->
						<c:forEach var="board" items="${list}">
						<c:url var="detailURL" value="bcodedetail.do">
								<c:param name="board_id" value="${board.board_id}" />
								<c:param name="member_id" value="${sessionScope.member_id}" />
							</c:url>
						 <c:if test="${board.board_id ne null}">
							<tr>
								<!-- 1 -->
								<td width="10" align="center">${board.board_id}</td>
								<!-- 2 -->
								<td width="10" align="center"><a href="${detailURL}">${board.board_title}</a></td>
								<!-- 3 -->
								<td width="10" align="center"><a href="#">${board.board_nickname}</a></td>
								<!-- 4 -->
								<td width="10" align="center"><fmt:formatDate
										value="${board.board_date}" pattern="yyyy.MM.dd" /></td>
								<!-- 5 -->
								<td width="10" align="center">${board.board_hit}
								<!-- <a class="icon fa-comment">3</a>&nbsp;&nbsp;&nbsp;
								<a class="icon fa-thumbs-up">10</a>&nbsp;&nbsp;&nbsp; 
								<a class="icon fa-eye">10</a> -->
								</td>
								<!-- 6 -->
								<td width="10" align="center">${board.board_like}</td>
							</tr>
							</c:if>
						</c:forEach>
						
						<%-- <c:forEach var="searchlist" items="${searchlist}">
						 <c:if test="${searchlist.board_id ne null}">
							<tr>
								<!-- 1 -->
								<td width="10" align="center">${searchlist.board_id}</td>
								<!-- 2 -->
								<td width="10" align="center"><a href="/dokky/bcodedetail.do?board_id=<c:out value="${searchlist.board_id}"/>">${searchlist.board_title}</a></td>
								<!-- 3 -->
								<td width="10" align="center"><a href="#">${searchlist.board_nickname}</a></td>
								<!-- 4 -->
								<td width="10" align="center"><fmt:formatDate
										value="${searchlist.board_date}" pattern="yyyy.MM.dd" /></td>
								<!-- 5 -->
								<td width="10" align="center">${searchlist.board_hit}
								<!-- <a class="icon fa-comment">3</a>&nbsp;&nbsp;&nbsp;
								<a class="icon fa-thumbs-up">10</a>&nbsp;&nbsp;&nbsp; 
								<a class="icon fa-eye">10</a> -->
								</td>
								<!-- 6 -->
								<td width="10" align="center">추천</td>
							</tr>
							</c:if>
						</c:forEach> --%>

					</tbody>

					<!-- 여기까지 forEach -->
				</table>
			</div>
			<!-- Search 박스 -->
			<div align="center">
				<section id="search" class="alt" style="min-width: 300px; max-width: 600px;">
					<form method="post" action="/dokky/bcodelist.do">
						<input type="text" name="search" id="search" placeholder="Search"
							width="100px" /> <input type="submit" id="search" value="검색" />
					</form>
				</section>
			</div>
			<!--  -->
		</div>
	</section>
	<%-- 	
	        <!--ㅡㅡㅡㅡㅡ paging ㅡㅡㅡㅡㅡ-->
			<div><p id="cm" class="hc vc">${page}</p></div>
			<style>
			div { position:relative; } 
			#cm { position:absolute; } 
			.hc { width:10%; left:0; right:0; margin-left:50%; margin-right:auto; }
			.vc { height:3%; top: 0; bottom:0; margin-top:auto; margin-bottom:auto; }
			</style>
			<!--ㅡㅡㅡㅡㅡ paging ㅡㅡㅡㅡㅡ-->
			</div> --%>
			</div>
</body>
</html>