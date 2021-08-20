package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.service.BoardService;
import kr.or.mn.service.ImageService;

public class BoardDelAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String n=request.getParameter("boardNum");
		String boardType=request.getParameter("boardType");
		
		int boardnum=1;
		if(n!=null && !n.equals("")) {
			boardnum=Integer.parseInt(n);
		}		
		BoardService service=BoardService.getInstance();
		service.delete(boardnum); //게시글 삭제

		//이미지 삭제
		String imageName=request.getParameter("imageName");
		String imagePath=request.getParameter("imagePath");
		
		ImageService imgService = ImageService.getService();
		int imgNum = imgService.getImgNum(imageName, imagePath);
		imgService.delete(imgNum); 
		
		
		Forward forward=new Forward();	
		forward.setForward(false);
		forward.setPath("boardlist.do?boardType="+boardType);

		return forward;
	}

}
