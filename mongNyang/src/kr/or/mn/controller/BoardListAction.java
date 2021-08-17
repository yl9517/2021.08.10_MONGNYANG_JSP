package kr.or.mn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.BoardDTO;
import kr.or.mn.service.BoardService;

public class BoardListAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		HttpSession session=request.getSession();
//		String userId=(String) session.getAttribute("userId");
		
		Forward forward=new Forward();
//		if(userId==null) {
//			forward.setForward(false);
//			forward.setPath("login.do");
//			
//		}else {
			BoardService service=BoardService.getInstance();
			
			List<BoardDTO> list=service.getList();
			request.setAttribute("list", list);
			System.out.println("list......"+list.size());
			
			forward.setForward(true);
			forward.setPath("board/boardList.jsp");
//		}
		return forward;
	}

}
