package kr.or.mn.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import kr.or.mn.comm.DBConnection;
import kr.or.mn.dao.UserDAO;
import kr.or.mn.dto.UserDTO;



public class UserService {
	
	// 싱글톤
	private static UserService instance = new UserService();
	public static UserService getInstance() {
		return instance;
	}
	private UserService() {}
	
	
	 
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
	public int insertUser(UserDTO dto) {
		
		DBConnection dbconn = DBConnection.getDBInstance();
		Connection conn = null;
		
		int result = 0;
		try {
			conn = dbconn.getConnection();
			
			UserDAO dao = UserDAO.getDAO();
			result = dao.insertUser(conn, dto);
			
		}catch(SQLException | NamingException e) {
			System.out.println(e);	
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return result;
	}
	
	
	
	// 회원정보 수정
	public void modifyUser(UserDTO dto) {
		
		DBConnection dbconn = DBConnection.getDBInstance();
		Connection conn =null;
		
		try {
			conn = dbconn.getConnection();
			
			UserDAO dao = UserDAO.getDAO();
			dao.modifyUser(conn, dto);
			
		}catch(SQLException | NamingException e) {
			System.out.println("서비스"+e);
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
			
			UserDAO dao = UserDAO.getDAO();
			dao.deleteUser(conn, userId);
			
		}catch(SQLException | NamingException e) {
			System.out.println(e);	
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		
	}
	
	
	
	
	
	// 비밀번호 찾기
	
	// 아이디 중복 확인
	
	// 아이디, 이메일 맞는지 확인
	
	
	
	
	
	
	
	
	
	
}
