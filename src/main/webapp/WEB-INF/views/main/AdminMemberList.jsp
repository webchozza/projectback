<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<script type="text/javascript">
	var auto_refresh = setTimeout(function() {//현재 접속한 회원을 주기적으로 체크//자기자신의 페이지를 불러오는 로직이기 때문에 interval대신 timeout함수를 재귀를 이용하여 구현
		$('#check').load('AdminMemberList.action').fadeIn("fast");
		setTimeout(auto_refresh, 2000);
	}, 2000);
</script>
<script type="text/javascript">window.NREUM||(NREUM={}),__nr_require=function(e,t,n){function r(n){if(!t[n]){var o=t[n]={exports:{}};e[n][0].call(o.exports,function(t){var o=e[n][1][t];return r(o||t)},o,o.exports)}return t[n].exports}if("function"==typeof __nr_require)return __nr_require;for(var o=0;o<n.length;o++)r(n[o]);return r}({1:[function(e,t,n){function r(){}function o(e,t,n){return function(){return i(e,[(new Date).getTime()].concat(u(arguments)),t?null:this,n),t?void 0:this}}var i=e("handle"),a=e(2),u=e(3),c=e("ee").get("tracer"),f=NREUM;"undefined"==typeof window.newrelic&&(newrelic=f);var s=["setPageViewName","setCustomAttribute","setErrorHandler","finished","addToTrace","inlineHit"],l="api-",p=l+"ixn-";a(s,function(e,t){f[t]=o(l+t,!0,"api")}),f.addPageAction=o(l+"addPageAction",!0),f.setCurrentRouteName=o(l+"routeName",!0),t.exports=newrelic,f.interaction=function(){return(new r).get()};var d=r.prototype={createTracer:function(e,t){var n={},r=this,o="function"==typeof t;return i(p+"tracer",[Date.now(),e,n],r),function(){if(c.emit((o?"":"no-")+"fn-start",[Date.now(),r,o],n),o)try{return t.apply(this,arguments)}finally{c.emit("fn-end",[Date.now()],n)}}}};a("setName,setAttribute,save,ignore,onEnd,getContext,end,get".split(","),function(e,t){d[t]=o(p+t)}),newrelic.noticeError=function(e){"string"==typeof e&&(e=new Error(e)),i("err",[e,(new Date).getTime()])}},{}],2:[function(e,t,n){function r(e,t){var n=[],r="",i=0;for(r in e)o.call(e,r)&&(n[i]=t(r,e[r]),i+=1);return n}var o=Object.prototype.hasOwnProperty;t.exports=r},{}],3:[function(e,t,n){function r(e,t,n){t||(t=0),"undefined"==typeof n&&(n=e?e.length:0);for(var r=-1,o=n-t||0,i=Array(o<0?0:o);++r<o;)i[r]=e[t+r];return i}t.exports=r},{}],ee:[function(e,t,n){function r(){}function o(e){function t(e){return e&&e instanceof r?e:e?c(e,u,i):i()}function n(n,r,o){if(!p.aborted){e&&e(n,r,o);for(var i=t(o),a=v(n),u=a.length,c=0;c<u;c++)a[c].apply(i,r);var f=s[w[n]];return f&&f.push([y,n,r,i]),i}}function d(e,t){b[e]=v(e).concat(t)}function v(e){return b[e]||[]}function g(e){return l[e]=l[e]||o(n)}function m(e,t){f(e,function(e,n){t=t||"feature",w[n]=t,t in s||(s[t]=[])})}var b={},w={},y={on:d,emit:n,get:g,listeners:v,context:t,buffer:m,abort:a,aborted:!1};return y}function i(){return new r}function a(){(s.api||s.feature)&&(p.aborted=!0,s=p.backlog={})}var u="nr@context",c=e("gos"),f=e(2),s={},l={},p=t.exports=o();p.backlog=s},{}],gos:[function(e,t,n){function r(e,t,n){if(o.call(e,t))return e[t];var r=n();if(Object.defineProperty&&Object.keys)try{return Object.defineProperty(e,t,{value:r,writable:!0,enumerable:!1}),r}catch(i){}return e[t]=r,r}var o=Object.prototype.hasOwnProperty;t.exports=r},{}],handle:[function(e,t,n){function r(e,t,n,r){o.buffer([e],r),o.emit(e,t,n)}var o=e("ee").get("handle");t.exports=r,r.ee=o},{}],id:[function(e,t,n){function r(e){var t=typeof e;return!e||"object"!==t&&"function"!==t?-1:e===window?0:a(e,i,function(){return o++})}var o=1,i="nr@id",a=e("gos");t.exports=r},{}],loader:[function(e,t,n){function r(){if(!h++){var e=y.info=NREUM.info,t=l.getElementsByTagName("script")[0];if(setTimeout(f.abort,3e4),!(e&&e.licenseKey&&e.applicationID&&t))return f.abort();c(b,function(t,n){e[t]||(e[t]=n)}),u("mark",["onload",a()],null,"api");var n=l.createElement("script");n.src="https://"+e.agent,t.parentNode.insertBefore(n,t)}}function o(){"complete"===l.readyState&&i()}function i(){u("mark",["domContent",a()],null,"api")}function a(){return(new Date).getTime()}var u=e("handle"),c=e(2),f=e("ee"),s=window,l=s.document,p="addEventListener",d="attachEvent",v=s.XMLHttpRequest,g=v&&v.prototype;NREUM.o={ST:setTimeout,CT:clearTimeout,XHR:v,REQ:s.Request,EV:s.Event,PR:s.Promise,MO:s.MutationObserver},e(1);var m=""+location,b={beacon:"bam.nr-data.net",errorBeacon:"bam.nr-data.net",agent:"js-agent.newrelic.com/nr-998.min.js"},w=v&&g&&g[p]&&!/CriOS/.test(navigator.userAgent),y=t.exports={offset:a(),origin:m,features:{},xhrWrappable:w};l[p]?(l[p]("DOMContentLoaded",i,!1),s[p]("load",r,!1)):(l[d]("onreadystatechange",o),s[d]("onload",r)),u("mark",["firstbyte",a()],null,"api");var h=0},{}]},{},["loader"]);</script>

