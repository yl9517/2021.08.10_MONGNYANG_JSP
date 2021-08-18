<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="../css/signUp.css">
</head>
<body>
	<div id="sign_wrap">
		<h2>MONG NYANG</h2>
		<form method="post" action="#">
			<ul>	
				<li>
					<label for="id">아이디</label><br>
					<input type="text" id="id" name="id" required="required"> 
					<a href="#"><input type="button" value="중복확인" id="overlap"></a>
				</li>
				<li>
					<label>비밀번호</label><br>
					<input type="password" id="pwd" name="pwd" required="required">
				</li>
				<li>
					<label>비밀번호 재확인</label><br>
					<input type="password" id="pwdCheck" name="pwdCheck" required="required">
				</li>
				<li>
					<label>전화번호</label><br>
					<input type="tel" id="phone" name="phone" placeholder="숫자만 입력해주세요" required="required">
				</li>
				<li>
					<label>이메일</label><br>
					<input type="email" id="email" name="email" required="required">
				</li>
				<li>
					<label>사는 곳</label><br>
					<select id="addr" name="addr">
						<option value="동">강동</option>
						<option value="서">강서</option>
						<option value="남">강남</option>
						<option value="북">강북</option>
					</select>
				</li>
				<li>
					<input type="submit" value="회원가입">
				</li>
				<li>
					<a href="login.jsp">로그인 </a>
				</li>
			</ul>
		
		</form>
	</div>
	<script src="../js/userSignup.js"></script>
</body>
</html>