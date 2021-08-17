<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/boardDetail.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div id="detailWrap">
		<div id="boardWrap"> 
			<div id="boardImg">
				<img src="../images/dog1.jpg" alt="dog">
			</div>
			<div id="titleBar">
				<h2>제목입니다</h2>
				<div id="userInfo">
					<p>hong01</p>
					<p class="date">2021.08.12 17:55</p>
				</div>
			</div>
			<div id="categoryBar">
				<p class="category"> 강서  > 강아지  </p>		
				<div class="switchBar">
					<span class="view">아직 해결 되지 않았습니다 😥</span>
					<span class="view" style="display:none;">도와주셔서 감사합니다 😊</span>
					<label class="switch">
					  <input type="checkbox">
					  <span class="slider round"></span>
					</label>
				</div>		
			</div>
			<textarea>
			
			</textarea>
			<p>조회 5</p>
		</div>
		
		<div id="replyWrap">
		
			<div id="replyBar">
				<img alt="replyImg" src="../images/reply.png">
				<h2>댓글</h2>
			</div>
			
			<ul id="replyList">
				<li>
					<p>hong01</p><p>2021.06.17 18:05</p>
					<p>어라 32사거리에서 본 것 같은데</p>
				</li>
			</ul>
			
			<form id="replyInsert">
				<textarea></textarea>
				<input type="file">
				<input type="submit" value="등록">
			</form>
			
		</div>	
	</div>
			
	<div id="btns">
		<input type="button" value="글쓰기">
		<input type="button" value="수정">
		<input type="button" value="삭제">
		<input type="button" value="목록">	
	</div>
	
	<script src="../js/boardBtn.js"></script>
</body>
</html>