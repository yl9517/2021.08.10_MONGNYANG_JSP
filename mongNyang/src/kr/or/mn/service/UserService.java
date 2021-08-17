package kr.or.mn.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import kr.or.mn.comm.DBConnection;
import kr.or.mn.dao.UserDAO;
import kr.or.mn.dto.UserDTO;



public class UserService {
	
	
	private static UserService instance = new UserService();
	public static UserService getInstance() {
		return instance;
	}
	private UserService() {}
	// 싱글톤
	
	
	
	// 회원 개별조회
	public UserDTO selectUser(String UserId){
		
		DBConnection dbconn = DBConnection.getDBInstance();
		Connection conn = null;
		UserDTO dto = new UserDTO();
		
		try {
			conn = dbconn.getConnection();
			UserDAO dao = UserDAO.getDAO();
			dto = dao.selectUser(conn, UserId);
			
			
		}catch(SQLException | NamingException e) {
			System.out.println(e);
			
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return dto;
		
	}
	
	
	// 회원등록
	public void insertUser(UserDTO dto) {
		
		DBConnection dbconn = DBConnection.getDBInstance();
		Connection conn = null;
		
		try {
			conn = dbconn.getConnection();
			conn.setAutoCommit(false);
			
			UserDAO dao = UserDAO.getDAO();
			dao.insertUser(conn, dto);
			
			conn.commit();
		}catch(SQLException | NamingException e) {
			System.out.println(e);	
			try {conn.rollback();}catch(SQLException e2) {}
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		
	}
	
	
	// 회원정보 수정
	public void modifyUser(UserDTO dto) {
		
		DBConnection dbconn = DBConnection.getDBInstance();
		Connection conn =null;
		
		try {
			conn = dbconn.getConnection();
			conn.setAutoCommit(false);
			
			UserDAO dao = UserDAO.getDAO();
			dao.modifyUser(conn, dto);
			
			conn.commit();
		}catch(SQLException | NamingException e) {
			System.out.println(e);
			try {conn.rollback();} catch(SQLException e2) {}
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		
		
	}

	
	
	// 회원정보삭제
	public void deleteUser(String userId) {
		
		DBConnection dbconn = DBConnection.getDBInstance();
		Connection conn = null;
		
		
		try {
			conn = dbconn.getConnection();
			conn.setAutoCommit(false);
			
			UserDAO dao = UserDAO.getDAO();
			dao.deleteUser(conn, userId);
			
			conn.commit();
		}catch(SQLException | NamingException e) {
			System.out.println(e);	
			try {conn.rollback();} catch(SQLException e2) {}
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
