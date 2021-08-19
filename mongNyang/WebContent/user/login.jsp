<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div id="log_wrap">
		<h2>MONG NYANG</h2>
		<form method="post" action="userlogintry.do">
			<ul>	
				<li>
					<input type="text" id="id" name="id" placeholder="UserID"> <br>
				</li>
				<li>
					<input type="password" id="pwd" name="pwd" placeholder="Password">
				</li>
				<li>
					<input type="submit" value="로그인">
				</li>
				<li>
					<a href="userfindpwd.do"> 비밀번호 찾기 </a> <br>
					<a href="usersignup.do">회원가입</a>
				</li>
			</ul>
		
		</form>
	</div>

</body>
</html>