package kr.or.mn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import kr.or.mn.dto.ImageDTO;

public class ImageDAO {
	private static ImageDAO dao = new ImageDAO();
	
	public static ImageDAO getDAO() {
		return dao;
	}
	private ImageDAO() {};
	
	public List<ImageDTO> getImgList(Connection conn, int num) { //게시글 번호 혹은 댓글번호
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select                         ");
		sql.append("              imageNum          ");
		sql.append("              imagePath         ");
		sql.append("              imageName         ");
		//boardNum이면
		sql.append("     where   boardNum=?         ");
		//replyNum이면
		sql.append("     where   replyNum=?         ");
		
		List<ImageDTO> list = new ArrayList<ImageDTO>();
		ResultSet rs = null;
		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
			
			
		}catch (SQLException e) {		
			System.out.println(e);
		}
		return list;
		
	}
	
	
}
