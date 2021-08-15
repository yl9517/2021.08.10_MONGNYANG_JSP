<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/board.css">
</head>
<body>
	
	<div id="board_wrap">
		<!-- 링크에 따라 이미지 변경 -->
		<img alt="map" src="../images/map.jpg" id="map">
		<div id="addr_bar">
			<a href="">전체</a>
			<a href="">강동</a>
			<a href="">강서</a>
			<a href="">강남</a>
			<a href="">강북</a>
		</div>
		<div id="find_bar">
			<form method="post" action="">
				<div id="date_search">
					<label id="cal"> 작성일 </label>
					<input type="date" value="달력" id="cal" class="c1">
				</div>
				<div id="direct_search">
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
					<li> 
						<img alt="" src="">
						<p>강동/강아지</p>
						<p>강동구 랄라동 말티즈를 찾습니다<span>[6]</span></p>
						<p>2021.08.12 15:03</p>
					</li>
				</ul>
			</div>
		</div>
	</div>
	
	
</body>
</html>