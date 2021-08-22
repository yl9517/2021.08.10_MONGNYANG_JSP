<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
<c:set var="result" value="${requestScope.result }"/>

	<script>
		if(${result} == 2){
			alert('가입하지 않은 아이디이거나, 잘못된 비밀번호입니다 ');
		}
	</script>

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
					<input style="background-color: #A9DFED; font-size: 16px;" type="submit" value="로그인">
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