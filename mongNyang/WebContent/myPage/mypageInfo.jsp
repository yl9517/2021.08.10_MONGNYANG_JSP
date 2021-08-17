<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/mypageInfo.css">
</head>
<body>

	<div id="mypageInfo">
		<h1> My Page </h1>
		<form>
			<ul>
				<li>
					<label>ID</label>
					<input type="text" id="id" name="id" required="required"> 
				</li>
				<li>
					<label>Phone</label>
					<input type="tel" id="phone" name="phone" placeholder="숫자만 입력해주세요" required="required">
				</li>
				<li>
					<label>Email</label>
					<input type="email" id="email" name="email" required="required">
				</li>
				<li>
					<label>Address</label>
					<select id="addr" name="addr">
						<option value="동">강동</option>
						<option value="서">강서</option>
						<option value="남">강남</option>
						<option value="북">강북</option>
					</select>
				</li>
				<li>
					<input type="submit" value="정보수정">
				</li>
				<li>
					<a href="#">회원탈퇴 </a>
				</li>
			</ul>
		</form>
		
		<a href="#" class="mybtn">
			<span>내가 쓴 게시글</span>
			<img alt="next" src="../images/next.png">
		</a>
		<a href="#" class="mybtn">
			<span>내가 쓴 댓글</span>
			<img alt="next" src="../images/next.png">
		</a>
		
	</div>

</body>
</html>