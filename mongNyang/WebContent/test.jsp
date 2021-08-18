<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
%>
<c:set var="imgdto" value="${requestScope.imgdto }"></c:set>

<img alt="${imgdto.imageName }" src="${imgdto.imagePath }">
<img alt="dd" src="C:/msa/logo.png">

</body>
</html>