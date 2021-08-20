package kr.or.mn.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;
import kr.or.mn.dto.ImageDTO;
import kr.or.mn.dto.MainDTO;
import kr.or.mn.service.ImageService;

public class ImageInsertAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String imagePath = "";
		String imageName = "";

		String uploadPath = request.getRealPath("upload");

		int size = 10 * 1024 * 1024;

		try {
			MultipartRequest multi = new MultipartRequest( // MultipartRequest 인스턴스 생성(cos.jar의 라이브러리)
					request, uploadPath, // 파일을 저장할 디렉토리 지정
					10 * 1024 * 1024, // 첨부파일 최대 용량 설정(bite)
					"utf-8", // 인코딩 방식 지정
					new DefaultFileRenamePolicy()); // 중복 파일 처리(동일한 파일명이 업로드되면 뒤에 숫자 등을 붙여 중복 회피)

			imagePath = multi.getFilesystemName("file1"); // name=file1의 업로드된 시스템 파일명을 구함(중복된 파일이 있으면, 중복 처리 후 파일 이름)
			imageName = multi.getOriginalFileName("file1"); // name=file1의 업로드된 원본파일 이름을 구함(중복 처리 전 이름)

		} catch (Exception e) {
			e.printStackTrace();
		}

		ImageService service = ImageService.getService();

		ImageDTO dto = new ImageDTO();
		dto.setImageName(imageName); // 실제 파일 명
		dto.setImagePath("upload/" + imagePath);
		//dto.setBoardNum(boardNum);
		
		service.insertImg(dto);

		Forward forward = new Forward();
		forward.setForward(false);
		forward.setPath("imageview.do");

		return forward;
	}
}
