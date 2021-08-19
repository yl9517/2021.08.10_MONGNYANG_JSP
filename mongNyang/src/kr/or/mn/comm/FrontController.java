package kr.or.mn.comm;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

@WebServlet(urlPatterns = {"*.do"},
			initParams = {@WebInitParam(name="init", value = "WEB-INF/prop.properties")})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FrontController() {
        super();
    }

    private Map<String, Action> hm = Collections.synchronizedMap(new HashMap<String, Action>());
   
    @Override
    public void init(ServletConfig config) throws ServletException {
    	String param = config.getInitParameter("init"); //prop파일경로 가져오기  	
    	String realpath = config.getServletContext().getRealPath(param); //prop파일의 실제 경로
    	
    	Properties prop = new Properties();
    	try {
    		prop.load(new FileReader(realpath));  //prop 파일 읽어옴
    		
    		Iterator ita = prop.keySet().iterator();  //prop파일의 key들 담기
    		
    		while(ita.hasNext()) {
    			String key = (String) ita.next(); // 값이 있을때마다 key로 저장
    			String value = (String) prop.get(key); //key의 value값 저장
    			
    			Class c = Class.forName(value); //value 문자열 값을 객체로 변환
    			Action act = (Action) c.newInstance(); //Action객체로 변환		

    			hm.put(key, act); //hm에 담기   			
    		}
    		  		
    	}catch (Exception e) {
			System.out.println(e);
		}
    	
    
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doReq(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doReq(request,response);
	}
	
	private void doReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		System.out.println(path);
		Action act = hm.get(path); //path경로를 hm(key)에 넣어서  해당 key의 value값 호출
		System.out.println(act);
		Forward forward = act.execute(request, response); 

		if(forward.isForward()) {
			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
			dis.forward(request, response);
		}else {
			response.sendRedirect(forward.getPath());
		}
		
	}


}
