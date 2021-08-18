package kr.or.mn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.ReplyDTO;
import kr.or.mn.service.ReplyService;

public class ReplyListAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ReplyService service=ReplyService.getInstance();
		
		List<ReplyDTO> list=service.getReplayList();
		request.setAttribute("list", list);
		
		Forward forward=new Forward();
		forward.setForward(true);
		/*
		 * forward.setPath("/view.jsp?page=);
		 */		
		return null;
	}

}
