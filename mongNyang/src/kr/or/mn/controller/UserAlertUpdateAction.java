package kr.or.mn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.AlertDTO;
import kr.or.mn.service.ReplyService;

public class UserAlertUpdateAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Forward forward = new Forward();
	
		//세션으로 아이디값 받기 
        String id = (String) request.getSession().getAttribute("userId");
        
        //if id가 null이면 로그인페이지로
        if(id==null) {
            forward.setForward(false);
            forward.setPath("userlogin.do");
        }
        else {
	        int replyNum = Integer.parseInt(request.getParameter("replyNum"));
	        int changeAlert = Integer.parseInt(request.getParameter("changeAlert"));
	        System.out.println("수정 상태!!!!!!!!!!!!:"+changeAlert+"로 바꿈 (댓글번호 = "+replyNum);
	        //service 연결
	        ReplyService service = ReplyService.getInstance();
	        service.myAlertUpdate(replyNum,changeAlert);
	        
			forward.setForward(false);
			forward.setPath("useralert.do");
        }
		return forward;
	}

}
