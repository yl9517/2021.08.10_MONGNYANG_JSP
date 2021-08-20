package kr.or.mn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.AlertDTO;
import kr.or.mn.service.ReplyService;

public class UserAlertAction implements Action {

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
	        //service 연결
	        ReplyService service = ReplyService.getInstance();    
	        List<AlertDTO> dto = service.myAlert(id);
	        
//	        HttpSession session = request.getSession();
//			session.setMaxInactiveInterval(60*5);	// 세션유효시간 5분
			request.setAttribute("alertdto", dto); //알림세션 만들기
	        
			forward.setForward(true);
			forward.setPath("/view.jsp?page=user/myAlert.jsp");
        }
		return forward;
	}

}
