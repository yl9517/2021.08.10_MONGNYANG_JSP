<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/mypageReply.css">
</head>
<body>
<c:set var="replydto" value="${requestScope.mypagereplylist }"></c:set>

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
		
		<div id="replywrap">
			<h2>댓글 목록</h2>
			
			<table>
				<thead>
					<tr>
						<th colspan="2">전체 10</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="500px"> 저희집 근처에서 본것 같아요!!  </td>
						<td width="150px"> 2021.07.25 </td>

					</tr>
					<tr>
						<td> 제가 발견했는데 데리고 있을까요?  </td>
						<td> 2021.07.25 </td>

					</tr>
				</tbody>
			</table>
			<table>
				<thead>
					<tr>
						<th colspan="2">전체<c:out value="${replydto.size() }"/></th>
	<!-- 					<th>댓글 내용</th>
						<th>작성일</th> -->
					</tr>
				</thead>
				<tbody>
				  <c:forEach var="item" items="${replydto }">
					<tr>
						<td><c:out value="${item.replyContent }"/></td>
					</tr>
				  </c:forEach>
				</tbody>
			</table>
		</div>
		
	</div>

</body>
</html>