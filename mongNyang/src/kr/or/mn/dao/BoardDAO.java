package kr.or.mn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.mn.dto.BoardDTO;
import kr.or.mn.dto.ReplyDTO;

public class BoardDAO {

	public List<BoardDTO> getList(Connection conn) {
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
		
		List<BoardDTO> list=new ArrayList<BoardDTO>();
		try(
				PreparedStatement pstmt=conn.prepareStatement(sql.toString());
				ResultSet rs=pstmt.executeQuery();
				){
			while(rs.next()) {
				BoardDTO dto=new BoardDTO();
				dto.setBoardNum(rs.getInt("boardNum"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setBoardContent(rs.getString("boardContent"));
				dto.setUserId(rs.getString("userId"));
				dto.setBoardDate(rs.getString("boardDate"));
				dto.setCategoryName(rs.getString("categoryName"));
				dto.setImageNum(rs.getInt("imageNum"));
				dto.setBoardState(rs.getBoolean("boardState"));
				dto.setBoardReadNo(rs.getInt("boardReadNo"));
				
				list.add(dto);
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

	public int insertReply(Connection conn, ReplyDTO dto) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("  insert into ond_reply  (                  ");
		sql.append("                           replyNum         ");
		sql.append("                          ,boardNum         ");
		sql.append("                          ,replyDate       ");
		sql.append("                          ,userId           ");
		sql.append("                          ,replyContent     ");
		sql.append("                          ,imageNum         ");
		sql.append("                          ,alertCheck)      ");
		sql.append("  values(boardseq.nextval, ?,?,?,?,?,?)     ");
		
		int result=0;
		try(
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			){
				pstmt.setInt(1, dto.getBoardNum());
				pstmt.setString(2, dto.getReplyDate());
				pstmt.setString(3, dto.getUserId());
				pstmt.setString(4, dto.getReplyContent());
				pstmt.setInt(5, dto.getImageNum());
				pstmt.setBoolean(6, false);
				
				pstmt.executeUpdate();
				
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		return result;
	}
	
	public List<ReplyDTO> replyDetail(Connection conn, int boardNum){
		
		StringBuilder sql=new StringBuilder();
		sql.append("  select                      ");
		sql.append("                replyNum      ");
		sql.append("               ,replyContent  ");
		sql.append("               ,userId        ");
		sql.append("               ,replyDate     ");
		sql.append("               ,boardNum      ");
		sql.append("               ,imageNum      ");
		sql.append("  from     one_reply          ");
		sql.append("  where                       ");
		sql.append("               boardNum=?     ");
		
		ResultSet rs=null;
		List<ReplyDTO> list=new ArrayList<ReplyDTO>();
		try(
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			){
			pstmt.setInt(1, boardNum);
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				ReplyDTO dto=new ReplyDTO();
				dto.setReplyNum(rs.getInt("replyNum"));
				dto.setReplyContent(rs.getString("replyContent"));
				dto.setUserId(rs.getString("userId"));
				dto.setReplyDate(rs.getString("replyDate"));
				dto.setBoardNum(rs.getInt("boardNum"));
				dto.setImageNum(rs.getInt("imageNum"));
				list.add(dto);
			}
			
			
		}catch(SQLException e)
		{
			System.out.println(e);
		}finally {
			if(conn!=null) try {rs.close();} catch(SQLException e) {}
		}
		return list;
		
	}
	
	public void replyDelete(Connection conn, int replyNum, int boardNum) {
		
		StringBuilder sql=new StringBuilder();
		sql.append("  delete from one_reply          ");
		sql.append("  where        replyNum=?        ");
		
		try(
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			){
			pstmt.setInt(1, replyNum);
			pstmt.executeUpdate();
			
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		
	}
	
	


}
