<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/boardInsert.css">
</head>
<body>
	<%
	String boardType=request.getParameter("boardType");
	
	%>
	<div id="writeWrap">
		<form method="post" action="boardinsertresult.do" enctype="multipart/form-data"  onsubmit="return writeSubmit();">
			<input type="hidden" value="<%=boardType %>" name="boardType">
			<ul>
				<li id="titleBar">
					<label>글쓰기</label>
					<input type="submit" value="등록">
				</li>
				<li id="titleBar2">
					<label for="boardTitle"></label>
					<input type="text" name="boardTitle" id="boardTitle" placeholder="제목을 입력해주세요(최대 40자)"  maxlength="40" required="required">
				</li>
				
				<li id="addrBar">			
					<label>실종장소</label>
					<select id="petAddr" name="petAddr">
						<option value="EAST">강동</option>
						<option value="WEST">강서</option>
						<option value="SOUTH">강남</option>
						<option value="NORTH">강북</option>
					</select>
	               	
	               	<label>견/묘</label>
					<select id="petType" name="petType">
						<option value="DOG">강아지</option>
						<option value="CAT">고양이</option>
					</select>
				</li>
				<li id="contentBar">
					<label for="boardContent"></label>
					<textarea name="boardContent" id="boardContent" placeholder="내용을 입력하세요"></textarea>
				</li>
				
			</ul>
			<div id="photo">
				<input type="file" name="file1">
			</div>
		</form>
	</div>	
	<script src="js/writeSubmit.js"></script>
</body>
</html>