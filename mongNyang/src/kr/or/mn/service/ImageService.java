package kr.or.mn.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import kr.or.mn.comm.DBConnection;
import kr.or.mn.dao.ImageDAO;
import kr.or.mn.dto.ImageDTO;

public class ImageService {

	private static ImageService service = new ImageService();
	
	public static ImageService getService() {
		return service;
	}	
	private ImageService() {};
	
	//전체이미지
	public List<ImageDTO> getIetImgList(int num){
		DBConnection dbconn = DBConnection.getDBInstance();
		
		Connection conn = null;
		List<ImageDTO> list = new ArrayList<ImageDTO>();
		try {
			conn = dbconn.getConnection();
			
			//dao연결
			ImageDAO dao = ImageDAO.getDAO();
			
			list = dao.getImgList(conn, num);
			
		}catch(SQLException | NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null)try { conn.close();}catch(SQLException e) {};
		}
		return list;
	}
	
	public ImageDTO getImg(int boardNum) {
		DBConnection dbconn = DBConnection.getDBInstance();
		
		Connection conn = null;
		ImageDTO dto = new ImageDTO();
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
	
	
	
}
