package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.ReplyDTO;
import kr.or.mn.service.ReplyService;

public class ReplyModifyAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		
		//댓글 수정 감을 못잡겠습니다 ㅜㅜ
		/*
		 * int replyNum=Integer.parseInt(request.getParameter("replyNum")); int
		 * boardNum=Integer.parseInt(request.getParameter("boardNum")); int
		 * replyContent=Integer.parseInt(request.getParameter("replyContent"));
		 * 
		 * ReplyService service=ReplyService.getInstance();
		 * service.replyModify(replyNum, boardNum, replyContent);
		 * 
		 * Forward forward=new Forward(); forward.setForward(false);
		 * forward.setPath("boarddetail.do?boardNum="+boardNum);
		 * 
		 */
		
		return null;
	}

}
