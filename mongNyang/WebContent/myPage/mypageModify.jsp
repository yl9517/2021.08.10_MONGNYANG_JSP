<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/mypageInfo.css">
</head>
<body>
 <c:set var="dto" value="${reqestScope.dto }"></c:set>
 <c:forEach var="item" items="${dto}"/> 
 
	<div id="mypage">
		<aside id="mypageBtns">
			<img alt="userImg" src="images/userImg.png">
			<ul>
				<li>
					<a href="usermain.do">
						회원정보
					</a>
				</li>
				<li>
					<a href="usermodify.do">
						회원정보수정
					</a>
				</li>
				<li>
					<a href="userboard.do">
						내가 쓴 게시물
					</a>
				</li>
				<li>
					<a href="userreply.do">
						내가 쓴 댓글
					</a>
				</li>
			
			</ul>
		</aside>
		<div id="mypageInfo">
			<h1> My Page </h1>
			<form method="post" action="usermodifyresult.do">
				<ul>
					<li>
						<label>ID</label>
						<input type="text" id="id" name="id" readonly="readonly" value="${item.userId }"> 
					</li>
					<li>
						<label>Password</label>
						<input type="password" id="pwd" name="pwd" required="required" value="${item.userPwd }"> 
					</li>
					<li>
						<label>Phone</label>
						<input type="tel" id="phone" name="phone" placeholder="숫자만 입력해주세요" required="required" value="${item.userPhone }">
					</li>
					<li>
						<label>Email</label>
						<input type="email" id="email" name="email" required="required" value="${item.userEmail }">
					</li>
					<li>
						<label>Address</label>
						<select id="addr" name="addr" value="${item.userAddr }">
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
						<a href="userdelete.do">회원탈퇴 </a>
					</li>
				</ul>
			</form>

		</div>
	</div>

</body>
</html>