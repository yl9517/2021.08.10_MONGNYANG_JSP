<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.*" %>
<%
	request.setCharacterEncoding("euc-kr");
	String uploadPath=request.getRealPath("upload");
   
   int size = 10*1024*1024;
   
   String filename1="";
   String filename2="";
   String filename3="";
   
   try{
      MultipartRequest multi=new MultipartRequest(request,
                     uploadPath,
                     size,
                     "euc-kr",
            new DefaultFileRenamePolicy());
 
     Enumeration files=multi.getFileNames();

     String file1 =(String)files.nextElement();
     filename1=multi.getFilesystemName(file1);
     
     String file2 =(String)files.nextElement();
     filename2=multi.getFilesystemName(file2);
     
     
     String file3 =(String)files.nextElement();
     filename3=multi.getFilesystemName(file3);
      
   }catch(Exception e){
      e.printStackTrace();
   }
   System.out.println(uploadPath);
  
%>
<html>
<head>
<meta charset="EUC-KR">
</head>
<body>

파일명1: <a href="upload/<%=filename1 %>"><%=filename1 %></a><br>
파일사진1: <img src="upload/<%=filename1 %>" alt="<%=filename1 %>"><br>
파일명2: <a href="upload/<%=filename2 %>"><%=filename2 %></a><br>
파일사진2: <img src="upload/<%=filename2 %>" alt="<%=filename2 %>"><br>
파일명3: <a href="upload/<%=filename3 %>"><%=filename2 %></a><br>
파일사진3: <img src="upload/<%=filename3 %>" alt="<%=filename3 %>"><br>

	out.println("<%=filename2 %>");
 <form name="filecheck" action="fileCheck.jsp" method="post">

   <input type="hidden" name="filename1" value="<%=filename1%>">
   <input type="hidden" name="filename2" value="<%=filename2%>">
   
</form>
<a href="#" onclick="javascript:filecheck.submit()">업로드 확인 및 다운로드 페이지 이동</a> --
</body>
</html>