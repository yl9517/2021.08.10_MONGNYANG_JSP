package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.ImageDTO;
import kr.or.mn.service.ImageService;

public class ImageViewAction implements Action{

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int boardNum =1;		
		ImageService service = ImageService.getService();		
		ImageDTO imgdto =  service.getImg(boardNum);
		System.out.println(imgdto.getImageName());
		System.out.println(imgdto.getImagePath());
		
		request.setAttribute("imgdto", imgdto);
		
		Forward forward = new Forward();
		forward.setForward(true);
		forward.setPath("/test.jsp");
		
		return forward;
	}
	
}
