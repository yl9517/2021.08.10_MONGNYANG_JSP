<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/boardModify.css">
</head>
<body>
	<c:set var="dto" value="${requestScope.dto }"></c:set>
	<form method="post" action="boardmodifyresult.do" enctype="multipart/form-data">
		<input type="text" value="${dto.boardNum }" name="bNum">
		<input type="text" value="${dto.boardType }" name="bType">
		<ul>
			<li id="titleBar">
				<label>글수정</label>
				<input type="submit" value="완료">
			</li>
			<li id="titleBar2">
				<label for="boardTitle"></label>
				<input type="text" name="boardTitle" id="boardTitle" value="${dto.boardTitle }" maxlength="40" required="required">
			</li>
			<li id="addrBar">
				<label>실종장소</label>
				<c:set var="addr" value="${dto.petAddr }"></c:set>
				<select id="petAddr" name="petAddr">
					<option value="EAST" ${addr.equals('EAST')?'selected':'' }>강동</option> 
					<option value="WEST" ${addr.equals('WEST')?'selected':'' }>강서</option>
					<option value="SOUTH" ${addr.equals('SOUTH')?'selected':'' }>강남</option>
					<option value="NORTH" ${addr.equals('NORTH')?'selected':'' }>강북</option>
				</select>
               	
               	<label>견/묘</label>
				<select id="petType" name="petType">
					<option value="DOG">강아지</option>
					<option value="CAT">고양이</option>
				</select>
			</li>
			<li id="contentBar">
				<label for="boardContent"></label>
				<textarea name="boardContent" id="boardContent">${dto.boardContent }</textarea>
			</li>
			
		</ul>
		<div id="photo">
			<input type="hidden" value="${dto.imageName }" name="imageName">
			<input type="hidden" value="${dto.imagePath }" name="imagePath">
			<input type="hidden" value="${dto.imageNum }" name="imageNum">
			<img alt="${dto.imageName }" src="${dto.imagePath }">
			<input type="file" name="file1">
		</div>
	</form>
			

</body>
</html>