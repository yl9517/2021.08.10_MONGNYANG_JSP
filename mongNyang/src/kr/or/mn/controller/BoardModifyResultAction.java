package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.MainDTO;
import kr.or.mn.service.BoardService;
import kr.or.mn.service.ImageService;

public class BoardModifyResultAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Forward forward = new Forward();

		// 세션으로 아이디값 받기
		System.out.println(29);
		String id = (String) request.getSession().getAttribute("userId");

		// if id가 null이면 로그인페이지로
		if (id == null) {
			forward.setForward(false);
			forward.setPath("userlogin.do");
			System.out.println(36);
		} else {

			String n = request.getParameter("boardNum");
			int boardNum = 1;
			if (n != null && !n.equals("")) {
				boardNum = Integer.parseInt(n);
				System.out.println(43);
			}
				// 이전에 등록했던 이미지
				System.out.println(46);

				String beforeimageName = request.getParameter("imageName");
				String beforeimagePath = request.getParameter("imagePath");
		
				String uploadPath = request.getRealPath("upload");

				int size = 10 * 1024 * 1024;
				
				//새로 수정한 이미지
				String imageName = "";
				String imagePath = "";

				String boardType = "";
				MainDTO dto = new MainDTO();
				BoardService service = BoardService.getInstance();
				System.out.println(62);

				try {
					MultipartRequest multi = new MultipartRequest( // MultipartRequest 인스턴스 생성(cos.jar의 라이브러리)
							request, uploadPath, // 파일을 저장할 디렉토리 지정
							10 * 1024 * 1024, // 첨부파일 최대 용량 설정(bite)
							"utf-8", // 인코딩 방식 지정
							new DefaultFileRenamePolicy()); // 중복 파일 처리(동일한 파일명이 업로드되면 뒤에 숫자 등을 붙여 중복 회피)
					System.out.println(0);

					imagePath = multi.getFilesystemName("file1"); // name=file1의 업로드된 시스템 파일명을 구함(중복된 파일이 있으면, 중복 처리 후
																	// 파일 이름)
					imageName = multi.getOriginalFileName("file1"); // name=file1의 업로드된 원본파일 이름을 구함(중복 처리 전 이름)
					System.out.println(1);
					boardType = request.getParameter("boardType");
					String boardTitle = multi.getParameter("boardTitle");
					String petAddr = multi.getParameter("petAddr");
					String petType = multi.getParameter("petType");
					String boardContent = multi.getParameter("boardContent");
					int imageNum = Integer.parseInt(multi.getParameter("imageNum"));
					String categoryName = service.findCategoryName(boardType, petAddr, petType);
					System.out.println(2);

					dto.setBoardType(boardType);
					dto.setBoardNum(boardNum);
					dto.setBoardTitle(boardTitle);
					dto.setPetAddr(petAddr);
					dto.setPetType(petType);
					dto.setBoardContent(boardContent);
					dto.setCategoryName(categoryName);
					dto.setImageName(imageName); // 새로 넣은 사진
					dto.setImagePath("upload/" + imagePath);
					dto.setImageNum(imageNum);
					System.out.println(3);

				} catch (Exception e) {
					e.printStackTrace();
				}
				service.modify(dto);
				
				if (imagePath != null) { //이미지 수정하였으면
					ImageService iservice = ImageService.getService();
					iservice.modify(dto);
					System.out.println(4);

				} else { //이미지를 뺐으면
					ImageService iservice = ImageService.getService();
					iservice.delete(dto);
					System.out.println(5);

				}

				forward.setForward(false);
				forward.setPath("boarddetail.do?boardNum=" + boardNum);
			}

		
		return forward;

	}
}
