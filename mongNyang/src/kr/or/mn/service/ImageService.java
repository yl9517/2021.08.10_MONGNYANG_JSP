package kr.or.mn.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import kr.or.mn.comm.DBConnection;
import kr.or.mn.dao.ImageDAO;
import kr.or.mn.dto.MainDTO;

public class ImageService {

	private static ImageService service = new ImageService();
	
	public static ImageService getService() {
		return service;
	}	
	private ImageService() {};
	
	//전체이미지
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
	
	public MainDTO getImg(int boardNum) {
		DBConnection dbconn = DBConnection.getDBInstance();
		
		Connection conn = null;
		MainDTO dto=new MainDTO();
		
		try {
			conn = dbconn.getConnection();
			
			//dao연결
			ImageDAO dao = ImageDAO.getDAO();
			
			dto = dao.getImg(conn, boardNum);
			
		}catch(SQLException | NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null)try { conn.close();}catch(SQLException e) {};
		}
		return dto;
	}
	
	//사진 등록
	public MainDTO insertImg(MainDTO dto) {
		DBConnection dbconn = DBConnection.getDBInstance();
		
		Connection conn = null;
		try {
			conn = dbconn.getConnection();
			
			//dao연결
			ImageDAO dao = ImageDAO.getDAO();
			dao.insertImg(conn, dto);
			
		}catch(SQLException | NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null)try { conn.close();}catch(SQLException e) {};
		}
		return dto;
	}
	
	
	
}
