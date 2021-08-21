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
import kr.or.mn.dto.ReplyDTO;
import kr.or.mn.service.ImageService;
import kr.or.mn.service.ReplyService;

public class ReplyInsertResultAction implements Action {

	@Override
	public Forward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	
		Forward forward = new Forward();

		//세션으로 아이디값 받기 
        String userId = (String) request.getSession().getAttribute("userId");
        
        
        //if id가 null이면 로그인페이지로
        if(userId==null) {
            forward.setForward(false);
            forward.setPath("userlogin.do");
        }
        else {
        	//이미지 받아오기
			String imagePath = "";
			String imageName = "";
			
			String uploadPath = request.getRealPath("upload");
			
			int boardNum = 1;
			String replyContent = "";
			
			try {
				MultipartRequest multi = new MultipartRequest(request, uploadPath, 10 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());
				
				imagePath = multi.getFilesystemName("replyfile"); 
				imageName = multi.getOriginalFileName("replyfile"); 
				
				// 게시글번호, 내용받기
		        String bn= multi.getParameter("reBoardNum");
		        if(bn!=null) {
		        	boardNum = Integer.parseInt(bn);
		        }
				 replyContent=multi.getParameter("replyContent");				
	
			}catch (Exception e) {
				System.out.println(e);
			}
	
			//서비스 접근
			ReplyDTO dto = new ReplyDTO();
			ReplyService service=ReplyService.getInstance();
			
			dto.setBoardNum(boardNum);
			dto.setUserId(userId);
			dto.setReplyContent(replyContent);
			 
			int replyNum = service.insertReply(dto); //댓글번호 받아오기
			//이미지 처리
			ImageService imgservice = ImageService.getService();
			if(imageName!=null && !imageName.equals("")) { //사진 넣었을 경우
				ImageDTO imgdto = new ImageDTO();
				imgdto.setImageName(imageName);
				imgdto.setImagePath("upload/"+imagePath);
				imgdto.setReplyNum(replyNum);	
				
				imgservice.insertImg(imgdto); //이미지 삽입 (번호 안가져옴)
				
			}
			
			
			forward.setForward(false);
			forward.setPath("boarddetail.do?boardNum="+boardNum);
        }
		return forward;
		
		
	}

}
