<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/myDelete.css">
</head>
<body>
<div id="myboardWrap">
		<aside id="mypageBtns">
			<img alt="userImg" src="../images/userImg.png">
			<ul>
				<li>
					<a href="#">
						회원정보
					</a>
				</li>
				<li>
					<a href="#">
						회원정보수정
					</a>
				</li>
				<li>
					<a href="#">
						내가 쓴 게시물
					</a>
				</li>
				<li>
					<a href="#">
						내가 쓴 댓글
					</a>
				</li>
			
			</ul>
		</aside>
		 
 		<div id="delWrap">
			<section>			
	    		<div id="btn_group">
			    	<p>정말로 탈퇴하시겠습니까?</p>
			        <button id="btn1" type="button">탈 퇴</button>
			        <button id="btn2" type="button">취 소</button>
	    		</div>  		 	
			</section>
		</div>
	</div>

<script src="js/userdelete.js"></script>

</body>
</html>