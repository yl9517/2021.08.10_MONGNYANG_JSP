package kr.or.mn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.jni.User;


import kr.or.mn.dto.UserDTO;

public class UserDAO {
	
	private static UserDAO dao = new UserDAO();
	public static UserDAO getDAO() {
		return dao;
	}
	private UserDAO() {}
	// 싱글톤
	

	
	// 회원등록
	public int insertUser(Connection conn, UserDTO dto) {
		 
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into one_user (  				");
		sql.append(" 						userId			");
		sql.append(" 						, userPwd		");
		sql.append(" 						, userPhone		");
		sql.append(" 						, userEmail		");
		sql.append(" 						, userAddr		");
		sql.append(" 						)				");
		sql.append(" values( ?, ?, ?, ?, ? )				");
		
		
		int result = 0;
		try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getUserPwd());
			pstmt.setString(3, dto.getUserPhone());
			pstmt.setString(4, dto.getUserEmail());
			pstmt.setString(5, dto.getUserAddr());
			result = pstmt.executeUpdate();	
			
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return result;
	}
		
	
	// 회원 개별조회(+아이디 중복체크)
	public UserDTO selectUser(Connection conn, String userId) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select 				");
		sql.append("		userId			");
		sql.append("		, userPhone		");
		sql.append("		, userEmail		");
		sql.append("		, userAddr		");
		sql.append(" from one_user 			");
		sql.append(" where userId = ?		");
		
		
		UserDTO dto = new UserDTO();
		ResultSet rs = null;
		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setUserId(rs.getString("userId"));
				dto.setUserPhone(rs.getString("userPhone"));
				dto.setUserEmail(rs.getString("userEmail"));
				dto.setUserAddr(rs.getString("userAddr"));
			}	
			
		}catch(SQLException e) {
			System.out.println(e);
			
		}finally {
			if(rs!=null) try {rs.close();} catch(SQLException e){}
		}
			
		return dto;
	}
	
	
	
	// 회원정보 수정
	public void modifyUser(Connection conn, UserDTO dto) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" update one_user		");
		sql.append(" set					");
		sql.append("	userPwd = ?			");
		sql.append("	, userPhone = ?		");
		sql.append("	, userEmail = ?		");
		sql.append("	, userAddr = ?		");
		sql.append(" where userId = ?		");
		
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, dto.getUserPwd());
			pstmt.setString(2, dto.getUserPhone());
			pstmt.setString(3, dto.getUserEmail());
			pstmt.setString(4, dto.getUserAddr());
			pstmt.setString(5, dto.getUserId());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	// 회원정보삭제
	public int deleteUser(Connection conn, String userId) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from one_user		");
		sql.append(" where userId = ?			");
			
		int result = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();		
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		return result;
	}
	
	
	//로그인 시도
	public int tryLogin(Connection conn, String userId, String userPwd) {
		StringBuilder sql = new StringBuilder();
		sql.append("  select  userId   ");
		sql.append("  from one_user    ");
		sql.append("  where userId=?   ");
		sql.append("  and  userPwd=?   ");
		
		ResultSet rs = null;
		int result = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result =1;
			}else {
				result =2;
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			if(rs!=null) try {rs.close();} catch(SQLException e){}
		}
		return result;
	}
	
	
	// 비밀번호 찾기(아이디, 이메일 맞는지 확인)
	public String searchPwd(Connection conn, String userId, String userEmail) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select	userPwd				");
		sql.append(" from one_user				");
		sql.append(" where userId = ?			");
		sql.append("		and userEmail = ?	");
		
		ResultSet rs = null;
		String findPwd = "";
		try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userEmail);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				findPwd = rs.getString("userPwd");
			}
			
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			if(rs!=null) try {rs.close();} catch(SQLException e){}
		}
		return findPwd;
	}
	
	//내 게시글 조회는 boardDAO에
	
	
	
	
}
