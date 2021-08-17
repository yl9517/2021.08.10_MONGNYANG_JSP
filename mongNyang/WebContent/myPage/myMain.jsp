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
	<hr>
	<div id ="myBordWarp">
		<aside>
			<img alt="userImg" src="../images/man.jsp">
			<p>본인 아이디</p>
			<ul class="nav">
				<li id="mpinfo"><a href="#">회원정보</a></li><br>
				<li id="mpmodify"><a href="#">회원정보 수정</a></li><br>
				<li id="mpboard"><a href="#">내가 쓴 게시물</a></li><br>
				<li id="mpreply"><a href="#">댓글 목록</a></li><br>
				<li id="withdrawal"><a href="#">회원 탈퇴</a></li>	<br>
			</ul>
		</aside>
				
		<section>
			<div id="infowrap">
				<table>
			        <tr><td>아이디</td><td>hong gil dong</td></tr>
			        <tr><td>이메일</td><td>hong123@gmail.com</td></tr>
			        <tr><td>주소</td><td>서울시 마포구 적막한산하</td></tr>
			        <tr><td>전화번호</td><td>010-1234-5678</td></tr>
    			</table>
			</div>
		</section>
	</div>

</body>
</html>