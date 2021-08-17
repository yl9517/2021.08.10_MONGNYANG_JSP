<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/insertform.css">
</head>
<body>
	
	<form method="post" action="">
		<ul>
			<li id="titleBar">
				<label>글쓰기</label>
				<input type="submit" value="등록">
			</li>
			<li id="titleBar2">
				<label for="boardTitle"></label>
				<input type="text" name="boardTitle" id="boardTitle" value="제목을 입력해주세요(최대 40자)" required="required">
			</li>
			<li id="addrBar">
				<label>실종장소</label>
				<select id="petAddr" name="petAddr">
					<option value="강동">강동</option>
					<option value="강서">강서</option>
					<option value="강남">강남</option>
					<option value="강북">강북</option>
				</select>
               	
               	<label>견/묘</label>
				<select id="petType" name="petType">
					<option value="강아지">강아지</option>
					<option value="고양이">고양이</option>
				</select>
			</li>
			<li id="contentBar">
				<label for="boardContent"></label>
				<textarea name="boardContent" id="boardContent"></textarea>
			</li>
			
		</ul>
		<div id="photo">
			사진 첨부 (미완성)
		</div>
	</form>
			

</body>
</html>