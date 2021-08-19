package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.UserDTO;
import kr.or.mn.service.UserService;

public class UserFindPwdResultAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		String userId = request.getParameter("id");
		String userEmail = request.getParameter("email");
			
		UserService service = UserService.getInstance();
		String findPwd = service.searchPwd(userId, userEmail);
		
		request.setAttribute("findPwd",findPwd);
				
		Forward forward = new Forward();
		forward.setForward(true);
		forward.setPath("/view.jsp?page=user/findPwdResult.jsp");

		return forward;
		
		
	}

}
