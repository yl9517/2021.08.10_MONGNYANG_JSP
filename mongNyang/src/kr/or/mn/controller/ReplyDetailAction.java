package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.service.ReplyService;

public class ReplyDetailAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int replyNum=Integer.parseInt(request.getParameter("replyNum"));
		
		ReplyService service=ReplyService.getInstance();
		service.replyDetail(replyNum);
		
		Forward forward=new Forward();
		forward.setForward(true);
		/* forward.setPath(""); */
		
		return forward;
	}

}
