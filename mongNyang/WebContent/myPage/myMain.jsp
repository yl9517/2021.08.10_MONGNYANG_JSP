<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/myMain.css">
</head>
<body>
<c:set var="dto" value="${reqestScope.dto }"></c:set>
 
	<div id="myboardWrap">
		<aside id="mypageBtns">
			<img alt="userImg" src="../images/userImg.png">
			<ul>
				<li>
					<a href="#">
						회원정보
					</a>
				</li>
				<li>
					<a href="#">
						회원정보수정
					</a>
				</li>
				<li>
					<a href="#">
						내가 쓴 게시물
					</a>
				</li>
				<li>
					<a href="#">
						내가 쓴 댓글
					</a>
				</li>
			
			</ul>
		</aside>
				
		<section>
			<div id="infowrap">
				<h2>회원정보</h2>
				<table>
			        <tr><td>ID</td><td>hong gil dong</td></tr>
			        <tr><td>Phone</td><td>010-1234-5678</td></tr>
			        <tr><td>Email</td><td>hong123@gmail.com</td></tr>
			        <tr><td>Address</td><td>강서</td></tr>			        
    			</table>
			</div>
		</section>
	</div>

</body>
</html>