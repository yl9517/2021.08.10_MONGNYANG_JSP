package kr.or.mn.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.MainDTO;
import kr.or.mn.dto.PagingDTO;
import kr.or.mn.service.BoardService;

public class BoardListAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String curr=request.getParameter("curr");
		
		int currpage=1;
		if(curr!=null) {
			currpage=Integer.parseInt(curr);
		}
		
		String search=request.getParameter("search");
		String searchtxt=request.getParameter("searchtxt");
		
		if(search==null) search="";
		if(searchtxt==null) searchtxt="";
		
		//전체 자료수
		BoardService service=BoardService.getInstance();
		int totalcount=service.getTotalCount(search, searchtxt);
		int pagepercount=6;	//한페이지에 보여질 자료수
		
		int totalpage=(int) Math.ceil((float)totalcount/pagepercount);
		int startrow=(currpage-1)*totalpage+1;
		int endrow=startrow+pagepercount-1;
		if(endrow>totalcount) {
			endrow=totalcount;
		}
		
		int blockcount=5;
		int startblock=(currpage-1)/blockcount*blockcount+1;
		int endblock=startblock+blockcount-1;
		if(endblock>totalpage) {
			endblock=totalpage;
		}
		
		PagingDTO dto=new PagingDTO();
		dto.setSearch(searchtxt);
		dto.setSearchtxt(searchtxt);
		dto.setTotalcount(totalcount);
		dto.setPagepercount(pagepercount);
		dto.setTotalpage(totalpage);
		dto.setStartrow(startrow);
		dto.setEndrow(endrow);
		dto.setBlockcount(blockcount);
		dto.setStartblock(startblock);
		dto.setEndblock(endblock);
		dto.setCurrpage(currpage);
		
		request.setAttribute("paging", dto);
		String boardType=request.getParameter("boardType");
		
		String petAddr="all";
		String paddr = request.getParameter("petAddr");
		if(paddr!=null) { //paddr이 널값이 아니면 대입
			petAddr=paddr;
		}
		
		
		
		List<MainDTO> list=service.getList(boardType,petAddr, dto);
		request.setAttribute("list", list);
		request.setAttribute("boardType", boardType);
			
		Forward forward=new Forward();
		forward.setForward(true);
		forward.setPath("/view.jsp?page=board/boardList.jsp");
		
		return forward;
	}

}
