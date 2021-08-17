<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int result=(Integer)request.getAttribute("result");
	
	if(result>0){
		%>
			<script>
				alert('입력성공!!!');
				location.href="boardlist.do";
			</script>
		<%
	}else{
		%>
			<script>
				alert('입력실패');
				location.href="boardinsert.do";
			</script>
		<%
	}
	
	
	%>
</body>
</html>