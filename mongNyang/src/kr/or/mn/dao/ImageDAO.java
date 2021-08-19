package kr.or.mn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.mn.dto.BoardDTO;
import kr.or.mn.dto.ImageDTO;
import kr.or.mn.dto.MainDTO;

public class ImageDAO {
	private static ImageDAO dao = new ImageDAO();
	
	public static ImageDAO getDAO() {
		return dao;
	}
	private ImageDAO() {};
	
	//일단 이거 나중에 꺼내기
	//게시글의 전체이미지 가져오기(첫번째이미지는 size=1로 jsp에서 조건설정) 댓글이미지
	public List<ImageDTO> getImgList(Connection conn, int num) { //게시글 번호 혹은 댓글번호
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select                         ");
		sql.append("              imageNum          ");
		sql.append("              imageName         ");
		sql.append("              imagePath         ");
		sql.append("    from      one_image         ");
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
//	//사진번호 가져오기
//	public int getImgNum(Connection conn, String imageName,String imagePath) { 
//		
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select                         ");
//		sql.append("              imageNum          ");
//		sql.append("    from      one_image         ");
//		sql.append("     where   imageName=?  and imagePath =?        ");
//		
//		ResultSet rs = null;
//		int imgNum = 0;
//		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
//			pstmt.setString(1, imageName);
//			pstmt.setString(2, imagePath);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				imgNum = rs.getInt("imageNum");
//			}
//						
//		}catch (SQLException e) {		
//			System.out.println(e);
//		}
//		return imgNum;		
//	}
	
	
	
	//사진 하나만 입력할 수 있다고 했을때 사진받아오기 (게시판 기준) - test
	public ImageDTO getImg(Connection conn, int boardNum) { //글번호 (사진번호?)
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select                         ");
		sql.append("              imageNum          ");
		sql.append("             , imageName         ");
		sql.append("             , imagePath         ");
		sql.append("    from      one_image         ");
		sql.append("     where   boardNum=?         ");
		
		ResultSet rs = null;
		ImageDTO dto=new ImageDTO();
		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
			pstmt.setInt(1, boardNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setImageNum(rs.getInt("imageNum"));
				dto.setImageName(rs.getString("imageName"));
				dto.setImagePath(rs.getString("imagePath"));
			}
						
		}catch (SQLException e) {		
			System.out.println(e);
		}
		return dto;		
	}
	
	//이미지 등록
	public void insertImg(Connection conn, MainDTO dto) {
		StringBuilder sql = new StringBuilder();
		sql.append("  insert into one_image(   ");
		sql.append("  		 imageName         ");
		sql.append("  		, imagePath	       ");
		sql.append("  		, boardNum	       ");
		sql.append("  		, replyNum	 )     ");
		sql.append("   values( ? , ? , 8 , null ) ");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
			pstmt.setString(1, dto.getImageName());
			pstmt.setString(2, dto.getImagePath());
//			pstmt.setInt(3, dto.getBoardNum());
//			pstmt.setInt(4, dto.getReplyNum());
			
			pstmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	//이미지 수정
	public void updateImg(Connection conn, ImageDTO dto) {
		StringBuilder  sql = new StringBuilder();
		sql.append(" update one_image set      ");
		sql.append("      imageName =?         ");
		sql.append("      , imagePath =?       ");
		sql.append("    where  imageNum = ?    ");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, dto.getImageName());
			pstmt.setString(2, dto.getImagePath());
			pstmt.setInt(3, dto.getImageNum());
			
			pstmt.executeUpdate();			
			
		}catch (SQLException e) {
			System.out.println(e);
		}		
	}
	
	//이미지 삭제
	public void deleteImg(Connection conn, int imageNum) {
		StringBuilder  sql = new StringBuilder();
		sql.append(" delete from one_image      ");
		sql.append("  where imageNum = ?        ");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, imageNum);
			
			pstmt.executeUpdate();			
			
		}catch (SQLException e) {
			System.out.println(e);
		}	
	}
	
	
}
