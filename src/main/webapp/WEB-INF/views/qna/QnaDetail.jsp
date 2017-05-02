<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp" %>
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


<body>
				<h4>Q&A</h4>

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
											<td colspan="2"><strong><a href="#">${detail.board_id }</a></strong>
												<br> <i><fmt:formatDate value="${detail.board_date}" pattern="yyyy.MM.dd hh:mm" /></i></td>
										</tr>
										<tr>
											<td><h2>
												${detail.board_title }
												</h2>
												<hr class="major" />

												<p>
												${detail.board_content }
					
												</p>
												<div align="right">
												<a href="#" class="button special small">수정</a>
														<a href="#" class="button small">삭제</a>
												</div>
												</td>
											<td><center>
													<a href="#" style="font-size: 30px"
														class="icon fa-thumbs-up"><br>10<!-- 여기에추천수 --></a><br>
													<a href="#" style="font-size: 30px"
														class="icon fa-bookmark"><br>10<!-- 여기에스크랩수 --></a>
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
											<td colspan="2">댓글 3<!-- 코멘트수 -->&nbsp;&nbsp;&nbsp;
										</tr>
										<tr>
											<td colspan="2"><strong><a href="#">ID<!-- 여기에 댓글작성자 아이디 --></a></strong>
												<i>2017.4.27<!-- 여기에 댓글 작성일 --></i>

												<p>
													QHD모니터를 FHD로 설정해서 게임하면 배수가 부족해서 도트가 어그러지지만 UHD에서 FHD 설정하면
													상당히 괜찮습니다. 어색함이 거의 없어요.
													<!-- 여기에 댓글내용 -->
												</p></td>
										</tr>
										<tr>
											<td colspan="2"><strong><a href="#">EEE222<!-- 여기에 댓글작성자 아이디 --></a></strong>
												<i>2017.4.27<!-- 여기에 댓글 작성일 --></i>

												<p>
													1920 1200 24인치~27인치 추천합니다. 적당히 쓰실거면 15만언 아래로 저가 브랜드 쓰셔도 되고,
													세로로 돌리고 싶으면 델 모니터가 가장 무난하다고 생각합니다. 눈 안 아프고 코드 7줄 정도 더
													보이는거.. 은근히 좋습니다 ㅎㅎ
													<!-- 여기에 댓글내용 -->
												</p></td>
										</tr>
										<tr height="50px">
											<td valign="middle" colspan="2" align="center">
											
											이전 1 2 3 4 5 다음
											
											</td>
										</tr>
										<tr height="50px">
											<td valign="middle" colspan="2"><center>
													<a href="#">로그인</a>을 하시면 댓글을 등록할 수 있습니다.
												</center></td>
										</tr>
										<tr>
											<td><textarea cols="20" rows="3">댓글을 적어주세요</textarea></td>
											<td valign="middle"><center><input type="submit" value="작성"
												class="button special"></center></td>
										</tr>
									</tbody>
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