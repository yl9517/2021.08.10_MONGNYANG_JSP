package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.MainDTO;
import kr.or.mn.service.BoardService;

public class BoardModifyResultAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		Forward forward = new Forward();

		// 세션으로 아이디값 받기
		String id = (String) request.getSession().getAttribute("userId");

		// if id가 null이면 로그인페이지로
		if (id == null) {
			forward.setForward(false);
			forward.setPath("userlogin.do");
		} else {

			String n = request.getParameter("boardNum");
			int boardNum = 1;
			if (n != null && !n.equals("")) {
				boardNum = Integer.parseInt(n);
			}

			String boardType = request.getParameter("boardType");
			String boardTitle = request.getParameter("boardTitle");
			String petAddr = request.getParameter("petAddr");
			String petType = request.getParameter("petType");
			String boardContent = request.getParameter("boardContent");
//		String photo=requset.getParameter("photo");

			BoardService service = BoardService.getInstance();
			String categoryName = service.findCategoryName(boardType, petAddr, petType);

			MainDTO dto = new MainDTO();
			dto.setBoardType(boardType);
			dto.setBoardNum(boardNum);
			dto.setBoardTitle(boardTitle);
			dto.setPetAddr(petAddr);
			dto.setPetType(petType);
			dto.setBoardContent(boardContent);
			dto.setCategoryName(categoryName);

			service.modify(dto);

			forward.setForward(false);
			forward.setPath("boarddetail.do?boardNum=" + boardNum);
		}
		return forward;
	}

}
