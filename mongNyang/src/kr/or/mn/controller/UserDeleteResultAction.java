package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.service.UserService;

public class UserDeleteResultAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String userId = request.getParameter("userId");		
		
		
		UserService service = UserService.getInstance();
		service.deleteUser(userId);
		
		
		Forward forward = new Forward();
		forward.setForward(false);
		forward.setPath("/main.mn");                   
		
		return forward;
	}

}
