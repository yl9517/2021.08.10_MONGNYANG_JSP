package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.ImageDTO;
import kr.or.mn.dto.MainDTO;
import kr.or.mn.service.BoardService;
import kr.or.mn.service.ImageService;

public class BoardModifyAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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

			BoardService service = BoardService.getInstance();
			MainDTO dto = service.getDetail(boardNum);
			ImageService iservice = ImageService.getService();
			ImageDTO idto=iservice.getImg(boardNum);
			dto.setImageName(idto.getImageName());
			dto.setImagePath(idto.getImagePath());
			dto.setImageNum(idto.getImageNum());
			request.setAttribute("dto", dto);

			forward.setForward(true);
			forward.setPath("/view.jsp?page=board/boardModify.jsp");
		}
		return forward;

		// modify리절트 분리하기~
	}

}
