<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/header.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<c:set var="userId" value="${sessionScope.userId}"></c:set>

	<header>
		<nav id="head">
			<div class="logo">
				<a href="main.mn">
					<img src="images/logo.png" alt="logo">
				</a>
			</div>
			<ul class="boards">
				<li><a href="boardlist.do?boardType=FIND">찾아주세요</a> </li>
				<li><a href="boardlist.do?boardType=HELP">도와주세요</a> </li>
			</ul>
			
			<!-- 로그인이 안되어 있을 경우 -->
			<c:if test="${userId==null }">
					<div class="user">
						<a href="userlogin.do">LOGIN</a>
					</div>
			</c:if>
			
			<c:if test="${userId!=null }"><!-- 로그인 되어있을 경우 -->
				<div class="user">
					<a href="useralert.do">
					
						<img alt="ringbell" src="images/bell2.png"><!-- 댓글 알림상태가 하나라도 0 일경우-->
						<img alt="bell" src="images/bell.png"> <!-- 그 외 -->
					
					</a>
					<a href="usermain.do"><c:out value="${userId }"></c:out></a>		
					<a href="userlogout.do">LOGOUT</a>	
				</div>
			</c:if>
		</nav>
	</header>
</body>
</html>