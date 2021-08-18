package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;

public class BoardInsertAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String boardType=request.getParameter("boardType");
//		System.out.println(boardType);
		
		Forward forward=new Forward();
		forward.setForward(true);
		forward.setPath("/view.jsp?page=board/boardInsert.jsp?boardType="+boardType);
		
		return forward;
	}

}

//리스트에서 디테일 들어올때 boardType을 다시 set해 줘야하는지
//setPath 맞는지
//boardinsert.jsp 에서 action 경로