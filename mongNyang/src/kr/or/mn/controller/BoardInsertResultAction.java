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
import kr.or.mn.dto.ImageDTO;
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

			String imagePath = "";
			String imageName = "";
	
			String uploadPath = request.getRealPath("upload");

			String boardType = "";
			BoardService service = BoardService.getInstance();
	
			MainDTO dto = new MainDTO();
			try {
				MultipartRequest multi = new MultipartRequest( // MultipartRequest 인스턴스 생성(cos.jar의 라이브러리)
						request, uploadPath, // 파일을 저장할 디렉토리 지정
						10 * 1024 * 1024, // 첨부파일 최대 용량 설정(bite)
						"utf-8", // 인코딩 방식 지정
						new DefaultFileRenamePolicy()); // 중복 파일 처리(동일한 파일명이 업로드되면 뒤에 숫자 등을 붙여 중복 회피)
	
				imagePath = multi.getFilesystemName("file1"); // name=file1의 업로드된 시스템 파일명을 구함(중복된 파일이 있으면, 중복 처리 후 파일 이름)
				imageName = multi.getOriginalFileName("file1"); // name=file1의 업로드된 원본파일 이름을 구함(중복 처리 전 이름)
	
				String boardTitle = multi.getParameter("boardTitle");
				String boardContent = multi.getParameter("boardContent");
				boardType = multi.getParameter("boardType");
				String petAddr = multi.getParameter("petAddr");
				String petType = multi.getParameter("petType");
				
				dto.setBoardTitle(boardTitle);
				dto.setBoardContent(boardContent);
				dto.setUserId(id);
				dto.setCategoryName(service.findCategoryName(boardType, petAddr, petType));
				dto.setBoardType(boardType);
				dto.setPetAddr(petAddr);
				dto.setPetType(petType);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			int boardNum=service.insertData(dto); //게시판 번호
			dto.setBoardNum(boardNum);
			
			
			ImageService imgservice = ImageService.getService();
			
			if(imageName!=null && !imageName.equals("")) { //사진 넣었을 경우
				ImageDTO imgdto = new ImageDTO();
				imgdto.setImageName(imageName);
				imgdto.setImagePath("upload/"+imagePath);
				imgdto.setBoardNum(boardNum);		
				
				int imageNum=imgservice.insertImg(imgdto); //이미지삽입 및 번호 가져오기
				
				dto.setImageNum(imageNum);
				dto.setImageName(imgdto.getImageName());
				dto.setImagePath(imgdto.getImagePath());

			}
	
	//		service.getImg(boardNum);
	//		입력하고 바로 불러오는거 해야함(미리보기)
			
			forward.setForward(false);
			forward.setPath("boarddetail.do?boardNum=" + boardNum);

        }
		return forward;
	}

}
