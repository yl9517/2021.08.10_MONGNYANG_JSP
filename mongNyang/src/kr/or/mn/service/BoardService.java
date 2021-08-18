package kr.or.mn.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import kr.or.mn.comm.DBConnection;
import kr.or.mn.dao.BoardDAO;
import kr.or.mn.dto.BoardDTO;
import kr.or.mn.dto.CategoryDTO;
import kr.or.mn.dto.MainDTO;

public class BoardService {

private static BoardService instance=new BoardService();
	
	public static BoardService getInstance() {
		return instance;
	}
	private BoardService() {}
	
	public List<MainDTO> getList(String boardType) {
		// TODO Auto-generated method stub
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		List<MainDTO> list=new ArrayList<>();
		
		try {
			conn=dbconn.getConnection();
			BoardDAO dao=BoardDAO.getDAO();
			list=dao.getList(conn, boardType);
			
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
	
	//게시글 삭제
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
	//게시글 등록(게시판, 카테고리, 이미지 등륵)
	public int insertData(String boardType, MainDTO dto) {
		// TODO Auto-generated method stub
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		int result=0;
		String categoryName= null;

		try {
			conn=dbconn.getConnection();
			BoardDAO dao=BoardDAO.getDAO();
			categoryName=dao.findCategoryName(conn, boardType, dto.getPetAddr(), dto.getPetType());
			dto.setCategoryName(categoryName);
			
			result=dao.insertBoard(conn, boardType, dto);
			
			
		}catch(SQLException|NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return result;
	}
	
	// 카테고리이름 찾기 ( -> 게시글 등록에 들어갈 것)
	public String findCategoryName(String boardType, String petAddr, String petType) {
		
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		String categoryName= null;
		try {
			conn=dbconn.getConnection();
			BoardDAO dao=BoardDAO.getDAO();
			
			categoryName=dao.findCategoryName(conn, boardType,petAddr,petType);
		}catch(SQLException|NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return categoryName;
	}
	
	// 카테고리내용 찾기 ( -> 게시글 목록/개별에 들어갈 것 )
	public CategoryDTO findCategoryContent(String categoryName) {
		
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		CategoryDTO categorys = new CategoryDTO();
		try {
			conn=dbconn.getConnection();
			BoardDAO dao=BoardDAO.getDAO();
			
			categorys=dao.findCategoryContent(conn, categoryName);
			
		}catch(SQLException|NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return categorys;
	}
	
	
	
}
