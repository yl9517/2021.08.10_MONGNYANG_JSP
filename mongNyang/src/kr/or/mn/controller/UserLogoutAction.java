package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;

public class UserLogoutAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.getSession().invalidate(); //로그아웃 (세션삭제)
			
			//알림창 띄우고?
			
			Forward forward = new Forward();			
			forward.setForward(true);
			forward.setPath("main.do"); //메인으로 돌아가기
			
		return forward;
	}

}
