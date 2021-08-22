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
		System.out.println("alertUp 댓글번호 :"+rn);
		//세션으로 아이디값 받기 
		
		
        String id = (String) request.getSession().getAttribute("userId");
        
        //if id가 null이면 로그인페이지로
        if(id==null) {
            forward.setForward(false);
            forward.setPath("userlogin.do");
            System.out.println("여기로가나?");
        }
        else {       	
        	int replyNum = Integer.parseInt(rn);
        	int changeAlert = Integer.parseInt(request.getParameter("changeAlert"));
	        System.out.println("수정 상태!!!!!!!!!!!!:"+changeAlert+"로 바꿈 (댓글번호 = "+replyNum);
	        String boardN=request.getParameter("boardNum");
	        System.out.println(boardN);
        	int boardNum = Integer.parseInt(request.getParameter("boardNum"));
	        System.out.println("UserAlertUpdate에서의 "+boardNum);
	        //service 연결하여 댓글 상태 변경
	        ReplyService service = ReplyService.getInstance();
	        service.myAlertUpdate(replyNum,changeAlert);
	        
	        //세션 알아서 바뀌나..? call by reference?
//	        List<AlertDTO> dto = service.myAlert(id);
//	        request.getSession().setAttribute("alertdto", dto); //세션 재저장
			
			forward.setForward(false);
			forward.setPath("boarddetail.do?boardNum="+boardNum);
        }
		return forward;
	}

}
