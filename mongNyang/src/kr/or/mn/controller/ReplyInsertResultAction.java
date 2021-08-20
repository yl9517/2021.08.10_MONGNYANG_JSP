package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.ReplyDTO;
import kr.or.mn.service.ReplyService;

public class ReplyInsertResultAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
	
		//아이디 세션으로 받아오기		
		Forward forward = new Forward();

		//세션으로 아이디값 받기 
        String userId = (String) request.getSession().getAttribute("userId");

        
        
		
		// 게시글번호, 내용 받아오기
		int boardNum = 1;
		String bn= request.getParameter("reBoardNum");
		if(bn!=null) {
			boardNum = Integer.parseInt(bn);
		}
		String replyContent=request.getParameter("replyContent");
		
		//서비스 접근(번호,내용) => 보이드
		ReplyDTO dto = new ReplyDTO();
		ReplyService service=ReplyService.getInstance();
		
		dto.setBoardNum(boardNum);
		dto.setUserId(userId);
		dto.setReplyContent(replyContent);
		 
		int result = service.insertReply(dto);
		
		request.setAttribute("result", result);
		// 페이지 이동, 페이지경로 정해주기(포워드)
		
		forward.setForward(false);
		forward.setPath("boarddetail.do?boardNum="+boardNum);
		
		return forward;
		
		
	}

}
