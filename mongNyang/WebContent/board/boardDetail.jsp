<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/boardDetail.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<c:set var="dto" value="${requestScope.dto }"></c:set>
<c:set var="loginId" value="${sessionScope.userId}"></c:set>

<body>

<input type="hidden" class="boardUserId" value="${dto.userId }"> <!-- 게시글 작성자 -->

   <div id="detailWrap">
      <div id="boardWrap"> 
         <div id="boardImg">
            <img alt="${dto.imageName }" src="${dto.imagePath }">
         </div>
         <div id="titleBar">
            <h2><c:out value="${dto.boardTitle }"></c:out></h2>
            <div id="userInfo">
               <p><c:out value="${dto.userId }"></c:out></p>
               <p class="date"><c:out value="${dto.boardDate }"></c:out></p>
            </div>
         </div>
         <div id="categoryBar">
            <p class="category">
               <c:out value="${dto.petAddr }"></c:out>
               <c:out value=" > "></c:out>
               <c:out value="${dto.petType }"></c:out>
            </p>      
            <c:if test="${dto.userId == loginId }"> <!-- 글 작성자랑 로그인 아이디랑 같지 않으면 아예 안뜨게 -->
            	<div class="switchBar">
	               <input type="hidden" value="${dto.boardState}" id="bstate">
	               <span class="view">아직 해결 되지 않았습니다 😥</span>
	               <span class="view" style="display:none;">도와주셔서 감사합니다 😊</span>
	               <label class="switch">
	                 <input type="checkbox" id="stateSelect">
	                 <span class="slider round"></span>
	               </label>
	            </div>
            </c:if>      
         </div>
         <p id="boardContent">
            <c:out value="${dto.boardContent }"></c:out>
         </p>
         <p style="float: right;">조회 <c:out value="${dto.boardReadNo }"></c:out></p>
      </div>
      
      <div id="replyWrap">
      
         <div id="replyBar">
            <img alt="replyImg" src="images/reply.png">
            <h2>댓글</h2>
         </div>
         
         <script>
         function del(replyNum, boardNum, imgNum)
         {
        	 if(confirm("삭제 하시겠습니까?")){		
          		 location.href="replydelete.do?replyNum="+replyNum+"&boardNum="+boardNum+"&imgNum="+imgNum;
          		  
			}else{
				return;	
			}
         }
         
        
         $(document).ready(function(){
            let no=${dto.boardNum};
 			let master = $('.boardUserId').val();
 			
            $.ajax({
               url:'replylist.mn'
               , data:{'boardNum':no,'master':master}
               , method:'post'
               , dataType:'json'
               ,success:function(data)
               {
                  $.each(data, function(index, item){
                     let replyList="<li> <div class='replyInfo'>";
                     replyList+="<img class='userImg' alt='userImg' src='images/userImg.png'> ";
                     replyList+="<p class='replyId'>"+item.userId+"</p>";
                     if(item.userId==item.master){
                   	 	replyList+="<p class='master'> 작성자 </p>"
                     }
                     replyList+="<p class='date'>"+item.replyDate+"</p>";                
                     
                     if(item.userId==item.loginId){
                     	replyList+="<img class='del' alt='del' src='images/del.png' onclick=del("+item.replyNum+","+item.boardNum+","+item.imgNum+")>";                    	 
                     }
					 replyList+="<p class='reply'>"+item.replyContent+"</p>";
                     /* 사진 널이 아닐경우 받아오기 */
                     if(item.imgName != null){
                    	 replyList+="<br>";
                         replyList+="<img class='insImg' src='"+item.imgPath+"' alt='"+item.imgName+"'>";
                     }
                     
                     replyList+="</div></li>";
                     $('#replyList').append(replyList);
        
                  });
               }
               ,error:function(data)
               {
                  console.log(data);
               }
            });
         });
         
         
         </script>
         
         <ul id="replyList">
   
         </ul>
         
         
         <form id="replyInsert" method="post" action="replyinsert.do" enctype="multipart/form-data">
            <input type="hidden" name="reBoardNum" value="${dto.boardNum }">
            <textarea name="replyContent" required="required"></textarea>
            <input type="submit" value="등록">
            <input type="file" name="replyfile">
         </form>
      
      </div>   
   </div>
         
   <div id="btns">
      <input type="hidden" value=${dto.boardType } id="boardType">
      <input type="hidden" value=${dto.boardNum } id="boardNum">   
      <input type="hidden" value=${dto.boardNum } id="bNum">   
      <input type="hidden" value=${dto.imageName } id="imageName">
      <input type="hidden" value=${dto.imagePath } id="imagePath">
      
      <input type="button" value="글쓰기" id="add">
      <c:if test="${loginId eq dto.userId }">
	      <input type="button" value="수정" id="modify">
	      <input type="button" value="삭제" id="delete">
      </c:if>
      <input type="button" class="right" value="목록" id="list">   
   </div>
   
   <script src="js/boardDetail.js"></script>
   <script src="js/boardState.js"></script>
</body>
</html>