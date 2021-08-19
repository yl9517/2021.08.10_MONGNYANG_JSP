package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.UserDTO;
import kr.or.mn.service.UserService;

public class UserModifyResultAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String addr = request.getParameter("addr");
		
		
		UserService service = UserService.getInstance();
		UserDTO dto = new UserDTO();
		dto.setUserPwd(pwd);
		dto.setUserPhone(phone);
		dto.setUserEmail(email);
		dto.setUserAddr(addr);
		
		service.modifyUser(dto);
		 
		Forward forward = new Forward();
		forward.setForward(false);
		forward.setPath("usermain.do?userId="+id);		//수정
		
		return forward;
	}

}
