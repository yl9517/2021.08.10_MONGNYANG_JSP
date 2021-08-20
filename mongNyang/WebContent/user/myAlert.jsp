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
	<c:set var="dto" value="${requestScope.dto }"></c:set>
		<div id="myAlert">
			<h1>내 알림</h1>			
			<table>
				<thead>
					<tr>
						<th colspan="2"> 전체 <c:out value="${dto.size() }"></c:out></th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="item" items="${dto }">
					<tr>
						<td width="880px"> 						
							<a href="boarddetail.do?boardNum=${item.boardNum }" onclick="return modifyAlert(${item.replyNum})">							
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
				</tbody>
				
			</table>
		</div>
		<script src="js/myAlert.js"></script>
</body>
</html>