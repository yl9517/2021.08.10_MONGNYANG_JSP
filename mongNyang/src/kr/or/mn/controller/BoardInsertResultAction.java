package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.MainDTO;
import kr.or.mn.service.BoardService;

public class BoardInsertResultAction implements Action { //게시글 등록

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		String boardType=request.getParameter("boardType");
		String boardTitle=request.getParameter("boardTitle");
		String petAddr=request.getParameter("petAddr");
		String petType=request.getParameter("petType");
		String boardContent=request.getParameter("boardContent");
		String imageName=request.getParameter("imageName");
		BoardService service=BoardService.getInstance();
//		String categoryName=service.findCategoryName(boardType, petAddr, petType);
		
		MainDTO dto=new MainDTO();
		dto.setBoardType(boardType);
		dto.setBoardTitle(boardTitle);
		dto.setPetAddr(petAddr);
		dto.setPetType(petType);
		dto.setBoardContent(boardContent);
		
		dto.setCategoryName(service.findCategoryName(boardType, petAddr, petType));
		service.insertData(dto);
		
		Forward forward=new Forward();
		forward.setForward(false);
		forward.setPath("boardlist.do?boardType="+boardType);
		
//		나중에 board.Detail.do에 boardnum 이랑 같이 보내야함
		
		return forward;
	}

}
