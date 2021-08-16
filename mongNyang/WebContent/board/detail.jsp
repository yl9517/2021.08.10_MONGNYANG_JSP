<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/detail.css">
</head>
<body>
	<div id="detail_wrap">
		<div id="img_bar">
			<input type="button" value="'<'" id="btn1">
			<!-- <img alt="imageName" src="imagePath" class="pet_img"> -->
			<div class="pet_img">사진영역</div>
			<input type="button" value="'>'" id="btn2">
		</div>
		<div id="title_bar">
			<h1><c:out value="제목입니다"></c:out></h1>
			<p><c:out value="hong0"></c:out></p>
			<p><c:out value="2021.08.12"></c:out></p>
		</div>
	
	</div>
</body>
</html>