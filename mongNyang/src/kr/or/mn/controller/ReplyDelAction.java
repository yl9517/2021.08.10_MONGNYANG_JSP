package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dao.ReplyDAO;
import kr.or.mn.service.ReplyService;

public class ReplyDelAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int replyNum=Integer.parseInt(request.getParameter("replyNum"));
		int boardNum=Integer.parseInt(request.getParameter("boardNum"));
		
		ReplyService service=ReplyService.getInstance();
		service.replyDelete(replyNum, boardNum);
		
		Forward forward=new Forward();
		forward.setForward(false);
		forward.setPath("boarddetail.do?boardNum="+boardNum);
		
		
		return forward;
	}

}
