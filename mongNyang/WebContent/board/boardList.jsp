<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/boardList.css">

</head>
<body>

<c:set var="list" value="${requestScope.list }"></c:set>
<c:set var="boardType" value="${requestScope.boardType }"></c:set>
	
	<div id="boardWrap">
		<!-- 링크에 따라 이미지 변경 -->
		<img alt="map" src="images/map.jpg" id="map">
		<div id="addrBar">
			
			<a href="">전체</a> 
			<a href="">강동</a>
			<a href="">강서</a>
			<a href="">강남</a>
			<a href="">강북</a>
		</div>
		<div id="findBar">
			<form method="post" action="">
				<div id="dateSearch">
					<label id="cal"> 작성일 </label>
					<input type="date" value="달력" id="cal" class="c1">
				</div>
				<div id="directSearch">
					<select id="search" name="search">
						<option value="제목">제목</option>
						<option value="내용">내용</option>
					</select>
					<input type="text" value="search">
					<input type="submit" value="검색" id="btn1">
				</div>
			</form>
		</div>
		<div id="list">
			<ul>
				<c:forEach var="item" items="${list }"> 
					
					<li>
						<a href="boarddetail.do?boardNum=${item.boardNum }">
							<%-- <img alt="${item.imageName }" src="${item.imagePath }" class="petImg"> --%>
							
							<div class="petImg">사진영역</div>
							<!-- div는 지우고 img 활성화-->
							<p class="petContent" id="petAddr"><c:out value="${item.petAddr } >"></c:out><c:out value="${item.petType }"></c:out></p>
							<p class="petContent" id="boardTitle"><c:out value="${item.boardTitle }"></c:out><span>[${item.boardReadNo }]</span></p>
							<p class="petContent"><c:out value="${item.boardDate }"></c:out></p>
						</a>				
					</li>
				</c:forEach>
				
			</ul>
			
			<input type="hidden" value="${boardType }" id="btype">
			<input type="button" value="글쓰기" id="add">
				
		</div>
	</div>
	
	<script src="js/boardList.js"></script>
	
</body>
</html>