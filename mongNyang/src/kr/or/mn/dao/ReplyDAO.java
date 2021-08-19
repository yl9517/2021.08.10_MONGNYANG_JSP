package kr.or.mn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.mn.dto.ReplyDTO;

public class ReplyDAO {
	
	public int insertReply(Connection conn, ReplyDTO dto) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("  insert into one_reply  (                  ");
		sql.append("                          replyNum          ");
		sql.append("                          ,boardNum         ");
		sql.append("                          ,replyDate        ");
		sql.append("                          ,userId           ");
		sql.append("                          ,replyContent     ");
		sql.append("                          ,imageNum         ");
		sql.append("                          ,alertCheck)      ");
		sql.append("  values(?,?,?,1,?,2,0)                     ");
		
		int result=0;
		try(
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			){
				pstmt.setInt(1, dto.getReplyNum());
				pstmt.setInt(2, dto.getBoardNum());
				pstmt.setString(3, "2021-08-18");
				pstmt.setString(4, dto.getReplyContent());
				
				result = pstmt.executeUpdate();
				
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		return result;
	}
	
	public List<ReplyDTO> replyList(Connection conn, int boardNum, int replyNum){
		
		StringBuilder sql=new StringBuilder();
		sql.append("  select                      ");
		sql.append("                replyNum  ");
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
	
	public void replyModify(Connection conn, ReplyDTO dto) {
		
		StringBuilder sql=new StringBuilder();
		sql.append("  update       one_reply      ");
		sql.append("  set                         ");
		sql.append("               replyContent=? ");
		sql.append("  where                       ");
		sql.append("               replyNum=?     ");
		
		try(
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
				
			){
			pstmt.setString(1, dto.getReplyContent());
			pstmt.setInt(2, dto.getReplyNum());
			pstmt.executeUpdate();
			
		}catch(SQLException e)
		{
			System.out.println(e);
		}
	}


}
