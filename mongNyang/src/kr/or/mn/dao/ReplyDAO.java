package kr.or.mn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.mn.dto.AlertDTO;
import kr.or.mn.dto.ReplyDTO;

public class ReplyDAO {
	private static ReplyDAO dao=new ReplyDAO();
	public static ReplyDAO getDAO() {
		return dao;
	}
	private ReplyDAO() {}
	
	public int insertReply(Connection conn, ReplyDTO dto) {	
		
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("  insert into one_reply  (                  ");
		sql.append("                          boardNum         ");
		sql.append("                          ,replyDate        ");
		sql.append("                          ,userId           ");
		sql.append("                          ,replyContent     ");
		sql.append("                          ,imageNum         ");
		sql.append("                          ,alertCheck)      ");
		sql.append("  values(?,now(),?,?,2,0)                     ");
		
		int result=0;
		try(
			PreparedStatement pstmt=conn.prepareStatement(sql.toString());
			){
				pstmt.setInt(1, dto.getBoardNum());
				pstmt.setString(2, dto.getUserId());
				pstmt.setString(3, dto.getReplyContent());
		//   	pstmt.setString(4, dto.getReplyContent()); //이미지넘버 받아오기
				
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
		sql.append("                replyNum  	  ");
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
	
	
	
	// 댓글 수정
	public void replyModify(Connection conn, ReplyDTO dto) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" update one_reply			");
		sql.append(" set						");
		sql.append("	replyContent = ?		");
		sql.append(" where replyNum = ?			");
		
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, dto.getReplyContent());
			pstmt.setInt(2, dto.getReplyNum());
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println(e);
		}	
		
	}
	
	
	
	//내 게시글에 다른사람의 댓글이 달렸다면(상태 0 혹은 1인것만 )
	public List<AlertDTO> myAlert(Connection conn, String userId){
		StringBuilder sql = new StringBuilder();
		sql.append(" select   b.boardNum        ");
		sql.append(" 		  ,boardTitle       ");
		sql.append(" 		  ,replyNum         ");
		sql.append(" 		  ,alertCheck       ");
		sql.append(" 		  ,replyDate        ");
		sql.append("  from one_board as b       ");
		sql.append(" inner join one_reply as r  ");
		sql.append(" on b.boardNum = r.boardNum ");
		sql.append("    where b.userId = ?      "); //본인의 게시글이고
		sql.append("    and not r.userId = ?    "); //본인의 댓글이 아니며
		sql.append("    and alertCheck in(0,1)  "); //상태가 0 혹은 1
		sql.append("    order by replyDate DESC "); //댓글 최신순으로 정렬
		
		ResultSet rs = null;
		List<AlertDTO> list = new ArrayList<AlertDTO>();
		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				AlertDTO dto = new AlertDTO();
				dto.setBoardNum(rs.getInt("boardNum"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setReplyNum(rs.getInt("replyNum"));
				dto.setAlertCheck(rs.getInt("alertCheck"));
				dto.setReplyDate(rs.getString("replyDate"));
				
				list.add(dto);
			}
		}catch (SQLException e) {
			System.out.println(e);
		}finally {
			if(rs!=null) try {rs.close();} catch(SQLException e){}
		}			
		return list;			
	}
	
	//내 게시글에 달린 댓글 상태변경 ( 링크누르면 1, x누르면 2)
	public void myAlertUpdate(Connection conn,int replyNum, int changeAlert) {
		StringBuilder sql = new StringBuilder();
		sql.append("	 update	 one_reply	 ");
		sql.append("     set alertCheck = ?  ");
		sql.append("     where replyNum = ?  ");
		
		System.out.println("dao에서 변경할 alert번호 확인"+changeAlert);
		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
			pstmt.setInt(1, changeAlert);
			pstmt.setInt(2, replyNum);
			
			pstmt.executeUpdate();
			System.out.println("변경 완료");
			
		}catch (SQLException e) {
			System.out.println(e);
		}
		
	}
	
	//내 댓글 받아오기 (유저 기준으로 모든 댓글리스트)
	   
	   public List<ReplyDTO> mypageReply(Connection conn,String userId) {
	      StringBuilder sql=new StringBuilder();
	      sql.append("select                        ");
	      sql.append("                replyNum      ");
	      sql.append("               ,boardNum      ");
	      sql.append("               ,replyDate     ");
	      sql.append("               ,userId        ");
	      sql.append("               ,replyContent  ");
	      sql.append("  from     one_reply          ");
	      sql.append("  where                       ");
	      sql.append("               userId = ?     ");
	      
	      ResultSet rs=null;
	      List<ReplyDTO> mypagereplylist=new ArrayList<ReplyDTO>();
	      
	      try(
	         PreparedStatement pstmt=conn.prepareStatement(sql.toString());   
	         ){
	         pstmt.setString(1, userId);
	         rs=pstmt.executeQuery();
	         
	         while(rs.next())
	         {
	            ReplyDTO replydto=new ReplyDTO();
	            replydto.setReplyNum(rs.getInt("replyNum"));
	            replydto.setBoardNum(rs.getInt("boardNum"));
	            replydto.setReplyDate(rs.getString("replyDate"));
	            replydto.setUserId(rs.getString("userId"));
	            replydto.setReplyContent(rs.getString("replyContent"));
	            
	            
	            mypagereplylist.add(replydto);
	            
	         }
	      }catch(Exception e)
	      {
	         System.out.println(e);
	      }finally {
	         if(rs!=null) try {rs.close();} catch(SQLException e) {}
	      }return mypagereplylist;
	
	   }

}
