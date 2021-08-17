<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/myDelete.css">
</head>
<body>

	<div id ="myBordWarp">
	
		<aside>
			<img alt="사람이미지" src="../images/man.jpg">
			<p>본인 아이디</p>
			<ul class="nav">
				<li id="mpinfo"><a href="#">회원정보</a></li><br>
				<li id="mpmodify"><a href="#">회원정보 수정</a></li><br>
				<li id="mpboard"><a href="#">내가 쓴 게시물</a></li><br>
				<li id="mpreply"><a href="#">댓글 목록</a></li><br>
				<li id="withdrawal"><a href="#">회원 탈퇴</a></li>	<br>
			</ul>
		</aside>


		<section>
			<div id="delWrap">
	    		<div id="btn_group">
			    	<p>정말로 탈퇴하시겠습니까?</p>
			        <button id="btn1" type="button">탈 퇴</button>
			        <button id="btn2" type="button">취 소</button>
	    		</div>
   		 	</div>
		</section>

	</div>


</body>
</html>