<link rel="shortcut icon" href="/favicon.ico" />
<link href="//www.facebook.com/tourtipsguide" rel="publisher" />

<title>관리자</title>
<link rel="stylesheet" type="text/css"
	href="//img.tourtips.com/css/pc/common.css?utv=0002217" />
<link rel="stylesheet" type="text/css"
	href="/khtour/css/input.css" />
<link rel="stylesheet" type="text/css"
	href="//img.tourtips.com/css/pc/community.css?utv=0002217" />
	<link rel="stylesheet" type="text/css"
	href="//img.tourtips.com/css/pc/main.css?utv=0002219" />
<link rel="stylesheet" type="text/css"
	href="//img.tourtips.com/css/pc/jquery-ui.css?utv=0002219" />
<script type="text/javascript">
document.domain = "tourtips.com";
</script>
<script type="text/javascript" src="//wcs.naver.net/wcslog.js"></script>
<script type="text/javascript"
	src="//img.tourtips.com/js/cm/jquery-1.11.2.min.js?utv=0002217"></script>
<script type="text/javascript"
	src="//img.tourtips.com/js/cm/common.js?utv=0002217"></script>
<script type="text/javascript" src="/css/js"></script>
<script type="text/javascript"
	src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js?utv=0002217"></script>
<script type="text/javascript"
	src="//img.tourtips.com/js/cm/jquery-ui.js?utv=0002219"></script>
<script type="text/javascript"
	src="//img.tourtips.com/js/cm/anythingslider.js?utv=0002219"></script>
<script type="text/javascript"
	src="//img.tourtips.com/js/pc/main/main.js?utv=0002219"></script>

<style type="text/css">
</style>


</head>

<body>
									<div id="check">
									<div id="u_skip">
		<a href="#container"
			onclick="document.getElementById('container').focus();return false;">컨텐츠
			바로가기</a>
	</div>
	<div id="wrap">
		<style>
