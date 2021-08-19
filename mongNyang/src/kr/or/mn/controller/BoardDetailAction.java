package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.CategoryDTO;
import kr.or.mn.dto.ImageDTO;
import kr.or.mn.dto.MainDTO;
import kr.or.mn.service.BoardService;
import kr.or.mn.service.ImageService;

public class BoardDetailAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		String n=request.getParameter("boardNum");
		int boardNum=1;
		if(n!=null && !n.equals("")) {
			boardNum=Integer.parseInt(n);
		}
		
		BoardService service=BoardService.getInstance();
		ImageService iservice=ImageService.getService();
		MainDTO dto=service.getDetail(boardNum);
		CategoryDTO categorys = service.findCategoryContent(dto.getCategoryName()); //dto의 카테고리 이름 가져오기
		dto.setPetAddr(categorys.getPetAddr());
		dto.setPetType(categorys.getPetType());
		ImageDTO imgdto=iservice.getImg(boardNum);
		dto.setImageName(imgdto.getImageName());
		dto.setImageNum(imgdto.getImageNum());
		dto.setImagePath(imgdto.getImagePath());
		
		request.setAttribute("dto", dto);
		
		Forward forward=new Forward();	

		forward.setForward(true);
		forward.setPath("/view.jsp?page=board/boardDetail.jsp");

		return forward;
		
	}

}
