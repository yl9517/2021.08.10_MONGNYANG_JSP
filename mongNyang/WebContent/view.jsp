<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="favicon.ico">
<link rel="icon" href="favicon.ico">
</head>
<body>

	<%@ include file="header.jsp" %>
	
	<c:set var="contentpage" value="${param.page }"></c:set> 
	<jsp:include page="${contentpage }"></jsp:include>

	
	<%@ include file="footer.jsp" %>
</body>
</html>