package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.service.BoardService;

public class BoardStateModifyAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//게시글 번호
		String n=request.getParameter("boardNum");
		int boardNum=1;
		if(n!=null && !n.equals("")) {
			boardNum=Integer.parseInt(n);
		}
		
		//상태
		boolean boardState=false;
		String state=request.getParameter("boardState");
		if(state.equals("true")) { //홀수면 해결			
			boardState=true;
		}
		System.out.println("s"+state);
		System.out.println(boardState);
		
		//상태수정
		BoardService service = BoardService.getInstance();
		service.stateModify(boardNum, boardState);
		
		Forward forward=new Forward();
		forward.setForward(false);
		forward.setPath("boarddetail.do?boardNum="+boardNum);
		
		return forward;
	}

}
