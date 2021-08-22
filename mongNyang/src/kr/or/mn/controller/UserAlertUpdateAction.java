package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.service.ReplyService;

public class UserAlertUpdateAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Forward forward = new Forward();
		String rn = request.getParameter("replyNum");
		//세션으로 아이디값 받기 
		
		
        String id = (String) request.getSession().getAttribute("userId");
        
        //if id가 null이면 로그인페이지로
        if(id==null) {
            forward.setForward(false);
            forward.setPath("userlogin.do");
        }
        else {       	
        	int replyNum = Integer.parseInt(rn);
        	int changeAlert = Integer.parseInt(request.getParameter("changeAlert"));
        	int boardNum=0;
        	if(changeAlert!=2)
        	boardNum = Integer.parseInt(request.getParameter("boardNum"));
	        //service 연결하여 댓글 상태 변경
	        ReplyService service = ReplyService.getInstance();
	        service.myAlertUpdate(replyNum,changeAlert);

	        //세션 알아서 바뀌나..? call by reference?
//	        List<AlertDTO> dto = service.myAlert(id);
//	        request.getSession().setAttribute("alertdto", dto); //세션 재저장
			
			forward.setForward(false);
			if(changeAlert==2)
				forward.setPath("useralert.do");
			else
				forward.setPath("boarddetail.do?boardNum="+boardNum);

        }
		return forward;
	}

}
