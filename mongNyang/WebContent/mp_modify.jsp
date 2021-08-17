<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/mp_modify.css">
</head>
<body>

	<form method="POST" action="">
        <label for="id">아이디</label>
        <input type="text" id="id" name="id" readonly value=""><br>
        <label for="pwd">패스워드</label>
        <input type="password" id="pwd" name="pwd" value=""><br>
        <label for="email">이메일</label>
        <input type="text" id="email" name="email" value=""><br>
        <label for="addr">주소</label>
        <input type="text" id="addr" name="addr" value=""><br>
        <label for="phone">전화번호</label>
        <input type="text" id="phone" name="phone" value=""><br>
        
        <input type="submit" value="수 정">
        <input type="reset" value="취 소">
    </form>

</body>
</html>