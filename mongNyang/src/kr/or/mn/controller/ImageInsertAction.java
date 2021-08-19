package kr.or.mn.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.ImageDTO;
import kr.or.mn.dto.MainDTO;
import kr.or.mn.service.ImageService;

public class ImageInsertAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		request.setCharacterEncoding("euc-kr");
		String imagePath=request.getRealPath("upload");
		 
	   int size = 10*1024*1024;
	   
	   String imageName="";
	   
	   try{
	      MultipartRequest multi=new MultipartRequest(request,
	                     imagePath,
	                     size,
	                     "euc-kr",
	            new DefaultFileRenamePolicy());
	 
	     Enumeration files=multi.getFileNames();
	     
	      String file =(String)files.nextElement();
	      imageName=multi.getFilesystemName(file);
	   }catch(Exception e){
	      e.printStackTrace();
	   }
		
	   
		ImageService service = ImageService.getService();
		
		MainDTO dto=new MainDTO();
		dto.setImageName(imageName);
		dto.setImagePath(imagePath);
		service.insertImg(dto);
		
		Forward forward = new Forward();
		forward.setForward(false);
		forward.setPath("imageview.do");
		
		return forward;
	}
}
