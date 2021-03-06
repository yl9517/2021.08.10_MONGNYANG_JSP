package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.UserDTO;

public class UserSignupAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String use = request.getParameter("useId");
		String writeId = request.getParameter("writeId");
		
		int useId = -1; 
		if(use!=null) { //아이디 중복확인번호
			useId = Integer.parseInt(use);
		}

		request.setAttribute("useId",useId);
		request.setAttribute("writeId",writeId);
			
		Forward forward = new Forward();
		forward.setForward(true);
		forward.setPath("/view.jsp?page=user/signup.jsp");
		 
		return forward;
	}

}
