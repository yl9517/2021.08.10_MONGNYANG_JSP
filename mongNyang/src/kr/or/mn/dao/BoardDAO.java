package kr.or.mn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.mn.dto.BoardDTO;
import kr.or.mn.dto.CategoryDTO;
import kr.or.mn.dto.MainDTO;

public class BoardDAO {
	private static BoardDAO dao=new BoardDAO();
	public static BoardDAO getDAO() {
		return dao;
	}
	private BoardDAO() {}
	
	public List<MainDTO> getList(Connection conn, String boardType) {
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
		sql.append("  from one_board as b				");
		sql.append("  inner join one_category as c		");
		sql.append("  on b.categoryName=c.categoryName  ");
		sql.append("  where c.boardType=?				");
		
		List<MainDTO> list=new ArrayList<>();
		ResultSet rs=null;
		try( PreparedStatement pstmt=conn.prepareStatement(sql.toString());
				
				){
				pstmt.setString(1, boardType);
				rs=pstmt.executeQuery();
			
			while(rs.next()) {
				MainDTO dto=new MainDTO();
				dto.setBoardNum(rs.getInt("boardNum"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setBoardContent(rs.getString("boardContent"));
				dto.setUserId(rs.getString("userId"));
				dto.setBoardDate(rs.getString("boardDate"));
				dto.setPetAddr(rs.getString("petAddr"));
				dto.setPetType(rs.getString("petType"));
				dto.setImageNum(rs.getInt("imageNum"));
				dto.setBoardState(rs.getBoolean("boardState"));
				dto.setBoardReadNo(rs.getInt("boardReadNo"));
				list.add(dto);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			if(rs!=null) try {rs.close();} catch(SQLException e) {}
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
	

	//게시판 테이블에 추가
	public int insertBoard(Connection conn, String boardType, MainDTO dto) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("  insert into one_board	(					");
		sql.append("						boardTitle		");
		sql.append("						, boardContent		");
		sql.append("						, userId			");
		sql.append("						, boardDate			");
		sql.append("						, categoryName		");
//		sql.append("						, imageNum			");
		sql.append("						, boardState		");
		sql.append("						, boardReadNo )		");
		sql.append("  values(?, ?, 1, ?, ?, 0, 0)		");
		
		int result=0;
		try(
				PreparedStatement pstmt=conn.prepareStatement(sql.toString());
				){
			pstmt.setString(1, dto.getBoardTitle());
			pstmt.setString(2, dto.getBoardContent());
//			pstmt.setString(3, "dto.getUserId()");
			//아이디 어디서 받아옴?
			pstmt.setString(3, "2021-08-18");
			pstmt.setString(4, dto.getCategoryName());
//			pstmt.setInt(5, dto.getImageNum());
			//이미지도 고민해야함
			
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println(e);
		}
		return result;
	}
	
	//해당하는 카테고리 찾기 (게시글 등록 시 필요)
	public String findCategoryName(Connection conn, String boardType, String petAddr, String petType) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select                   ");
		sql.append("  categoryName            ");
		sql.append("   from one_category      ");
		sql.append("  where  boardType = ?    ");
		sql.append("  and  petAddr = ?        ");
		sql.append("  and  petType = ?        ");
			
		ResultSet rs = null;
		String categoryName= null;
		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
			pstmt.setString(1, boardType);
			pstmt.setString(2, petAddr);
			pstmt.setString(3, petType);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				categoryName = rs.getString("categoryName");
			}
			
		}catch (SQLException e) {
			System.out.println(e);
		}
		return categoryName;
	}
	
	//카테고리내용 리스트
//	public List<CategoryDTO> findCategoryContent(Connection conn, String categoryName) {
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select                    ");
//		sql.append("    boardType              ");
//		sql.append("    ,petAddr               ");
//		sql.append("    ,petType               ");
//		sql.append("   from one_category       ");
//		sql.append("  where categoryName = ?   ");
//			
//		ResultSet rs = null;
//		CategoryDTO categorys = new CategoryDTO();
//		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
//			pstmt.setString(1, categoryName);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				categorys.setBoardType(rs.getString("boardType"));
//				categorys.setPetAddr(rs.getString("petAddr"));
//				categorys.setPetType(rs.getString("petType"));
//			}
//			
//		}catch (SQLException e) {
//			System.out.println(e);
//		}
//		return categorys;
//	} 
	
	
	//카테고리이름에 해당하는 내용 찾기 (개별 조회 시 필요)
	public CategoryDTO findCategoryContent(Connection conn, String categoryName) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select                    ");
		sql.append("    boardType              ");
		sql.append("    ,petAddr               ");
		sql.append("    ,petType               ");
		sql.append("   from one_category       ");
		sql.append("  where categoryName = ?   ");
			
		ResultSet rs = null;
		CategoryDTO categorys = new CategoryDTO();
		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
			pstmt.setString(1, categoryName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				categorys.setBoardType(rs.getString("boardType"));
				categorys.setPetAddr(rs.getString("petAddr"));
				categorys.setPetType(rs.getString("petType"));
			}
			
		}catch (SQLException e) {
			System.out.println(e);
		}
		return categorys;
	}
	
	//조회수 증가 ( 게시글 클릭할 때마다)
	    public void updateReadNo(Connection conn,int boardNum) {
	        StringBuilder sql = new StringBuilder();
	        sql.append("  update one_board                  ");
	        sql.append("  set readNo = readNo+1             ");
	        sql.append("  where boardNum = ?                ");

	        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
	            pstmt.setInt(1, boardNum);

	            pstmt.executeUpdate();
	        }catch (SQLException e) {
	            System.out.println(e);
	        }
	    }
	
}
