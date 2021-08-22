<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/mypageReply.css">
</head>
<body>
<c:set var="dto" value="${requestScope.mypagereplylist }"></c:set>
<c:set var="paging" value="${requestScope.paging }"></c:set>
<c:set var="userId" value="${sessionScope.userId}"></c:set>
<c:set var="totalcount" value="${requestScope.totalcount }"></c:set>

   <div id="mypage">
      <aside id="mypageBtns">
         <img alt="userImg" src="images/userImg.png">
         <ul>
            <li>
               <a href="usermain.do">
                  회원정보
               </a>
            </li>
            <li>
               <a href="usermodify.do">
                  회원정보수정
               </a>
            </li>
            <li>
               <a href="userboard.do">
                  내가 쓴 게시물
               </a>
            </li>
            <li>
               <a href="userreply.do">
                  내가 쓴 댓글
               </a>
            </li>
         
         </ul>
      </aside>
      
      <div id="replywrap">
         <h1>내가 쓴 댓글</h1>
         
         <table>
            <thead>
               <tr class="myreplycss">
                  <th width="600px">전체  <c:out value="${totalcount }"/></th>
                  <th style="text-align:center;">작성일</th>
               </tr>
            </thead>
            <tbody>
            	<c:if test="${dto.size() ==0 }"> <!-- 자료가 하나도 없으면 -->
					<tr>
						<th width="900px" colspan="3"> 작성한 댓글이 없습니다. </th>
					</tr>
				</c:if>
				<c:if test="${dto.size() >= 1 }">
		              <c:forEach var="item" items="${dto }">
		               <tr>
		                  <td class="myreplycss"><a href="boarddetail.do?boardNum=${item.boardNum }"><c:out value="${item.replyContent }"/></a></td>
		                  <td><c:out value="${item.replyDate }"/></td>
		               </tr>
		              </c:forEach>
              </c:if>
            </tbody>
         </table>
      </div>
      <div></div>
      <div class="page1">
         <c:if test="${paging.startblock>1 }">
            <a href="userreply.do?userId=${userId }&curr=${paging.prevPageblock }" id="next"><</a>
         </c:if>
      </div>
      <div class="page2">
         <c:forEach var="index" begin="${paging.startblock }" end="${paging.endblock }">
            <c:if test="${paging.currpage==index }">
               <a href="#"><c:out value="${index }"></c:out></a>
            </c:if>
            
            <c:if test="${paging.currpage!=index }">
               <a href="userreply.do?userId=${userId }&curr=${index }">${index }</a>
            </c:if>
         </c:forEach>
      </div>
      <div class="page3">
         <c:if test="${paging.endblock<paging.totalpage }">
            <a href="userreply.do?userId=${userId }&curr=${paging.nextPageblock }" id="next">></a>
         </c:if>
      </div>
      
   </div>

</body>
</html>