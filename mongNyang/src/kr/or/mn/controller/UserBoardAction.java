package kr.or.mn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.BoardDTO;
import kr.or.mn.dto.PageDTO;
import kr.or.mn.service.BoardService;

public class UserBoardAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		Forward forward = new Forward();
		
		String curr=request.getParameter("curr");
		
		//세션으로 아이디값 받기 
        String userId = (String) request.getSession().getAttribute("userId");

        int currpage=1;
		if(curr!=null) {
			currpage=Integer.parseInt(curr);
		}
				
		//전체 자료수
		BoardService service=BoardService.getInstance();
		int totalcount=service.getUserBoardTotalCount(userId);
		int pageSize=5; //한페이지에 보여질 자료수
		request.setAttribute("totalcount", totalcount);
		PageDTO pdto = new PageDTO("", "", currpage, totalcount, pageSize);
		
		request.setAttribute("paging", pdto);
        
        
        //if id가 null이면 로그인페이지로
        if(userId==null) {
        	
            forward.setForward(false);
            forward.setPath("userlogin.do");
        }else {
        
	     //게시판서비스에서 받아오기
        List<BoardDTO> list = service.findMyWrite(userId, pdto);
        request.setAttribute("list",list);
		
		forward.setForward(true);
		forward.setPath("/view.jsp?page=myPage/mypageBoard.jsp");		
        }
		return forward;
	}

}
