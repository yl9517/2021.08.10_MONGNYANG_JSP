package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;

public class UserLoginAction implements Action {
	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int result =0;
		String r = request.getParameter("result");
		if(r!=null) {
			result = Integer.parseInt(r);
		}
		request.setAttribute("result", result);
		//로그인 폼으로 가기
		Forward forward = new Forward();
		forward.setForward(true);
		forward.setPath("/view.jsp?page=user/login.jsp");
		 
		return forward;
	}

}
