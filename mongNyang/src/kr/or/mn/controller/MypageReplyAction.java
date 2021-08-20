package kr.or.mn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.ReplyDTO;
import kr.or.mn.service.ReplyService;

public class MypageReplyAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Forward forward=new Forward();
		
		//세션으로 아이디값 받기
		String userId = (String) request.getSession().getAttribute("userId");
		
		if(userId==null) {
		        	
		            forward.setForward(false);
		            forward.setPath("userlogin.do");
		        }
		
		else {
			//게시판서비스에서 받아오기
			ReplyService service=ReplyService.getInstance();
			List<ReplyDTO> mypagereplylist=service.mypageReply(userId);
			request.setAttribute("mypagereplylist", mypagereplylist );
			//System.out.println("my댓글 "+mypagereplylist.get(1).getReplyContent());

			forward.setForward(true);
			forward.setPath("/view.jsp?page=myPage/mypageReply.jsp");
			
		}
		return forward;
	}

}
