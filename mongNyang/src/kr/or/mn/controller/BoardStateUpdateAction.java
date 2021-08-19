package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;

public class BoardStateUpdateAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//해결상태 받아오기
		String state = request.getParameter("boardState");
		if(state!=null) {
			int boardState = Integer.parseInt(state);
		}
		
				
		Forward forward=new Forward();
		forward.setForward(false);
		forward.setPath("boardDetail?);
		
		return null;
	}

}
