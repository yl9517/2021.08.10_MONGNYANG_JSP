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
import kr.or.mn.dto.PageDTO;

public class BoardService {

private static BoardService instance=new BoardService();
	
	public static BoardService getInstance() {
		return instance;
	}
	private BoardService() {}
	
	//게시글 리스트 받기
	public List<MainDTO> getList(String boardType,String petAddr,PageDTO pdto) {
		// TODO Auto-generated method stub
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		List<MainDTO> list=new ArrayList<>();
		
		try {
			conn=dbconn.getConnection();
			BoardDAO dao=BoardDAO.getDAO();
			list=dao.getList(conn, boardType,petAddr,pdto);
			
		}catch(SQLException|NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return list;
	}
	
	//디테일 받아오기
	public MainDTO getDetail(int boardNum) {
		// TODO Auto-generated method stub
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		MainDTO dto=new MainDTO();
		
		try {
			conn=dbconn.getConnection();
			conn.setAutoCommit(false);
			
			BoardDAO dao=BoardDAO.getDAO();
			dto=dao.getDetail(conn, boardNum); //dto 받아오기 (디테일)
			dao.updateReadNo(conn, boardNum); //조회수 증가

			conn.commit();
		}catch(SQLException|NamingException e) {
			System.out.println(e);
			try {conn.rollback();}catch (SQLException e2) {}
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
	//게시글 등록(게시판, 카테고리, 이미지 등륵)후 바로 게시판번호 받아옴
	public int insertData(MainDTO dto) {
		// TODO Auto-generated method stub
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		
		int boardNum=0;
		try {
			conn=dbconn.getConnection();
			BoardDAO dao=BoardDAO.getDAO();
			boardNum=dao.insert(conn, dto);
			
		}catch(SQLException|NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return boardNum;
	}
	
	//게시글 수정
		public void modify(MainDTO dto) {
			// TODO Auto-generated method stub
			DBConnection dbconn=DBConnection.getDBInstance();
			Connection conn=null;

			try {
				conn=dbconn.getConnection();
				BoardDAO dao=BoardDAO.getDAO();
				dao.modify(conn, dto);
//				System.out.println(dto.getCategoryName()); 안됨
			}catch(SQLException|NamingException e) {
				System.out.println(e);
			}finally {
				if(conn!=null) try {conn.close();} catch(SQLException e) {}
			}			
		}
	
  //해결상태 변경		
	public void stateModify(int boardNum, boolean boardState) {
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;

		try {
			conn=dbconn.getConnection();
			BoardDAO dao=BoardDAO.getDAO();
			dao.stateModify(conn, boardNum,boardState);
			
		}catch(SQLException|NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}			
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
	
	//내 게시글 찾기
	public List<BoardDTO> findMyWrite(String userId, PageDTO pdto){
		DBConnection dbconn = DBConnection.getDBInstance();
		
		Connection conn = null;
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			conn = dbconn.getConnection();
			
			BoardDAO dao = BoardDAO.getDAO();
			list = dao.findMyWrite(conn, userId, pdto);
			
		}catch (SQLException | NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return list;		
	}
	//자료 전체 갯수 받아오기
	public int getTotalCount(String search, String searchtxt) {
		// TODO Auto-generated method stub
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		
		int totalcount=0;
		try {
			conn=dbconn.getConnection();
			BoardDAO dao=BoardDAO.getDAO();
			
			totalcount=dao.getTotalCount(conn, search, searchtxt);
			
		}catch(SQLException|NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return totalcount;
	}
	
	//해당 게시글의 댓글 수 가져오기
	public int replyCount(int boardNum) {
		// TODO Auto-generated method stub
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		
		int replyCount=0;
		try {
			conn=dbconn.getConnection();
			BoardDAO dao=BoardDAO.getDAO();
			
			replyCount=dao.replyCount(conn, boardNum);
			
		}catch(SQLException|NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return replyCount;
	}
	
	//게시물자료 전체 갯수 받아오기(유저기준으로)
	
	public int getUserBoardTotalCount(String userId) {
		// TODO Auto-generated method stub
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		
		int totalcount=0;
		try {
			conn=dbconn.getConnection();
			BoardDAO dao=BoardDAO.getDAO();
			
			totalcount=dao.getUserBoardTotalCount(conn, userId);
			
		}catch(SQLException|NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return totalcount;
	}
	
	//메인에 쓸 게시글 자료 가져오기 (오늘 등록 된 글, 총 해결된 글, 총 글수)
	public int[] getData(){
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		
		int[] data = new int[3];
		try {
			conn=dbconn.getConnection();
			BoardDAO dao=BoardDAO.getDAO();
			
			data[0] = dao.getTodayData(conn); //접수 된 글
			data[1] = dao.getFinData(conn);   //총 해결된 글
			data[2] = dao.getTotalCount(conn, "", ""); //총 글수
		
			
		}catch(SQLException|NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return data;
	}
	
}
