package kr.or.mn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.mn.dto.BoardDTO;
import kr.or.mn.dto.CategoryDTO;

public class BoardDAO {
	private static BoardDAO dao=new BoardDAO();
	public static BoardDAO getDAO() {
		return dao;
	}
	private BoardDAO() {}
	
	public List<BoardDTO> getList(Connection conn, String boardType) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("  select							");
		sql.append("		boardNum					");
		sql.append("		, boardTitle				");
		sql.append("		, boardContent				");
		sql.append("		, userId					");
		sql.append("		, boardDate					");
		sql.append("		, petAddr					");
		sql.append("		, petType					");
		sql.append("		, imageNum					");
		sql.append("		, boardState				");
		sql.append("		, boardReadNo				");
		sql.append("  from one_board b					");
		sql.append("  inner join one_category c			");
		sql.append("  on b.categoryName=c.categoryName	");
		sql.append("  where c.boardType=?				");
		
		List<BoardDTO> list=new ArrayList<BoardDTO>();
		List<CategoryDTO> clist=new ArrayList<CategoryDTO>();
		try(
				PreparedStatement pstmt=conn.prepareStatement(sql.toString());
				ResultSet rs=pstmt.executeQuery();
				){
			pstmt.setString(1, boardType);
			
			while(rs.next()) {
				BoardDTO dto=new BoardDTO();
				CategoryDTO cdto=new CategoryDTO();
				dto.setBoardNum(rs.getInt("boardNum"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setBoardContent(rs.getString("boardContent"));
				dto.setUserId(rs.getString("userId"));
				dto.setBoardDate(rs.getString("boardDate"));
//				dto.setCategoryName(rs.getString("categoryName"));
				cdto.setBoardType(boardType);
				cdto.setPetAddr(rs.getString("petAddr"));
				cdto.setPetType(rs.getString("petType"));
				dto.setImageNum(rs.getInt("imageNum"));
				dto.setBoardState(rs.getBoolean("boardState"));
				dto.setBoardReadNo(rs.getInt("boardReadNo"));
				
				list.add(dto);
				clist.add(cdto);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return list;
	}

	public BoardDTO getDetail(Connection conn, int boardnum) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("  select				");
		sql.append("		boardNum		");
		sql.append("		, boardTitle	");
		sql.append("		, boardContent	");
		sql.append("		, userId		");
		sql.append("		, boardDate		");
		sql.append("		, categoryName	");
		sql.append("		, imageNum		");
		sql.append("		, boardState	");
		sql.append("		, boardReadNo	");
		sql.append("  from one_board		");
		sql.append("  where boardNum=?		");

		ResultSet rs=null;
		BoardDTO dto=new BoardDTO();
		try(
				PreparedStatement pstmt=conn.prepareStatement(sql.toString());
				){
			pstmt.setInt(1, boardnum);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setBoardNum(rs.getInt("boardNum"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setBoardContent(rs.getString("boardContent"));
				dto.setUserId(rs.getString("userId"));
				dto.setBoardDate(rs.getString("boardDate"));
				dto.setCategoryName(rs.getString("categoryName"));
				dto.setImageNum(rs.getInt("imageNum"));
				dto.setBoardState(rs.getBoolean("boardState"));
				dto.setBoardReadNo(rs.getInt("boardReadNo"));
			}
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
		}
		return dto;
	}

	public void delete(Connection conn, int boardnum) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("  delete				");
		sql.append("  from one_board		");
		sql.append("  where boardNum=?		");

		try(
				PreparedStatement pstmt=conn.prepareStatement(sql.toString());
				){
			pstmt.setInt(1, boardnum);
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println(e);
		}
	}

	public int insert(Connection conn, BoardDTO dto) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("  insert into one_board	(					");
		sql.append("						boardNum			");
		sql.append("						, boardTitle		");
		sql.append("						, boardContent		");
		sql.append("						, userId			");
		sql.append("						, boardDate			");
		sql.append("						, categoryName		");
		sql.append("						, imageNum			");
		sql.append("						, boardState		");
		sql.append("						, boardReadNo )		");
		sql.append("  values(boardseq.nextval,?,?,?,?,?,?,?,0	");
		
		int result=0;
		try(
				PreparedStatement pstmt=conn.prepareStatement(sql.toString());
				){
			pstmt.setString(1, dto.getBoardTitle());
			pstmt.setString(2, dto.getBoardContent());
			pstmt.setString(3, dto.getUserId());
			//아이디 어디서 받아옴?
			pstmt.setString(4, dto.getBoardDate());
			pstmt.setString(5, dto.getCategoryName());
			//카테고리 나눠야함
			pstmt.setInt(6, dto.getImageNum());
			//이미지도 고민해야함
			pstmt.setBoolean(7, false);
			
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		return result;
	}
	
	
}
