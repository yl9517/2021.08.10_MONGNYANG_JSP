<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/myModify.css">
</head>
<body>
	<hr>
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
			<div id="formwrap">
		  		<form method="POST" action="">
			        <label for="id">아이디</label>
			        <input type="text" id="id" name="id" readonly value=""><br>
			        <label for="pwd">패스워드</label>
			        <input type="password" id="pwd" name="pwd" value="" required><br>
			        <label for="email">이메일</label>
			        <input type="text" id="email" name="email" value="" required><br>
			        <label for="addr">주소</label>
			        <input type="text" id="addr" name="addr" value=""><br>
			        <label for="phone">전화번호</label>
			        <input type="text" id="phone" name="phone" value=""><br>
			
			        <input type="submit" class="button" value="수 정">
			        <input type="reset" class="button" value="취 소">
	    		</form>
    		</div>
		</section>
	</div>

</body>
</html>