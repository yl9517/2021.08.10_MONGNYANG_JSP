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
import kr.or.mn.dto.PageDTO;
import kr.or.mn.service.ReplyService;

public class UserAlertAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Forward forward = new Forward();
		
		String curr=request.getParameter("curr");
		
		//세션으로 아이디값 받기 
        String id = (String) request.getSession().getAttribute("userId");
        
        
        int currpage=1;
        if(curr!=null) {
           currpage=Integer.parseInt(curr);
        }
        //if id가 null이면 로그인페이지로
        if(id==null) {
            forward.setForward(false);
            forward.setPath("userlogin.do");
        }
        else {
	        //service 연결
	        ReplyService service = ReplyService.getInstance(); 
	        int totalcount=service.getAlertTotalCount(id);
	        int pageSize=8; //한페이지에 보여질 자료수
	        
	        PageDTO pdto = new PageDTO("", "", currpage, totalcount, pageSize);
	        
	        
	        request.setAttribute("paging", pdto);
	        List<AlertDTO> dto = service.myAlert(id, pdto);

//			session.setMaxInactiveInterval(60*5);	// 세션유효시간 5분
			request.setAttribute("alertdto", dto); //알림세션 만들기
	        
			forward.setForward(true);
			forward.setPath("/view.jsp?page=user/myAlert.jsp");
        }
		return forward;
	}

}
