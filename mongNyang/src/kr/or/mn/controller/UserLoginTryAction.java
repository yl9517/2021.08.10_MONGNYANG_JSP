package kr.or.mn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.AlertDTO;
import kr.or.mn.service.ReplyService;
import kr.or.mn.service.UserService;

public class UserLoginTryAction implements Action{

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("pwd");

		UserService service = UserService.getInstance();
		int result = service.tryLogin(userId, userPwd);
		
		
		Forward forward = new Forward();
		if(result ==1) { //로그인 성공 시 메인으로
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60*5);	// 세션유효시간 5분
			session.setAttribute("userId", userId); //로그인 세션 만들기
			
			forward.setForward(true);
			forward.setPath("main.do");
			
		}else { 
			forward.setForward(false);
			forward.setPath("userlogin.do?result="+result);
		}
		return forward;
	}

}
