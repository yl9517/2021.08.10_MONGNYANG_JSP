package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.UserDTO;
import kr.or.mn.service.UserService;

public class UserModifyInfoAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Forward forward = new Forward();

		//세션으로 아이디값 받기 
		String id = (String) request.getSession().getAttribute("userId");		
		
		//if id가 null이면 로그인페이지로
		if(id==null) {
			forward.setForward(false);
			forward.setPath("userlogin.do");		
		}
		else {
			//유저서비스로 접근 => dto로 받
			UserService service = UserService.getInstance();
			UserDTO dto = new UserDTO();
			dto = service.selectUser(id);
			
			request.setAttribute("dto", dto);
			
			forward.setForward(true);
			forward.setPath("myPage/mypageModify.jsp");
		}
		return forward;
		
	}

}
