package kr.or.mn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.BoardDTO;
import kr.or.mn.service.BoardService;

public class BoardListAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//String boardType=request.getParameter("boardType");
		String boardType="FIND";
		
		BoardService service=BoardService.getInstance();
		
		List list=service.getList(boardType);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		request.setAttribute("list", list);
			
		Forward forward=new Forward();
		forward.setForward(true);
		forward.setPath("/view.jsp?page=board/boardList.jsp");
		
		return forward;
	}

}
