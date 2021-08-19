package kr.or.mn.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import kr.or.mn.comm.DBConnection;
import kr.or.mn.dao.ReplyDAO;
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
	
	public int insertReply(ReplyDTO dto) {
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		int result=0;
		
		try {
			conn=dbconn.getConnection();
			ReplyDAO dao=new ReplyDAO();
			result=dao.insertReply(conn, dto);
			
		}catch(SQLException | NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return result;
	}
	
	public List<ReplyDTO> replyList(int boardNum) {
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		List<ReplyDTO> list=new ArrayList<ReplyDTO>();
		
		try {
			conn=dbconn.getConnection();
			ReplyDAO dao=new ReplyDAO();
			list=dao.replyList(conn, boardNum, boardNum);
		}catch(SQLException | NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		return list;
	}
	
	public void replyDelete(int replyNum, int boardNum) {
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		
		try {
			conn=dbconn.getConnection();
			ReplyDAO dao=new ReplyDAO();
			dao.replyDelete(conn, replyNum, boardNum);
		}catch(SQLException | NamingException e) {
			System.out.println(e);
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
			
		}
	}
	
	public void replyModify(ReplyDTO dto) {
		
		DBConnection dbconn=DBConnection.getDBInstance();
		Connection conn=null;
		ReplyDAO dao=new ReplyDAO();
		try {
			conn=dbconn.getConnection();
			dao.replyModify(conn, dto);
			
			conn.commit();
		}catch(SQLException|NamingException e)
		{
			System.out.println(e);
			try {conn.rollback();} catch(SQLException e2) {}
		}finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
	}
	
}
	

