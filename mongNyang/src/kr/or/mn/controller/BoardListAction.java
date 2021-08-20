package kr.or.mn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.MainDTO;
import kr.or.mn.service.BoardService;

public class BoardListAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String boardType=request.getParameter("boardType");
		
		String petAddr="";
		String paddr = request.getParameter("petAddr");
		if(paddr!=null) { //paddr이 널값이 아니면 대입
			petAddr=paddr;
		}
		
		BoardService service=BoardService.getInstance();
		
		List<MainDTO> list=service.getList(boardType,petAddr);
		request.setAttribute("list", list);
		request.setAttribute("boardType", boardType);
			
		Forward forward=new Forward();
		forward.setForward(true);
		forward.setPath("/view.jsp?page=board/boardList.jsp");
		
		return forward;
	}

}
