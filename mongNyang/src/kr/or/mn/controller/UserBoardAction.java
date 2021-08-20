package kr.or.mn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.BoardDTO;
import kr.or.mn.service.BoardService;
import kr.or.mn.service.UserService;

public class UserBoardAction implements Action {

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
            
        }else {
        
	     //게시판서비스에서 받아오기
        BoardService service = BoardService.getInstance();
        List<BoardDTO> list = service.findMyWrite(id);
        request.setAttribute("list",list);
		
		forward.setForward(true);
		forward.setPath("/view.jsp?page=myPage/mypageBoard.jsp");		
        }
		return forward;
	
	}

}
