<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/header.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<header>
		<nav id="head">
			<div class="logo">
				<img src="images/logo.png" alt="logo">
			</div>
			<ul class="boards">
				<li><a href="boardList.do?boardType=FIND">찾아주세요</a> </li>
				<li><a href="boardList.do?boardType=HELP">도와주세요</a> </li>
			</ul>

			<!-- 로그인 null -->
			<div class="user">
				<a href="#">LOGIN</a>
			</div>
			<!-- 로그인 되어있을 경우 -->
			<div class="user">
				<a href="#"><!-- 유저아이디 --></a>			
			</div>
			
		</nav>
	</header>
	<script src="js/header.js"></script>
</body>
</html>