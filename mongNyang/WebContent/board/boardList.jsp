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
<c:set var="paging" value="${requestScope.paging }"></c:set>
	
	<div id="boardWrap">
		<!-- 링크에 따라 이미지 변경 -->
		<img alt="map" src="images/map.png" id="map">
		<input type="hidden" value="${boardType }" id="btype">
		
		<div id="addrBar">
		
			<input type="button" value="전체" id="addrAll" class="addrBtn" name= "petAddr">
			<input type="button" value="강동" id="addrEast" class="addrBtn" name= "petAddr">
			<input type="button" value="강서" id="addrWest" class="addrBtn" name= "petAddr">
			<input type="button" value="강남" id="addrSouth" class="addrBtn" name= "petAddr">
			<input type="button" value="강북" id="addrNorth" class="addrBtn" name= "petAddr">
			
		</div>
		<div id="findBar">
			<form method="post" action="boardlist.do?boardType=${boardType }">
				<div id="directSearch">
					<select id="search" name="search">
						<option value="boardTitle">제목</option>
						<option value="boardContent">내용</option>
					</select>
					<input type="text" name="searchtxt">
					<input type="submit" value="검색" id="btn1">
				</div>
			</form>
		</div>
		<div id="list">
			<ul>
				<c:forEach var="item" items="${list }"> 
					
					<li>
						<a href="boarddetail.do?boardNum=${item.boardNum }">
							<img alt="${item.imageName }" src="${item.imagePath }" class="petImg">
							
							<!-- <div class="petImg">사진영역</div> -->
							<!-- div는 지우고 img 활성화-->
							<p class="petContent" id="petAddr"><c:out value="${item.petAddr } >"></c:out><c:out value="${item.petType }"></c:out></p>
							<p class="petContent" id="boardTitle"><c:out value="${item.boardTitle }"></c:out><span>[${item.boardReadNo }]</span></p>
							<p class="petContent"><c:out value="${item.boardDate }"></c:out></p>
						</a>				
					</li>
				</c:forEach>
				
			</ul>
			
			<input type="button" value="글쓰기" id="add">
				
		</div>
		
		<div class="page1">
			<c:if test="${paging.startblock>1 }">
				<a href="boardlist.do?boardType=${boardType }&curr=${paging.currpage-1 }&search=${paging.search }&searchtxt=${paging.searchtxt }">이전</a>
			</c:if>
		</div>
		<div class="page2">
			<c:forEach var="index" begin="${paging.startblock }" end="${paging.endblock }">
				<c:if test="${paging.currpage==index }">
					<a href="#"><c:out value="${index }"></c:out></a>
				
				</c:if>
				
				<c:if test="${paging.currpage!=index }">
					<a href="boardlist.do?boardType=${boardType }&curr=${index }&search=${paging.search }&searchtxt=${paging.searchtxt }">${index }</a>
				
				</c:if>
			</c:forEach>
		</div>
		<div class="page3">
			<c:if test="${paging.endblock<paging.totalpage }">
				<a href="boardlist.do?boardType=${boardType }&curr=${paging.currpage+1 }&search=${paging.search }&searchtxt=${paging.searchtxt }">다음</a>
			</c:if>
		</div>
	</div>
	
	<script src="js/boardList.js"></script>
	
</body>
</html>