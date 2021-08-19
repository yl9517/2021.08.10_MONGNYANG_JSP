<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/boardDetail.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<c:set var="dto" value="${requestScope.dto }"></c:set>
<c:set var="categorys" value="${requestScope.categorys }"></c:set>

<body>
	<div id="detailWrap">
		<div id="boardWrap"> 
			<div id="boardImg">
				<img src="images/dog1.jpg" alt="dog">
			</div>
			<div id="titleBar">
				<h2><c:out value="${dto.boardTitle }"></c:out></h2>
				<div id="userInfo">
					<p><c:out  value="${dto.userId }"></c:out></p>
					<p class="date"><c:out value="${dto.boardDate }"></c:out></p>
				</div>
			</div>
			<div id="categoryBar">
				<p class="category">
					<c:out value="${categorys.petAddr }"></c:out>
					<c:out value=" > "></c:out>
					<c:out value="${categorys.petType }"></c:out>
				</p>		
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
			<p>조회 <c:out value="${dto.boardReadNo }"></c:out></p>
		</div>
		
		<div id="replyWrap">
		
			<div id="replyBar">
				<img alt="replyImg" src="images/reply.png">
				<h2>댓글</h2>
			</div>
			
			<script>
			$(document).ready(function(){
				let no=${dto.boardNum};
				$.ajax({
					url:'replylist.mn'
					, data:{'num':no}
					, method:'post'
					, dataType:'json'
					,success:function(data)
					{
						$.each(data, function(index, item){
							let replyList="<li> <div class='replyInfo'>";
							replyList+="<p class="replyId">"+item.replyId+"</p>";
							replyList+="<p class="date">"+item.replyDate+"</p>";
							replyList+="<p class="reply">"+item.replyContent+"</p></div>";
							replyList+="</li>";
							/* 사진 있을 시 추가 */
							$('#replyList').append(replyList);
							
						});
					}
					,error:function(data)
					{
						console.log(data);
					}
				});
			});
			
			</script>
			
			<ul id="replyList"></ul>
			
			
			<form id="replyInsert" method="post" action="replyinsert.do">
				<input type="hidden" name="num" value="${dto.boardNum }">
				<textarea name="replyContent"></textarea>
				<input type="submit" value="등록">
				<input type="file">
			</form>
		
		</div>	
	</div>
			
	<div id="btns">
		<input type="hidden" value=${categorys.boardType } id="btype">
		<input type="hidden" value=${dto.boardNum } id="bnum">
		<input type="button" value="글쓰기" id="add">
		<input type="button" value="수정" id="modify">
		<input type="button" value="삭제" id="delete">
		<input type="button" class="right" value="목록" id="list">	
	</div>
	
	<script src="js/boardDetail.js"></script>
</body>
</html>