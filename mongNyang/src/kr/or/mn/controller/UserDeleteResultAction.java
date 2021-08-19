package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.service.UserService;

public class UserDeleteResultAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		UserService service = UserService.getInstance();
		
		int result = service.deleteUser(userId);
		if(result==1)
			request.getSession().invalidate();
		else
			System.out.println("탈퇴실패");

		
		
		Forward forward = new Forward();
		forward.setForward(true);
		forward.setPath("/view.jsp?page=main.mn");                   

		return forward;
	}

}
