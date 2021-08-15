<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/board.css">
</head>
<body>
	<%@ include file="header.jsp" %>
	
	<div id="board_wrap">
		<img alt="map" src="images/map.jpg" id="map">
		<div id="line1">
			<a href="">전체</a>
			<a href="">강동</a>
			<a href="">강서</a>
			<a href="">강남</a>
			<a href="">강북</a>
		</div>
		<div id="line2">
			<form method="post" action="">
				<input type="date" value="달력" id="cal" class="c1">
				<div id="search_menu">
					<select id="search" name="search">
							<option value="지역">지역</option>
							<option value="견/묘">견/묘</option>
					</select>
					<input type="text" value="search">
					<input type="submit" value="검색" id="btn1">
				</div>
			</form>
		</div>
		<div id="list">
			<div id="inner">
				<img alt="imageName" src="imagePath">
				<ul>
					<li>강동/강아지</li>
					<li>강동구 랄라동 말티즈를 찾습니다[6]</li>
					<li>2021.08.12 15:03</li>
				</ul>
			</div>
		</div>
	</div>
	
	
	<%@ include file="footer.jsp" %>
</body>
</html>