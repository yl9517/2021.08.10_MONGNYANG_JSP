package kr.or.mn.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import kr.or.mn.comm.DBConnection;
import kr.or.mn.dao.BoardDAO;
import kr.or.mn.dto.BoardDTO;

public class BoardService {

private static BoardService instance=new BoardService();
	
	public static BoardService getInstance() {
		return instance;
	}
	private BoardService() {}
	
	public List<BoardDTO> getList() {
		// TODO Auto-generated method stub
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		List<BoardDTO> list=new ArrayList<BoardDTO>();
		
		try {
			conn=dbconn.getConnection();
			BoardDAO dao=BoardDAO.getDAO();
			list=dao.getList(conn);
			
		}catch(SQLException|NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return list;
	}
	public BoardDTO getDetail(int boardnum) {
		// TODO Auto-generated method stub
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		BoardDTO dto=new BoardDTO();
		
		try {
			conn=dbconn.getConnection();
			BoardDAO dao=BoardDAO.getDAO();
			dto=dao.getDetail(conn, boardnum);
			
		}catch(SQLException|NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return dto;
	}
	public void delete(int boardnum) {
		// TODO Auto-generated method stub
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		
		try {
			conn=dbconn.getConnection();
			BoardDAO dao=BoardDAO.getDAO();
			dao.delete(conn, boardnum);
			
		}catch(SQLException|NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
	}
	public int insertData(BoardDTO dto) {
		// TODO Auto-generated method stub
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		int result=0;
		
		try {
			conn=dbconn.getConnection();
			BoardDAO dao=BoardDAO.getDAO();
			result=dao.insert(conn, dto);
			
		}catch(SQLException|NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return result;
	}
	
	
	
}
