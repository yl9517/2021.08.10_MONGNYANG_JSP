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

public class BoardInsertResultAction implements Action { // 게시글 등록

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Forward forward = new Forward();

		//세션으로 아이디값 받기 
        String id = (String) request.getSession().getAttribute("userId");

        //if id가 null이면 로그인페이지로
        if(id==null) {
            forward.setForward(false);
            forward.setPath("userlogin.do");
        }
        else {

			String fileName1 = "";
			String orgfileName1 = "";
	
			String uploadPath = request.getRealPath("upload");
	
			int size = 10 * 1024 * 1024;
	
			String imageName = "";
			String boardType = "";
			MainDTO dto = new MainDTO();
			BoardService service = BoardService.getInstance();
	
			try {
				MultipartRequest multi = new MultipartRequest( // MultipartRequest 인스턴스 생성(cos.jar의 라이브러리)
						request, uploadPath, // 파일을 저장할 디렉토리 지정
						10 * 1024 * 1024, // 첨부파일 최대 용량 설정(bite)
						"utf-8", // 인코딩 방식 지정
						new DefaultFileRenamePolicy()); // 중복 파일 처리(동일한 파일명이 업로드되면 뒤에 숫자 등을 붙여 중복 회피)
	
				fileName1 = multi.getFilesystemName("file1"); // name=file1의 업로드된 시스템 파일명을 구함(중복된 파일이 있으면, 중복 처리 후 파일 이름)
				orgfileName1 = multi.getOriginalFileName("file1"); // name=file1의 업로드된 원본파일 이름을 구함(중복 처리 전 이름)
	
				boardType = multi.getParameter("boardType");
				String boardTitle = multi.getParameter("boardTitle");
				String petAddr = multi.getParameter("petAddr");
				String petType = multi.getParameter("petType");
				String boardContent = multi.getParameter("boardContent");
				dto.setUserId(id);
				dto.setBoardType(boardType);
				dto.setBoardTitle(boardTitle);
				dto.setPetAddr(petAddr);
				dto.setPetType(petType);
				dto.setBoardContent(boardContent);
				dto.setCategoryName(service.findCategoryName(boardType, petAddr, petType));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			ImageService imgservice = ImageService.getService();
			
			dto.setImageName(orgfileName1); // 실제 파일 명
			dto.setImagePath("upload/" + fileName1);
			
			int boardNum=service.insertData(dto);

			dto.setBoardNum(boardNum);
			
			if(dto.getImageName()!=null) {
			int imageNum=imgservice.insertImg(dto);
			dto.setImageNum(imageNum);

			}
	
	//		service.getImg(boardNum);
	//		입력하고 바로 불러오는거 해야함(미리보기)
			
			forward.setForward(false);
			forward.setPath("boarddetail.do?boardNum=" + boardNum);
	
	//		나중에 board.Detail.do에 boardnum 이랑 같이 보내야함
        }
		return forward;
	}

}
