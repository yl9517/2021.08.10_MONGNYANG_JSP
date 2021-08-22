<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<c:set var="data" value="${requestScope.data }"></c:set>
	<div id="mainWrap">
		<div id="mainImg">
			<ul id="data">
				<li>				
					<h2><c:out value="${data[0] }건"></c:out></h2>
					<p>오늘 접수 된 <br>실종 건수</p>				
				</li>
				<li>
					<h2><c:out value="${data[1] }건"></c:out></h2>
					<p>찾은 반려동물 건수</p>				
				</li>
				<li>
					<h2><c:out value="${data[2] }건"></c:out></h2>
					<p>누적 신고 건수</p>				
				</li>
			</ul>
			<p class="msg">
				가족을 잃어버린 몽냥이들에게 <b>가족의 품을</b> <br>
				거리의 몽냥이들에게 <b>따뜻한 손길을</b>
			</p>
		</div>	
	</div>
	
</body>
</html>