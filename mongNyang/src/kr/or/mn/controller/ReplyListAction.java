package kr.or.mn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.or.mn.dto.ReplyDTO;
import kr.or.mn.service.ReplyService;

/**
 * Servlet implementation class ReplyListAction
 */
@WebServlet("/replylist.mn")
public class ReplyListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doReq(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doReq(request, response);	
	}
	
	private void doReq(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out=response.getWriter();
			int boardNum=Integer.parseInt(request.getParameter("boardNum"));
			ReplyService service=ReplyService.getInstance();
			List<ReplyDTO> list=service.replyList(boardNum);
			
			
			JSONArray arr=new JSONArray();
			
			for(ReplyDTO dto:list)
			{
				JSONObject replyobject=new JSONObject();
				replyobject.put("replyContent", dto.getReplyContent());
				replyobject.put("userId", dto.getUserId());
				replyobject.put("replyDate", dto.getReplyDate());
				replyobject.put("boardNum", dto.getBoardNum());
				replyobject.put("replyNum", dto.getReplyNum());
				//replyobject.put("imageNum", dto.getImageNum());
				arr.add(replyobject);
			}
			out.print(arr);
	}

}
