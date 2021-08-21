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
<c:set var="loginId" value="${sessionScope.userId}"></c:set>
<c:set var="alertdto" value="${requestScope.alertdto}"></c:set>

	<header>
		<nav id="head">
			<div class="logo">
				<a href="main.do">
					<img src="images/logo.png" alt="logo">
				</a>
			</div>
			<ul class="boards">
				<li><a href="boardlist.do?boardType=FIND">찾아주세요</a> </li>
				<li><a href="boardlist.do?boardType=HELP">도와주세요</a> </li>
			</ul>
			
			<!-- 로그인이 안되어 있을 경우 -->
			<c:if test="${loginId == null }">
					<div class="user log">
						<a href="userlogin.do">LOGIN</a>
					</div>
			</c:if>
			
			<c:if test="${loginId != null }"><!-- 로그인 되어있을 경우 -->
					<c:set var="loop_alCheck" value="true"/><!-- 댓글 상태 확인 (0이 포함되어있는지 아닌지) 초기화:1 = 꺼져있음 -->
					
					<c:forEach var="item" items="${alertdto }">	
						<c:if test="${loop_alCheck}">
							<c:if test="${item.alertCheck == 0 }">
								<c:set var="loop_alCheck" value="false"/>
							</c:if>
						</c:if>
					</c:forEach>
					
				<div class="user">
					<a href="useralert.do">

						<c:choose>
							<c:when test="${loop_alCheck}">							
								<img alt="bell" src="images/bell.png"> <!-- 트루일경우 -->
							</c:when>
							<c:otherwise>
								<img alt="ringbell" src="images/bell2.png"><!-- 댓글 알림상태가 하나라도 0 일경우 불빛 -->
							</c:otherwise>
						</c:choose>
					
					</a>
					<a href="usermain.do" class="logid"><c:out value="${loginId }"></c:out></a>		
					<a href="userlogout.do">LOGOUT</a>	
				</div>
			</c:if>
		</nav>
	</header>
</body>
</html>