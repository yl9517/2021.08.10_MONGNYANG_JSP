package kr.or.mn.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;

public class ImageInsertAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		request.setCharacterEncoding("utf-8");
		String imageName ="";
		String imagePath ="";
		String uploadPath = request.getRealPath("images"); 
		System.out.println("절대 경로 :"+uploadPath);

		
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadPath, 1024*1024*10,"utf-8",new DefaultFileRenamePolicy());
			
			//전송한 전체 파일이름들 가져오기
//	        Enumeration fileName = multi.getFileNames();
//	        String files = (String)fileName.nextElement();  
	        
	        //파일명 중복 발생시 뒤에 1,2,3 붙이기
	        imageName = multi.getFilesystemName("file1");
	        imagePath = multi.getOriginalFileName("file1");

	        
	        System.out.println(imageName);
	        System.out.println(imagePath);


			
//			imageName = multi.getFilesystemName("file1");
//			imagePath = multi.getOriginalFileName("file1");
//			String boardTitle = multi.getParameter("boardTitle");
//			String boardContent = multi.getParameter("boardContent");
//			String categoryName = multi.getParameter("petAddr")+multi.getParameter("petType");
//		
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		Forward forward = new Forward();
		forward.setForward(false);
		forward.setPath("test.jsp");
		
		return forward;
	}
}
