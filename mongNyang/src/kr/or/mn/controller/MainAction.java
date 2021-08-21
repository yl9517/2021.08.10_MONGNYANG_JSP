package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;

public class MainAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Forward forward = new Forward();
		forward.setForward(true);
		forward.setPath("/view.jsp?page=main.jsp");
		
//		RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
//		dis.forward(request, response);

		return forward;
	}

}
