package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.UserDTO;
import kr.or.mn.service.UserService;

public class UserSignOverlapAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {  //회원가입 시 아이디 중복확인

		String writeId = request.getParameter("writeId");
	
		//유저서비스로 접근 => dto로 받
		UserService service = UserService.getInstance();
		UserDTO dto = new UserDTO();
		dto = service.selectUser(writeId);

		int useId = -1;//중복 아이디 여부
		if(dto.getUserId() == null) {	//중복 아이디 없음
			useId=0;
		}else {	//중복아이디 있음
			useId=1;
		}
		
		Forward forward = new Forward();
		forward.setForward(false);
		forward.setPath("usersignup.do?useId="+useId+"&writeId="+writeId);
		return forward;
	}

}
