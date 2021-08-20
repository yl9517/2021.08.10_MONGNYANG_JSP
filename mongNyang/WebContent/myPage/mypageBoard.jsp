<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/mypageBoard.css">
</head>
<body>
<c:set var="dto" value="${requestScope.list }"></c:set>
	<div id="myboardWrap">
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
		
		<div id="myboard">
			<h2>내가 쓴 게시글</h2>	 
			<table>
			
				<thead>
					<tr>
						<th>전체 <c:out value="${dto.size() }"/></th>
						<th>작성일</th>
						<th>상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${dto}">
						<tr>
							<td width="500px"> <c:out value="${item.boardTitle }"/> </td>
							<td width="150px"> <c:out value="${item.boardDate }"/> </td>
							<td width="90px"> <c:if test="${item.boardState==true }">해결</c:if>
											  <c:if test="${item.boardState==false }">미해결</c:if></td><!-- 불린으로 받아와서 해결 / 미해결 설정 -->
						</tr>	
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>