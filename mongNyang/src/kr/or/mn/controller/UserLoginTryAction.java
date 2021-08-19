package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.service.UserService;

public class UserLoginTryAction implements Action{

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("pwd");
		System.out.println("아디 :"+userId);
		System.out.println("비번 :"+userPwd);
		UserService service = UserService.getInstance();
		int result = service.tryLogin(userId, userPwd);
		
		Forward forward = new Forward();
		
		
		if(result ==1) { //로그인 성공 시 메인으로
			System.out.println("성공"+result);
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId); //세션 만들기
			
			forward.setForward(true);
			forward.setPath("/view.jsp?page=main.mn");
		}else { //실패 시 로그인창으로 -> 알림창 뜨게 어떻게?
			System.out.println("실패"+result);
			forward.setForward(false);
			forward.setPath("userlogin.do");
		}
		return forward;
	}

}
