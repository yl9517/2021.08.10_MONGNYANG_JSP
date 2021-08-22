package kr.or.mn.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import kr.or.mn.comm.DBConnection;
import kr.or.mn.dao.ReplyDAO;
import kr.or.mn.dto.AlertDTO;
import kr.or.mn.dto.PageDTO;
import kr.or.mn.dto.ReplyDTO;

public class ReplyService {

private static ReplyService instance=new ReplyService();
   
   public static ReplyService getInstance() {
      return instance;
   }
   private ReplyService() {}
   
   /*
    * public List<ReplyDTO> getReplayList(){
    * 
    * DBConnection dbconn=DBConnection.getDBInstance(); Connection conn=null;
    * List<ReplyDTO> list=new ArrayList<ReplyDTO>();
    * 
    * try { conn=dbconn.getConnection(); ReplyDAO dao=new ReplyDAO();
    * list=dao.getReplyList(conn); }catch(SQLException|NamingException e) {
    * System.out.println(e); }finally { if(conn!=null) try {conn.close();}
    * catch(SQLException e) {} } return list; }
    */
   
   // 댓글 등록 및 댓글번호 받아오기
   public int insertReply(ReplyDTO dto) {
      DBConnection dbconn=DBConnection.getDBInstance();
      Connection conn=null;
      int replyNum=0;
      
      try {
         conn=dbconn.getConnection();
         ReplyDAO dao=ReplyDAO.getDAO();
         replyNum=dao.insertReply(conn, dto);
         
      }catch(SQLException | NamingException e) {
         System.out.println(e);
      }finally {
         if(conn!=null) try {conn.close();} catch(SQLException e) {}
      }
      return replyNum;
   }
   
   
   
   //댓글 리스트
   public List<ReplyDTO> replyList(int boardNum) {
      DBConnection dbconn=DBConnection.getDBInstance();
      Connection conn=null;
      List<ReplyDTO> list=new ArrayList<ReplyDTO>();
      
      try {
         conn=dbconn.getConnection();
         ReplyDAO dao=ReplyDAO.getDAO();
         list=dao.replyList(conn, boardNum, boardNum);
      }catch(SQLException | NamingException e) {
         System.out.println(e);
      }finally {
         if(conn!=null) try {conn.close();} catch(SQLException e) {}
      }
      return list;
   }
   
   
   
   //댓글 삭제
   public void replyDelete(int replyNum, int boardNum) {
      DBConnection dbconn=DBConnection.getDBInstance();
      Connection conn=null;
      
      try {
         conn=dbconn.getConnection();
         ReplyDAO dao=ReplyDAO.getDAO();
         dao.replyDelete(conn, replyNum, boardNum);
      }catch(SQLException | NamingException e) {
         System.out.println(e);
      }finally {
         if(conn!=null) try {conn.close();} catch(SQLException e) {}
         
      }
   }
   
   
   //댓글 수정 감을 못잡겠어요 ㅜㅜ
   /*
    * public void replyModify(ReplyDTO dto) {
    * 
    * DBConnection dbconn=DBConnection.getDBInstance(); Connection conn=null;
    * ReplyDAO dao=new ReplyDAO(); try { conn=dbconn.getConnection();
    * dao.replyModify(conn, dto);
    * 
    * conn.commit(); }catch(SQLException|NamingException e) {
    * System.out.println(e); try {conn.rollback();} catch(SQLException e2) {}
    * }finally { if(conn!=null) try {conn.close();} catch(SQLException e) {} } }
    
   public void replyModify(int replyNum, int boardNum, String replyContent) {
      // TODO Auto-generated method stub
      
      DBConnection dbconn=DBConnection.getDBInstance();
      Connection conn=null;
      ReplyDAO dao=ReplyDAO.getDAO();
      try {
         conn=dbconn.getConnection();
         dao.replyModify(conn, replyNum, boardNum, replyContent);
         
         conn.commit();
      }catch(SQLException|NamingException e)
      {
         System.out.println(e);
         try {conn.rollback();} catch(SQLException e2) {}
      }finally {
         if(conn!=null) try {conn.close();} catch(SQLException e) {}
      }
   
      
   }*/
   
   
   
   //   댓글수정
   public void replyModify(ReplyDTO dto) {
      
      DBConnection dbconn = DBConnection.getDBInstance();
      Connection conn = null;
      
      try {
         conn = dbconn.getConnection();
         ReplyDAO dao = ReplyDAO.getDAO();
         dao.replyModify(conn, dto);   
         
      }catch(SQLException | NamingException e) {
         System.out.println(e);
         
      }finally {
         if(conn!=null) try {conn.close();} catch(SQLException e) {}
      }
      
      
   }
   
   
   
   
   //내 게시글에 댓글이 달렸다면
   public List<AlertDTO> myAlert(String userId){
      DBConnection dbconn = DBConnection.getDBInstance();
      
      Connection conn = null;
      List<AlertDTO> list = new ArrayList<AlertDTO>();
      try {
         conn= dbconn.getConnection();
         
         ReplyDAO dao = ReplyDAO.getDAO();
         list = dao.myAlert(conn, userId);      
      }catch (SQLException | NamingException e) {
         System.out.println(e);
      }finally {
         if(conn!=null) try {conn.close();} catch(SQLException e) {}
      }
      return list;
   }
   
   //내 게시글에 달린 댓글 1또는 2로 변경
   public void myAlertUpdate(int replyNum, int changeAlert){
      DBConnection dbconn = DBConnection.getDBInstance();
      
      Connection conn = null;
      try {
         conn= dbconn.getConnection();
         
         ReplyDAO dao = ReplyDAO.getDAO();
         dao.myAlertUpdate(conn, replyNum,changeAlert);      
         
      }catch (SQLException | NamingException e) {
         System.out.println(e);
      }finally {
         if(conn!=null) try {conn.close();} catch(SQLException e) {}
      }
   }
   
   //내 댓글 받아오기 (유저 기준으로 모든 댓글리스트)
   public List<ReplyDTO> mypageReply(String userId, PageDTO pdto) {
         DBConnection dbconn=DBConnection.getDBInstance();
         Connection conn=null;
         List<ReplyDTO> mypagereplylist=new ArrayList<ReplyDTO>();
       
         try {
            conn=dbconn.getConnection();
            ReplyDAO dao=ReplyDAO.getDAO();
            mypagereplylist=dao.mypageReply(conn, userId, pdto);
            
         }catch(SQLException | NamingException e) {
            System.out.println(e);
         }finally {
            if(conn!=null) try {conn.close();} catch(SQLException e) {}
         }
         return mypagereplylist;
      }
   
   
   //댓글자료 전체 갯수 받아오기(유저기준으로) 
      public int getTotalCount(String userId) {
         // TODO Auto-generated method stub
         DBConnection dbconn=DBConnection.getDBInstance();
         Connection conn=null;
         
         int totalcount=0;
         try {
            conn=dbconn.getConnection();
            ReplyDAO dao=ReplyDAO.getDAO();
            
            totalcount=dao.getTotalCount(conn, userId);
            
         }catch(SQLException|NamingException e) {
            System.out.println(e);
         }finally {
            if(conn!=null) try {conn.close();} catch(SQLException e) {}
         }
         return totalcount;
      }

}
   