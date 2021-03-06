<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/myMain.css">
</head>
<body>
<c:set var="dto" value="${requestScope.dto }"></c:set>

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

		<section>
			<div id="infowrap">
				<h1> My Page</h1>
				<table>
	
			        <tr><td>ID</td><td width="200px"><c:out value="${dto.userId }"/></td></tr>
			        <tr><td>Phone</td><td><c:out value="${dto.userPhone }"/></td></tr>
			        <tr><td>Email</td><td><c:out value="${dto.userEmail }"/></td></tr>
			        <tr><td>Address</td><td><c:out value="${dto.userAddr }"/></td></tr>	
		        
    			</table>
			</div>
		</section>
	</div>

</body>
</html>