<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/findPwd.css">
</head>
<body>
	<div id="findPwd_wrap">
		<h2>비밀번호 찾기</h2> <br>
		<div class="line"></div>
		<form method="post" action="#">
				<ul>	
					<li>
						<label>아이디</label>
						<input type="text" id="id" name="id">
					</li>
					<li>
						<label>이메일</label>
						<input type="email" id="email" name="email">
					</li>
					<li>
						<input type="submit" value="확인">
					</li>
				</ul>
		</form>
	</div>
	
</body>
</html>