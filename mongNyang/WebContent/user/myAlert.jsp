<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/myAlert.css">
</head>
<body>
	<c:set var="dto" value="${requestScope.alertdto }"></c:set>
	<c:set var="paging" value="${requestScope.paging }"></c:set>
	<c:set var="userId" value="${sessionScope.userId}"></c:set>
		<div id="myAlert">
			<h1>내 알림</h1>			
			<table>
				<thead>
					<tr>
						<th colspan="2"> 전체 <c:out value="${dto.size() }"></c:out></th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${dto.size() ==0 }"> <!-- 자료가 하나도 없으면 -->
						<tr>
							<th width="900px"> 새 댓글 알림이 없습니다. </th>
							<td width="30px"></td>
						</tr>
					</c:if>
					<c:if test="${dto.size() >= 1 }"> <!-- 자료가 하나 이상이면 -->
						<c:forEach var="item" items="${dto }"> <!-- for문 돌기 -->
							<tr>
								<td width="880px">
									 				
									
									<a href="useralertupdate.do?replyNum=${item.replyNum }&changeAlert=1&boardNum=${item.boardNum } ">
										<c:if test="${item.alertCheck == 1 }"> <!-- 알림상태가 1이면 -->
											<span style="color : #ccc;">[ <b><c:out value="${item.boardTitle }"></c:out></b> ] 에 새 댓글이 등록되었습니다.</span>
										</c:if>
										<c:if test="${item.alertCheck == 0 }"> <!-- 알림상태가 0이면 -->
											<span>[ <b><c:out value="${item.boardTitle }"></c:out></b> ] 에 새 댓글이 등록되었습니다.</span>
										</c:if>
									</a> 
								</td>
								<td width="50px"><a href="useralertupdate.do?replyNum=${item.replyNum }&changeAlert=2"><img alt="del" src="images/del.png"></a> </td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				
			</table>
			
			<div class="page1">
	         	<c:if test="${paging.startblock>1 }">
	        		<a href="useralert.do?userId=${userId }&curr=${paging.prevPageblock }" class="next"> < </a>
	         	</c:if>
	      	</div>
	      	<div class="page2">
	         	<c:forEach var="index" begin="${paging.startblock }" end="${paging.endblock }">
	           		<c:if test="${paging.currpage==index }">
	              	 	<a href="#"><c:out value="${index }"></c:out></a>
	            	</c:if>
	          	  
	            	<c:if test="${paging.currpage!=index }">
	               		<a href="useralert.do?userId=${userId }&curr=${index }">${index }</a>
	            	</c:if>
	         	</c:forEach>
	      	</div>
	      	<div class="page3">
	         	<c:if test="${paging.endblock<paging.totalpage }">
	            	<a href="useralert.do?userId=${userId }&curr=${paging.nextPageblock }" class="next"> > </a>
	         	</c:if>
	      	</div>
		</div>
		
</body>
</html>