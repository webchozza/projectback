<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/main/Taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
   <title>DOKKY</title>

<style type="text/css">
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
</head>
<body>
   <h4>[${tag }]로 태그된 글</h4>
   <form>
<input type="hidden" id="taglist" value="${taglist}">
   </form>
   <!-- 바디 -->
   <section id="banner">
      <div class="content">
         <div class="table-wrapper">
            <table>
               <colgroup>
                  <col width="58%" />
                  <col width="22%" />
                  <col width="10%" />
                  <col width="10%" />
               </colgroup>
               <thead>
                  <tr>
                     <td colspan="2"><a href="javascript:;" onclick='urlencode2("${tag}","")'>최신순</a>
                        <a href="javascript:;" onclick='urlencode2("${tag}","like")'>추천순</a> <a
                        href="javascript:;" onclick='urlencode2("${tag}","comment")'>댓글순</a> <a
                        href="javascript:;" onclick='urlencode2("${tag}","scrap")'>스크랩순</a>
                        <a href="javascript:;" onclick='urlencode2("${tag}","hit")'>조회순</a></td>
                     <td colspan="2" align="right"></td>
                  </tr>
               </thead>
               <tbody>
                  <c:forEach var="list" items="${taglist}" varStatus="status">
                  <c:set var="btl_count" value="${status.index}"/>
                     <c:if test="${list.bgroup_id eq 1}"> <!-- 오픈소스 -->
                        <c:url var="detailURL" value="/bcodelist.do">
                           <c:param name="board_id" value="${list.board_id }" />
                           <c:param name="currentPage" value="1" />
                           <c:param name="session_id" value="${sessionScope.member_id}" />
                        </c:url>
                     </c:if>
                     <c:if test="${list.bgroup_id eq 2}"> <!-- 자유 -->
                        <c:url var="detailURL" value="/bfreedetail.do">
                           <c:param name="board_id" value="${list.board_id }" />
                           <c:param name="currentPage" value="1" />
                           <c:param name="session_id" value="${sessionScope.member_id}" />
                        </c:url>
                     </c:if>
                     <c:if test="${list.bgroup_id eq 3}"> <!-- 구인구직 -->
                        <c:url var="detailURL" value="/bjobdetail.do">
                           <c:param name="board_id" value="${list.board_id }" />
                           <c:param name="currentPage" value="1" />
                           <c:param name="session_id" value="${sessionScope.member_id}" />
                        </c:url>
                     </c:if>
                     <c:if test="${list.bgroup_id eq 4}"> <!-- Q&A -->
                        <c:url var="detailURL" value="/bqnadetail.do">
                           <c:param name="board_id" value="${list.board_id }" />
                           <c:param name="currentPage" value="1" />
                           <c:param name="session_id" value="${sessionScope.member_id}" />
                        </c:url>
                     </c:if>
                     <tr>
                        <td><a href="${detailURL }">${list.board_title }</a><div class='icon fa-tags'>
                        <c:forEach var="btl" items="${tagmap[btl_count]}" varStatus="sta">
                        
                        <script type="text/javascript">
                        function urlencode2(str,sort) {
                            str = (str + '').toString();
                            str = encodeURIComponent(str)
                                .replace(/!/g, '%21')
                                .replace(/'/g, '%27')
                                .replace(/\(/g, '%28')
                                .replace(/\)/g, '%29')
                                .replace(/\*/g, '%2A')
                                .replace(/%20/g, '+');
                        
                            location.href="taglist.do?tag="+str+"&sort="+sort;
                        }
                        
                        </script>
                        
                        
                        <a href="javascript:;" class="tagcode" onclick='urlencode2("${btl}","")'>${btl}</a>
                        </c:forEach>
                        </div></td>
                        <td><a class="icon fa-comment">${list.board_comment_count }</a>&nbsp;&nbsp;&nbsp;
                           <a class="icon fa-thumbs-up">${list.board_like }</a>&nbsp;&nbsp;&nbsp;
                           <a class="icon fa-eye">${list.board_hit }</a></td>
                        <td><a
                           href="/dokky/MemberPage.do?member_id=${list.member_id }&session_id=${sessionScope.member_id}">${list.board_nickname }</a></td>
                        <td><fmt:formatDate value="${list.board_date }"
                              pattern="yyyy.MM.dd" /></td>
                     </tr>
                  </c:forEach>
               </tbody>
            </table>
         </div>
         <div class="paging" align="center">${pagingHtml}</div>
         <br>
      </div>
   </section>
</body>
</html>