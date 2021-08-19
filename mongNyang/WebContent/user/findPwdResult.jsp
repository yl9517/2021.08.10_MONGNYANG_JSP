<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/findPwdResult.css">
</head>
<body>
<% String findpwd = (String) request.getAttribute("findPwd"); %>
	<div id="findPwd_wrap">
		<h1>비밀번호 찾기</h1> <br>
		<img alt="run" src="images/find.gif"> <br>
		<div class="line"></div>

		<div id="findwrap">
			<p>해당 회원님의 비밀번호는 <b><%=findpwd %></b> 입니다.</p>
		</div>
	</div>
</body>
</html>