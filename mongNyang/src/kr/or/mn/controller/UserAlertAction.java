package kr.or.mn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.AlertDTO;
import kr.or.mn.service.BoardService;

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

        //service 연결
        BoardService service = BoardService.getInstance();
        List<AlertDTO> dto = service.myAlert(id);
        
        request.setAttribute("dto", dto);
        
		forward.setForward(true);
		forward.setPath("/view.jsp?page=user/myAlert.jsp");
		
		return null;
	}

}
