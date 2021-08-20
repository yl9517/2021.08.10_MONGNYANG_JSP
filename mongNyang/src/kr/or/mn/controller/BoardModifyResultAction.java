package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.ImageDTO;
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
		String id = (String) request.getSession().getAttribute("userId");

		// if id가 null이면 로그인페이지로
		if (id == null) {
			forward.setForward(false);
			forward.setPath("userlogin.do");
		} else {

			// 이전에 등록했던 이미지
	
			String uploadPath = request.getRealPath("upload");

			int size = 10 * 1024 * 1024;
			String beforeimageName = "";
			String beforeimagePath ="";
			//새로 수정한 이미지
			String imageName = "";
			String imagePath = "";
			String boardType = "";
			int boardNum = 0;
			int imageNum = 0;
			MainDTO dto = new MainDTO();
			BoardService service = BoardService.getInstance();

				try {
					MultipartRequest multi = new MultipartRequest( // MultipartRequest 인스턴스 생성(cos.jar의 라이브러리)
							request, uploadPath, // 파일을 저장할 디렉토리 지정
							10 * 1024 * 1024, // 첨부파일 최대 용량 설정(bite)
							"utf-8", // 인코딩 방식 지정
							new DefaultFileRenamePolicy()); // 중복 파일 처리(동일한 파일명이 업로드되면 뒤에 숫자 등을 붙여 중복 회피)

					beforeimageName = multi.getParameter("imageName");
					beforeimagePath = multi.getParameter("imagePath");
					
					imagePath = multi.getFilesystemName("file1"); // name=file1의 업로드된 시스템 파일명을 구함(중복된 파일이 있으면, 중복 처리 후																	// 파일 이름)
					imageName = multi.getOriginalFileName("file1"); // name=file1의 업로드된 원본파일 이름을 구함(중복 처리 전 이름)
				
					boardNum = Integer.parseInt(multi.getParameter("bNum"));
					String boardTitle = multi.getParameter("boardTitle");
					String boardContent = multi.getParameter("boardContent");
					String petAddr = multi.getParameter("petAddr");
					String petType = multi.getParameter("petType");
					boardType = multi.getParameter("bType");	
					String categoryName = service.findCategoryName(boardType, petAddr, petType); //카테고리 이름 찾기						
					imageNum = Integer.parseInt(multi.getParameter("imageNum"));

					dto.setBoardNum(boardNum);
					dto.setBoardTitle(boardTitle);
					dto.setBoardContent(boardContent);
					dto.setUserId(id);
					dto.setCategoryName(categoryName);
					dto.setBoardType(boardType);
					dto.setPetAddr(petAddr);
					dto.setPetType(petType);
					dto.setImageNum(imageNum);

				} catch (Exception e) {
					e.printStackTrace();
				}
				
				ImageService imgservice = ImageService.getService();
				ImageDTO imgdto = new ImageDTO();
				
				if (imagePath != null) { //이미지 수정하였으면
					imgdto.setImageName(imageName);
					imgdto.setImagePath("upload/"+imagePath);		
				} else { //수정 안되었으면 이전 이미지 그대로
					imgdto.setImageName(beforeimageName);
					imgdto.setImagePath(beforeimagePath);					
				}	
				imgdto.setImageNum(imageNum);	
				imgservice.modify(imgdto); //이미지 테이블 수정
				
				dto.setImageName(imgdto.getImageName()); // dto에 추가
				dto.setImagePath(imgdto.getImagePath());	
										
				service.modify(dto); //mainDTO 수정
				
				forward.setForward(false);
				forward.setPath("boarddetail.do?boardNum="+boardNum);
			}
		
		return forward;

	}
}