.up_close {
	background: url(//img.tourtips.com/images/common/close_btn_b.png)
		no-repeat right center;
	position: absolute;
	width: 38px;
	height: 37px;
	top: 15px;
	right: 30px;
	cursor: pointer
}
</style>
		<script>
			function TTNBClick() {
				setCookie('TTNB_219', '0', '1');
				$('.ttnb').hide();
			}
		</script>
		<!-- header -->
		<div id="header">
			<div class="loading" id="loading">
				<div class="bg"></div>
				<div class="pc">
					<img src="//img.tourtips.com/images/cm/loadingImg_pc.gif"
						alt="로딩 중입니다.">
				</div>
			</div>
			<!-- headTop -->
			<div class="headTop">
				<h1>
					<a href="Main.action"> <img src="/khtour/img/KHTOUR-logo.png"
						width="340px" ; alt="KH투어"> <!-- KH TOUR 로고 및 로고이미지에  링크추가하자 -->
					</a>
				</h1>

				<s:if test='%{#session.id == null}'>
					<!-- 비로그인일 때 -->
					<!-- loginArea : 로그인 전 -->
					<div class="loginArea" style="width: 300px;">
						<a href="LoginForm.action">로그인</a> <span>|</span> <a
							href="JoinForm.action">회원가입</a>
					</div>
				</s:if>

				<s:elseif test='%{#session.admincheck == 0 && #session.id != null}'>
					<!-- 회원이 로그인 했을 때 -->
					<div class="loginArea" style="width: 300px;">
						<a
							href="LogoutMember.action?id=<s:property value="#session.id" />">로그아웃</a>
						<span>|</span><Strong><s:property value="#session.id" /></Strong>님
						어서오세요
					</div>
				</s:elseif>

				<s:elseif test='%{#session.admincheck == 1 && #session.id != null}'>
					<!-- 관리자일 때 -->
					<div class="loginArea" style="width: 300px;">
						<s:a href="LogoutMember.action">로그아웃</s:a>
						<span>|</span> <a href="AdminForm.action">상품 및 고객 관리</a> <span>|</span><Strong>관리자</Strong>님
						어서오세요
					</div>
				</s:elseif>

				<s:elseif test='%{#session.admincheck == 11 && #session.id != null}'>
					<!-- 관리자일 때 -->
					<div class="loginArea" style="width: 300px;">
						<s:a href="LogoutMember.action">로그아웃</s:a>
						<span>|</span> <a href="AdminForm.action">상품 및 고객 관리</a> <span>|</span><Strong>관리자</Strong>님
						어서오세요
					</div>
				</s:elseif>
				<!-- //loginArea -->
				<!-- search -->
				<div id="search">
					<fieldset>
						<jsp:include page="/main/weather.jsp" flush="false" />
					</fieldset>
				</div>
				<!-- //search -->

			</div>
			<!-- //headTop -->

			<!-- gnb -->
			<div id="gnb">
				<div class="gnbIn">
					<ul>
						<li class="dest"><a href="CompanyIntro.action"
							class="deapth1 " data-title="회사소개">회사소개<!-- <span class="new">new</span> --></a></li>
						<li class="community"><a href="ListItem.action"
							class="deapth1" data-name="여행정보">여행 패키지</a></li>
						<li class="book"><a href="ListAir.action" class="deapth1"
							data-title="항공">항공권</a></li>
						<li class="ticket"><a href="ListHotel.action" class="deapth1"
							class="deapth1" data-title="호텔">호텔 / 리조트</a></li>
						<li class="ticket"><a href="ListReview.action"
							class="deapth1" data-title="후기">여행 후기</a></li>
						<li class="air"><a href="MyPageForm.action" class="deapth1"
							data-title="마이페이지">마이페이지</a></li>
						<li class="hotel"><a href="ServiceForm.action"
							class="deapth1" data-title="고객센터">고객센터</a></li>
					</ul>
				</div>

			</div>
			<!-- //gnb -->

		</div>
	</div>
	<hr>
<jsp:include page="/item/SideBar.jsp" flush="false" />
	<script>
		function deletecheck() {
			    return confirm("해당 회원을 탈퇴 처리하시겠습니까?");
			}
	</script>
	<div id="subWrap">
		<!-- 통시작 -->

		<!-- 사이드부분 -->
		<div class="lnb">
			<h1 class="lnbTitle">관리자</h1>
			<ul>
				<li class=""><a href="AdminForm.action" class="">상품 등록</a></li>
				<li class=""><a href="AdminMemberList.action" class="on">회원
						관리</a></li>
			</ul>



			<div class="adWrap"></div>
			<div class="adWrap"></div>

		</div>
		<!-- //사이드부분 -->


		<!-- 고객센터임을 알리는 -->
		<div class="contents">

			<h2 class="titleSt02">회원 정보</h2>
			<br> <br>

			<!-- 글리스트 -->
			<div class="noticeListWrap">
				<s:form action="AdminMemberList">
					<select name="n">
						<option value="0">아이디</option>
						<option value="1">이름</option>
						<option value="2">이메일</option>
						<option value="3">연락처</option>
					</select>
					<input type="text" name="search" />
					<input type="submit" value="검색" />
					<a href="AdminMemberList.action?ch=1">&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;접속 회원&nbsp;&nbsp;|&nbsp;&nbsp;</a>
					<a href="AdminMemberList.action?ch=0">미접속 회원&nbsp;&nbsp;|&nbsp;&nbsp;</a>
				</s:form>


				<table>
					<colgroup>
						<col style="width: 10%">
						<col style="width: 7%">
						<col style="width: 14%">
						<col style="width: 12%">
						<col style="width: 7%">
						<col style="width: 5%">
						<col style="width: 20%">
						<col style="width: 5%">

					</colgroup>
					<thead>
						<tr>
							<th scope="col">아이디</th>
							<th scope="col">이름</th>
							<th scope="col">이메일</th>
							<th scope="col">연락처</th>
							<th scope="col">가입일자</th>
							<th scope="col">등급</th>
							<th scope="col">관리</th>
							<th scope="col">접속</th>

						</tr>
					</thead>
					<tbody>

						<s:iterator value="adminmemberlist" status="stat">

							<tr>
								<td class="num"><s:property value="id" /></td>
								<td class="num"><s:property value="name" /></a></td>
								<td class="num"><s:property value="email" /></td>
								<td class="num"><s:property value="tel" /></td>
								<td class="num"><s:property value="regdate" /></td>
								<td class="num"><s:if test='%{admin == 0}'>회원</s:if> <s:elseif
										test='%{admin != 0}'>관리자</s:elseif></td>
								<td class="num"><a href="OrderListPage.action?id=${id}">구매내역</a>
									&nbsp;&nbsp;&nbsp;<a
									href="AdminMemberModifyForm.action?id=<s:property value="id"/>">정보수정</a>
									&nbsp;&nbsp;&nbsp;<a
									href="AdminMemberDelete.action?id=<s:property value="id"/>"
									onclick="return deletecheck()">탈퇴</a></td>
									<td class="num">
									<s:if test="%{ch == 1}">
									<img src="/khtour/img/green.jpg" width="20px" height="20px" alt="접속중">
									</s:if>
									<s:elseif test="%{ch == 0 || ch == 2}">
									<img src="/khtour/img/empty.jpg" width="20px" height="20px">
									</s:elseif>
									</td>
							</tr>

						</s:iterator>


					</tbody>
				</table>
				<!-- 페이징 -->
				<div class="paging">
					<s:property value="pagingHtml" escape="false" />
				</div>
				<!-- //페이징 -->
			</div>

			<!-- 글쓰기 버튼 -->

		</div>
		<!-- //contents -->

	</div>
<div id="footer">
		<div class="policy">
		<a href="ServiceForm.action">고객센터</a>
	</div>
	<address>
				㈜KHTOUR / 대표이사 오윤구 / 서울특별시 강남구 테헤란로 14길 6 남도빌딩 3F <br>
		사업자등록번호 111-11-11111 / 통신판매업 신고번호 : 2017-서울종로-0317
		대표번호 02-1111-1111 / FAX 070-1111-1111 / 대표메일 kttour@kttour.com<br>
		copyright &copy; TOURTIPS Inc. All rights Reserved.
	</address>
</div>
<!-- //footer -->
</div>
</body>
</html>

