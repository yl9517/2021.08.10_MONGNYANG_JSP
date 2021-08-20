package kr.or.mn.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.mn.comm.Action;
import kr.or.mn.comm.Forward;

/**
 * Servlet implementation class MainActions
 */
@WebServlet("/main.mn")
public class MainAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Forward forward = new Forward();
		forward.setForward(true);
		forward.setPath("/view.jsp?page=main.jsp");
		
		RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
		dis.forward(request, response);

		
		 
	}

}
