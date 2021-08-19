package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.service.ReplyService;

public class ReplyDelAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("실행 확");
		
		String reply=request.getParameter("replyNum");
		System.out.println(reply);
		int replyNum=Integer.parseInt(request.getParameter("replyNum"));
		int boardNum=Integer.parseInt(request.getParameter("boardNum"));
		System.out.println("액션 댓글번호 : "+replyNum);
		System.out.println("액션 게시물번호:"+boardNum);
		
		ReplyService service=ReplyService.getInstance();
		service.replyDelete(replyNum, boardNum);
		
		Forward forward=new Forward();
		forward.setForward(false);
		forward.setPath("boarddetail.do?boardNum="+boardNum);
		
		
		return forward;
	}

}
