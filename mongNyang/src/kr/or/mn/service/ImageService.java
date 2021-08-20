package kr.or.mn.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import kr.or.mn.comm.DBConnection;
import kr.or.mn.dao.ImageDAO;
import kr.or.mn.dto.ImageDTO;
import kr.or.mn.dto.MainDTO;

public class ImageService {

	private static ImageService service = new ImageService();

	public static ImageService getService() {
		return service;
	}

	private ImageService() {
	};

	// 전체이미지
//	public List<MainDTO> getIetImgList(int num){
//		DBConnection dbconn = DBConnection.getDBInstance();
//		
//		Connection conn = null;
//		List<MainDTO> list = new ArrayList<MainDTO>();
//		try {
//			conn = dbconn.getConnection();
//			
//			//dao연결
//			ImageDAO dao = ImageDAO.getDAO();
//			
//			list = dao.getImgList(conn, num);
//			
//		}catch(SQLException | NamingException e) {
//			System.out.println(e);
//		}finally {
//			if(conn!=null)try { conn.close();}catch(SQLException e) {};
//		}
//		return list;
//	}

	public ImageDTO getImg(int boardNum) {
		DBConnection dbconn = DBConnection.getDBInstance();

		Connection conn = null;
		ImageDTO dto = new ImageDTO();

		try {
			conn = dbconn.getConnection();

			// dao연결
			ImageDAO dao = ImageDAO.getDAO();
			dto = dao.getImg(conn, boardNum);

		} catch (SQLException | NamingException e) {
			System.out.println(e);
		} finally {	
			if (conn != null) try {conn.close();} catch (SQLException e) {};
		}
		return dto;
	}

//	public int getImgNum(int boardNum) { //이미지 넘버
//		DBConnection dbconn = DBConnection.getDBInstance();
//		
//		Connection conn = null;
//		MainDTO dto=new MainDTO();
//		
//		try {
//			conn = dbconn.getConnection();
//			
//			//dao연결
//			ImageDAO dao = ImageDAO.getDAO();
//			
//			dto = dao.getImg(conn, boardNum);
//			
//		}catch(SQLException | NamingException e) {
//			System.out.println(e);
//		}finally {
//			if(conn!=null)try { conn.close();}catch(SQLException e) {};
//		}
//		return dto;
//	}

	// 사진 등록
	public int insertImg(MainDTO dto) {
		DBConnection dbconn = DBConnection.getDBInstance();

		Connection conn = null;
		int imageNum = 0;
		try {
			conn = dbconn.getConnection();

			// dao연결
			ImageDAO dao = ImageDAO.getDAO();
			imageNum = dao.insertImg(conn, dto);

		} catch (SQLException | NamingException e) {
			System.out.println(e);
		} finally {	
			if (conn != null) try {conn.close();} catch (SQLException e) {};
		}
		return imageNum;
	}

	public void modify(MainDTO dto) {
		// TODO Auto-generated method stub
		DBConnection dbconn = DBConnection.getDBInstance();

		Connection conn = null;
		try {
			conn = dbconn.getConnection();

			// dao연결
			ImageDAO dao = ImageDAO.getDAO();
			dao.updateImg(conn, dto);

		} catch (SQLException | NamingException e) {
			System.out.println(e);
		} finally {	
			if (conn != null) try {conn.close();} catch (SQLException e) {};
		}
	}

	public void delete(MainDTO dto) {
		// TODO Auto-generated method stub
		DBConnection dbconn = DBConnection.getDBInstance();

		Connection conn = null;
		try {
			conn = dbconn.getConnection();

			// dao연결
			ImageDAO dao = ImageDAO.getDAO();
			dao.deleteImg(conn, dto);

		} catch (SQLException | NamingException e) {
			System.out.println(e);
		} finally {	
			if (conn != null) try {conn.close();} catch (SQLException e) {};
		}
	}

}
