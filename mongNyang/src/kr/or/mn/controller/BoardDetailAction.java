package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.CategoryDTO;
import kr.or.mn.dto.ImageDTO;
import kr.or.mn.dto.MainDTO;
import kr.or.mn.service.BoardService;
import kr.or.mn.service.ImageService;

public class BoardDetailAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		Forward forward = new Forward();

		//세션으로 아이디값 받기 
        String id = (String) request.getSession().getAttribute("userId");

        //if id가 null이면 로그인페이지로
        if(id==null) {
            forward.setForward(false);
            forward.setPath("userlogin.do");
        }
        else {

		String n=request.getParameter("boardNum");
		int boardNum=1;
		if(n!=null && !n.equals("")) {
			boardNum=Integer.parseInt(n);
		}
		
		BoardService service=BoardService.getInstance(); 
		ImageService iservice=ImageService.getService();
		MainDTO dto=service.getDetail(boardNum);
		CategoryDTO categorys = service.findCategoryContent(dto.getCategoryName()); //dto의 카테고리 이름 가져오기
		dto.setLoginId(id);		//로그인 아이디
		dto.setUserId(dto.getUserId());		//글쓴이 아이디
		dto.setPetAddr(categorys.getPetAddr());
		dto.setPetType(categorys.getPetType());
		
		ImageDTO imgdto=iservice.getImg(boardNum); //boardNum으로 이미지 가져오기
		dto.setImageName(imgdto.getImageName());
		dto.setImageNum(imgdto.getImageNum());
		dto.setImagePath(imgdto.getImagePath());
		
		request.setAttribute("dto", dto);
		
		forward.setForward(true);
		forward.setPath("/view.jsp?page=board/boardDetail.jsp");
        }
        
		return forward;
      
		
	}

}
