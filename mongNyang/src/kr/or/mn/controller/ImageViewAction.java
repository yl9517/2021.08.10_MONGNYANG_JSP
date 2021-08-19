package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.ImageDTO;
import kr.or.mn.dto.MainDTO;
import kr.or.mn.service.ImageService;

public class ImageViewAction implements Action{

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		String n=request.getParameter("boardNum");
		int boardNum=8;
//		if(n!=null && !n.equals("")) {
//			boardNum=Integer.parseInt(n);
//		}
		
		ImageService service = ImageService.getService();		
		MainDTO dto =  service.getImg(boardNum);

		
		request.setAttribute("imgdto", dto);
		
		Forward forward = new Forward();
		forward.setForward(true);
		forward.setPath("/test.jsp");
		
		return forward;
	}
	
}
