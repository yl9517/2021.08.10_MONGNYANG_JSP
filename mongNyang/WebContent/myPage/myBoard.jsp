<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/myBoard.css">
</head>
<body>
	<%@ include file="../header.jsp" %>
	
	<main>
		<aside>
			<img alt="사람이미지" src="../imgages/man.jpg">
			<p>본인 아이디</p>
			<ul class="nav">
				<li id="mpinfo"><a href="#">회원정보</a></li><br>
				<li id="mpmodify"><a href="#">회원정보 수정</a></li><br>
				<li id="mpboard"><a href="#">내가 쓴 게시물</a></li><br>
				<li id="mpreply"><a href="#">댓글 목록</a></li><br>
				<li id="withdrawal"><a href="#">회원 탈퇴</a></li>	<br>
			</ul>
		</aside>
		<div class="boardwrap">
		<section>
			<h1>내가 쓴 게시물</h1>
			<p>전체 12</p>
			<div>
				[찾아주세요][용산구][시바견]저희집 누렁이 보신분 있나요...    2021.07.25      5
			</div>
			<div>
				[찾아주세요][용산구][시바견]저희집 누렁이 보신분 있나요...    2021.07.25      5
			</div>
			<div>
				[찾아주세요][용산구][시바견]저희집 누렁이 보신분 있나요...    2021.07.25      5
			</div>
		</section>
		</div>
	</main>
	<%@ include file="../footer.jsp" %>
	
</body>
</html>