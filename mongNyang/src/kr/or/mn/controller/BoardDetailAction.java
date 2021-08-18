package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.BoardDTO;
import kr.or.mn.service.BoardService;

public class BoardDetailAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String n=request.getParameter("boardNum");
		
		int boardnum=1;
		if(n!=null && !n.equals("")) {
			boardnum=Integer.parseInt(n);
		}
		
		BoardService service=BoardService.getInstance();
		
		BoardDTO dto=service.getDetail(boardnum);
		request.setAttribute("dto", dto);
		
		Forward forward=new Forward();	

		forward.setForward(true);
		forward.setPath("/view.jsp?page=board/boardDetail.jsp");

		return forward;
		
	}

}
