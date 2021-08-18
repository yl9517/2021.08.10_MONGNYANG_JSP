package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.BoardDTO;
import kr.or.mn.service.BoardService;

public class BoardInsertResultAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String boardTitle=request.getParameter("boardTitle");
		String boardType=request.getParameter("boardType");
		String petAddr=request.getParameter("petAddr");
		String petType=request.getParameter("petType");
		String boardContent=request.getParameter("boardContent");
		/* String photo=request.getParameter("photo"); */
		BoardService service=BoardService.getInstance();
		String categoryName=service.findCategory(boardType, petAddr, petType);
		
		BoardDTO dto=new BoardDTO();
		dto.setBoardTitle(boardTitle);
		dto.setBoardContent(boardContent);
		dto.setCategoryName(categoryName);
		
		int result=service.insertData(dto);
		/* dto constructor만들어서 넘기기 */
		
		request.setAttribute("result", result);
		
		Forward forward=new Forward();
		forward.setForward(false);
		forward.setPath("boardList.do");
		return forward;
	}

}
