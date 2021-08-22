package kr.or.mn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.PagingDTO;
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
      int pagepercount=5; //한페이지에 보여질 자료수
      
      int totalpage=(int) Math.ceil((float)totalcount/pagepercount);
      int startrow=(currpage-1)*pagepercount+1;
      int endrow=startrow+pagepercount-1;
      if(endrow>totalcount) {
         endrow=totalcount;
      }
      
      int blockcount=5; //한 페이지에 보여질 최대 페이지 수
      int startblock=((currpage-1)/blockcount)*blockcount+1;
      int endblock=startblock+blockcount-1;
      if(endblock>totalpage) {
         endblock=totalpage;
      }
      
      PagingDTO pdto=new PagingDTO();
      pdto.setTotalcount(totalcount);
      pdto.setPagepercount(pagepercount);
      pdto.setTotalpage(totalpage);
      pdto.setStartrow(startrow);
      pdto.setEndrow(endrow);
      pdto.setBlockcount(blockcount);
      pdto.setStartblock(startblock);
      pdto.setEndblock(endblock);
      pdto.setCurrpage(currpage);
      
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