package kr.or.mn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.PageDTO;
import kr.or.mn.dto.ReplyDTO;
import kr.or.mn.service.ReplyService;

public class UserReplyAction implements Action {

   @Override
   public Forward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      
      String curr=request.getParameter("curr");
      //세션으로 아이디값 받기
      String userId = (String) request.getSession().getAttribute("userId");
      
      int currpage=1;
      if(curr!=null) {
         currpage=Integer.parseInt(curr);
      }
            
      //전체 자료수
      ReplyService service=ReplyService.getInstance();
      int totalcount=service.getTotalCount(userId);
      int pageSize=6; //한페이지에 보여질 자료수
      request.setAttribute("totalcount", totalcount);

      PageDTO pdto = new PageDTO("", "", currpage, totalcount, pageSize);
      
      
      request.setAttribute("paging", pdto);
      
      
      //게시판서비스에서 받아오기
      List<ReplyDTO> mypagereplylist=service.mypageReply(userId, pdto);
      request.setAttribute("mypagereplylist", mypagereplylist );
      
      Forward forward=new Forward();
      forward.setForward(true);
      forward.setPath("/view.jsp?page=myPage/mypageReply.jsp");
      
      return forward;
   }

}