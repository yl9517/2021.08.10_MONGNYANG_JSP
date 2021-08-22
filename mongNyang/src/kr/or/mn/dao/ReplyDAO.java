package kr.or.mn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.mn.dto.AlertDTO;
import kr.or.mn.dto.PagingDTO;
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
      sql.append("                          boardNum          ");
      sql.append("                          ,replyDate        ");
      sql.append("                          ,userId           ");
      sql.append("                          ,replyContent     ");
      sql.append("                          ,alertCheck)      ");
      sql.append("  values(?,now(),?,?,0)                     ");
      
      int replyNum=0;
      ResultSet rs=null;
      try(
         PreparedStatement pstmt=conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
         ){
            pstmt.setInt(1, dto.getBoardNum());
            pstmt.setString(2, dto.getUserId());
            pstmt.setString(3, dto.getReplyContent());
            
            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys(); //방금 생성된 댓글번호 받기
            if(rs.next())
               replyNum = rs.getInt(1);
            
      }catch(SQLException e)
      {
         System.out.println(e);
      }finally {
         if(rs!=null) try {rs.close();} catch (SQLException e) {}
      }
      return replyNum;
   }
   
   
   public List<ReplyDTO> replyList(Connection conn, int boardNum, int replyNum){
      
      StringBuilder sql=new StringBuilder();
      sql.append("  select                      ");
      sql.append("                replyNum       ");
      sql.append("               ,replyContent  ");
      sql.append("               ,userId        ");
      sql.append("               ,replyDate     ");
      sql.append("               ,boardNum      ");
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
      sql.append(" update one_reply         ");
      sql.append(" set                  ");
      sql.append("   replyContent = ?      ");
      sql.append(" where replyNum = ?         ");
      
      
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
      sql.append("         ,boardTitle       ");
      sql.append("         ,replyNum         ");
      sql.append("         ,alertCheck       ");
      sql.append("         ,replyDate        ");
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
      sql.append("    update    one_reply    ");
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
      
      public List<ReplyDTO> mypageReply(Connection conn,String userId, PagingDTO pdto) {
         StringBuilder sql=new StringBuilder();
         sql.append("select *                      ");
         sql.append("  from(                       ");
         sql.append("  select                      ");
         sql.append("                replyNum      ");
         sql.append("               ,boardNum      ");
         sql.append("               ,replyDate     ");
         sql.append("               ,userId        ");
         sql.append("               ,replyContent  ");
         sql.append("  from     one_reply          ");
         sql.append("  where                       ");
         sql.append("               userId = ?     ");
         sql.append("  order by replyNum desc  ) e ");
         sql.append("  limit                       ");
         sql.append("          ?,?                 ");
         
         ResultSet rs=null;
         List<ReplyDTO> mypagereplylist=new ArrayList<ReplyDTO>();
         
         try(
            PreparedStatement pstmt=conn.prepareStatement(sql.toString());   
            ){
            pstmt.setString(1, userId);
            pstmt.setInt(2, pdto.getStartrow()-1);
            pstmt.setInt(3, pdto.getEndrow());
            rs=pstmt.executeQuery();
            
            while(rs.next())
            {
               ReplyDTO dto=new ReplyDTO();
               dto.setReplyNum(rs.getInt("replyNum"));
               dto.setBoardNum(rs.getInt("boardNum"));
               dto.setReplyDate(rs.getString("replyDate"));
               dto.setUserId(rs.getString("userId"));
               dto.setReplyContent(rs.getString("replyContent"));
               
               
               mypagereplylist.add(dto);
            
            }
         }catch(Exception e)
         {
            System.out.println(e);
         }finally {
            if(rs!=null) try {rs.close();} catch(SQLException e) {}
         }return mypagereplylist;
   
      }
      
      public int getTotalCount(Connection conn, String userId) {
         // TODO Auto-generated method stub
         StringBuilder sql=new StringBuilder();
         sql.append("  select count(*)      ");
         sql.append("  from one_reply      ");
         sql.append("  where userId=?        ");
         
         int totalcount=0;
         ResultSet rs=null;
         try(
               PreparedStatement pstmt=conn.prepareStatement(sql.toString());
               
               ){
               pstmt.setString(1, userId);
               rs=pstmt.executeQuery();
               
            if(rs.next()) {
               totalcount=rs.getInt(1);
            }
            
         }catch(SQLException e) {
            System.out.println(e);
         }finally {
            if(rs!=null) try {rs.close();} catch(SQLException e) {}
         }
         return totalcount;
      }

}