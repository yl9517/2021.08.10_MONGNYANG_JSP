package kr.or.mn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.mn.dto.ReplyDTO;

public class ReplyDAO {
	
public List<ReplyDTO> getReplyList(Connection conn){
		
		StringBuilder sql=new StringBuilder();
		sql.append("  select                   ");
		sql.append("           replyNum        ");
		sql.append("          ,boardNum        ");
		sql.append("          ,replyDate       ");
		sql.append("          ,userId          ");
		sql.append("          ,replyContent    ");
		sql.append("          ,imageNum        ");
		sql.append("          ,alertCheck      ");
		sql.append("  from  one_reply          ");
		
		List<ReplyDTO> list=new  ArrayList<ReplyDTO>();
		try(
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			ResultSet rs=pstmt.executeQuery();
			){
			
			
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		return list;
	}
	
	

	public int insertReply(Connection conn, ReplyDTO dto) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("  insert into one_reply  (                  ");
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