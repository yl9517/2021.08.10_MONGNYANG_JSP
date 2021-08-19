package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.MainDTO;
import kr.or.mn.service.BoardService;

public class BoardModifyAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String n=request.getParameter("boardNum");
		int boardNum=1;
		if(n!=null && !n.equals("")) {
			boardNum=Integer.parseInt(n);
		}
		
		BoardService service=BoardService.getInstance();
		MainDTO dto=service.getDetail(boardNum);
		
		request.setAttribute("dto", dto);
		
		Forward forward=new Forward();	
		forward.setForward(true);
		forward.setPath("/view.jsp?page=board/boardModify.jsp");
		
		return forward;
		
		//modify리절트 분리하기~
	}

}
