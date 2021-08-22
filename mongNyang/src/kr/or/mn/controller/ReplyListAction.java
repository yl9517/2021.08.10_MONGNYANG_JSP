package kr.or.mn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.or.mn.dto.ImageDTO;
import kr.or.mn.dto.ReplyDTO;
import kr.or.mn.service.ImageService;
import kr.or.mn.service.ReplyService;

/**
 * Servlet implementation class ReplyListAction
 */
@WebServlet("/replylist.mn")
public class ReplyListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReplyListAction() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doReq(request, response);	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doReq(request, response);	
	}
	
	private void doReq(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("application/json;charset=utf-8");

		PrintWriter out=response.getWriter();
		int boardNum=Integer.parseInt(request.getParameter("boardNum"));
		String master = request.getParameter("master");
	
		//댓글
		ReplyService service=ReplyService.getInstance();
		List<ReplyDTO> list=service.replyList(boardNum);
		
		//이미지
		ImageService imgService = ImageService.getService();			
		List<ImageDTO> imgList = new ArrayList<ImageDTO>();  			
		for(int i=0; i<list.size(); i++) {
			ImageDTO imgdto = imgService.getImg(list.get(i).getReplyNum(), 1); //numType = 0이면 글, 1이면 댓글
			imgList.add(imgdto);
		}
		
		
		//JSON
		JSONArray arr=new JSONArray();
		
		int index = 0;
		for(ReplyDTO dto:list)
		{
			JSONObject replyobject=new JSONObject();
			replyobject.put("replyContent", dto.getReplyContent());
			replyobject.put("userId", dto.getUserId());
			replyobject.put("replyDate", dto.getReplyDate());
			replyobject.put("boardNum", dto.getBoardNum());
			replyobject.put("replyNum", dto.getReplyNum());
			replyobject.put("imgName", imgList.get(index).getImageName()); 
			replyobject.put("imgPath", imgList.get(index).getImagePath()); 
			replyobject.put("imgNum", imgList.get(index).getImageNum()); 
			replyobject.put("master", master);

			arr.add(replyobject);
			index++;
		}

		out.print(arr);
	}

}
