package kr.or.mn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public List<MainDTO> getList(Connection conn, String boardType,String petAddr) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("  select							");
		sql.append("		b.boardNum					");
		sql.append("		, boardTitle				");
		sql.append("		, boardContent				");
		sql.append("		, userId					");
		sql.append("		, boardDate					");
		sql.append("		, petAddr					");
		sql.append("		, petType					");
		sql.append("		, imageName					");
		sql.append("		, imagePath					");
		sql.append("		, boardState				");
		sql.append("		, boardReadNo				");
		sql.append("  from one_board as b				");
		sql.append("  inner join one_category as c		");
		sql.append("  on b.categoryName=c.categoryName  ");
		sql.append("  inner join one_image as i			");
		sql.append("  on b.boardNum=i.boardNum			");
		sql.append("  where c.boardType=?				");
	if(!petAddr.equals("")) //petAddr 이름이 ""이 아니라면
		sql.append("  and c.petAddr=?					"); //지역
		sql.append("  order by boardNum desc			");
		
		List<MainDTO> list=new ArrayList<>();
		ResultSet rs=null;
		try( PreparedStatement pstmt=conn.prepareStatement(sql.toString());
				
				){
				pstmt.setString(1, boardType);
			if(!petAddr.equals("")) //petAddr 이름이 all이 아니라면
				pstmt.setString(2, petAddr);
			
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
				dto.setImageName(rs.getString("imageName"));
				dto.setImagePath(rs.getString("imagePath"));
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

	public MainDTO getDetail(Connection conn, int boardnum) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("  select				");
		sql.append("		boardNum		");
		sql.append("		, boardTitle	");
		sql.append("		, boardContent	");
		sql.append("		, userId		");
		sql.append("		, boardDate		");
		sql.append("		, b.categoryName	");
//		sql.append("		, imageNum		");
		sql.append("		, boardState	");
		sql.append("		, boardReadNo	");
		sql.append("		, boardType		");
		sql.append("  from one_board as b				");
		sql.append("  inner join one_category as c		");
		sql.append("  on b.categoryName=c.categoryName  ");
		sql.append("  where boardNum=?		");

		ResultSet rs=null;
		MainDTO dto=new MainDTO();
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
//				dto.setImageNum(rs.getInt("imageNum"));
				dto.setBoardState(rs.getBoolean("boardState"));
				dto.setBoardReadNo(rs.getInt("boardReadNo"));
				dto.setBoardType(rs.getString("boardType"));
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
	public int insert(Connection conn, MainDTO dto) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("  insert into one_board	(					");
		sql.append("						boardTitle			");
		sql.append("						, boardContent		");
		sql.append("						, userId			");
		sql.append("						, boardDate			");
		sql.append("						, categoryName		");
//		sql.append("						, imageNum			");
		sql.append("						, boardState		");
		sql.append("						, boardReadNo )		");
		sql.append("  values(?, ?, ?, now(), ?, 0, 0)		");
		
		int boardNum=0;
		ResultSet rs=null;
		try(
				PreparedStatement pstmt=conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				){
			pstmt.setString(1, dto.getBoardTitle());
			pstmt.setString(2, dto.getBoardContent());
			pstmt.setString(3, dto.getUserId());
			pstmt.setString(4, dto.getCategoryName());
//			pstmt.setInt(5, dto.getImageNum());
			//이미지도 고민해야함
			
			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
			if(rs.next()) {
				boardNum=rs.getInt(1);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}finally {
			if(rs!=null) try {rs.close();} catch (SQLException e) {}
		}
		return boardNum;
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
	        sql.append("  set boardReadNo = boardReadNo+1             ");
	        sql.append("  where boardNum = ?                ");

	        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
	            pstmt.setInt(1, boardNum);

	            pstmt.executeUpdate();
	        }catch (SQLException e) {
	            System.out.println(e);
	        }
	    }
	    
	    //게시글 수정
		public void modify(Connection conn, MainDTO dto) {
			// TODO Auto-generated method stub
			StringBuilder sql=new StringBuilder();
			sql.append("  update one_board		");
			sql.append("  set					");
			sql.append("	boardTitle=?		");
			sql.append("	, boardContent=?	");
			sql.append("	, categoryName=?	");
//			sql.append("	, imageNum=?		");
			sql.append("	, boardState=1		"); 
			sql.append("  where					");
			sql.append("  		boardNum=?		");

			try(
					PreparedStatement pstmt=conn.prepareStatement(sql.toString());
					){
				pstmt.setString(1, dto.getBoardTitle());
				pstmt.setString(2, dto.getBoardContent());
				pstmt.setString(3, dto.getCategoryName());
//				pstmt.setBoolean(4, dto.isBoardState());  //필요없음
				pstmt.setInt(4, dto.getBoardNum());
				//이미지도 고민해야함
				
				pstmt.executeUpdate();
				
			}catch(SQLException e) {
				System.out.println(e);
			}
		}
		
		
		//해결상태 변경
		public void stateModify(Connection conn, int boardNum, boolean boardState) {
			// TODO Auto-generated method stub
			StringBuilder sql=new StringBuilder();
			sql.append("  update one_board		");
			sql.append("  set					");
			sql.append("	boardState=?		");
			sql.append("  where					");
			sql.append("  		boardNum=?		");

			try(PreparedStatement pstmt=conn.prepareStatement(sql.toString());
					){
				pstmt.setBoolean(1, boardState);
				pstmt.setInt(2, boardNum);
				
				pstmt.executeUpdate();
				
			}catch(SQLException e) {
				System.out.println(e);
			}
		}
		
		// 내 게시글 찾기
		public List<BoardDTO> findMyWrite(Connection conn,String userId) {
			StringBuilder sql = new StringBuilder();
			sql.append("   select  boardTitle    		 ");
			sql.append("     	   ,boardDate      		 ");
			sql.append("           ,boardState    		 "); 
			sql.append("           ,boardNum    		 "); 	//
			sql.append("   	from one_board    			 ");
			sql.append("	where userId = ?    		 ");
			
			ResultSet rs = null;
			List<BoardDTO> list = new ArrayList<BoardDTO>();
			try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
				pstmt.setString(1, userId);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setBoardTitle(rs.getString("boardTitle"));
					dto.setBoardDate(rs.getString("boardDate"));
					dto.setBoardState(rs.getBoolean("boardState"));
					dto.setBoardNum(rs.getInt("boardNum"));		//
					
					list.add(dto);
				}
						
			}catch (Exception e) {
				System.out.println(e);
			}finally {
				if(rs!=null) try {rs.close();} catch(SQLException e){}
			}
			return list;
		}
			
		
}
