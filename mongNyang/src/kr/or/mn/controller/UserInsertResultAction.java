package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.UserDTO;
import kr.or.mn.service.UserService;

public class UserInsertResultAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String id = request.getParameter("id");
		String pwd =  request.getParameter("pwd");
		String pwdCheck = request.getParameter("pwdCheck");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String addr = request.getParameter("addr");
		
		if(pwd!=pwdCheck) {
			  
		}
		
		UserService service = UserService.getInstance();
		UserDTO dto = new UserDTO();
		dto.setUserId(id);
		dto.setUserPwd(pwd);
		dto.setUserPhone(phone);
		dto.setUserEmail(email);
		dto.setUserAddr(addr);
		
		int result = service.insertUser(dto);
		Forward forward = new Forward();
		if(result ==1) { //회원가입 완료 시 로그인 창으로 보내기
			forward.setForward(false);
			forward.setPath("userlogin.do");
		}else { //회원가입 실패 시 에러창

		}
		return forward;
		
	}

}
