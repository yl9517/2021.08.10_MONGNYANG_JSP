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
import kr.or.mn.dto.PageDTO;

public class BoardDAO {
	private static BoardDAO dao=new BoardDAO();
	public static BoardDAO getDAO() {
		return dao;
	}
	private BoardDAO() {}
	
	public List<MainDTO> getList(Connection conn, String boardType,String petAddr, PageDTO pdto) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("  select *											");
		sql.append("  from (											");
		sql.append("  		select								");
		sql.append("				b.boardNum					");
		sql.append("				, boardTitle				");
		sql.append("				, boardContent				");
		sql.append("				, userId					");
		sql.append("				, boardDate					");
		sql.append("				, petAddr					");
		sql.append("				, petType					");
		sql.append("				, imageName					");
		sql.append("				, imagePath					");
		sql.append("				, boardState				");
		sql.append("				, boardReadNo				");
		sql.append("		from one_board as b					");
		sql.append("		inner join one_category as c		");
		sql.append("		on b.categoryName=c.categoryName	");
		sql.append("		inner join one_image as i			");
		sql.append("		on b.boardNum=i.boardNum			");
		sql.append("		where c.boardType=?					");
		if(!petAddr.equals("all")) 	//petAddr 이름이 all이 아니라면 (뭐라도 적혀져 있다면)
			sql.append("  			and c.petAddr=?						"); //지역
		if(!pdto.getSearch().equals("")&&!pdto.getSearchtxt().equals("")) {
			if(pdto.getSearch().equals("boardTitle")) {
				sql.append("  		and boardTitle like ?				");
			}else if(pdto.getSearch().equals("boardContent")) {
				sql.append("  		and boardContent like ?				");
			}
		}
		sql.append("  		order by boardNum desc				");
		sql.append("		) s											");
		sql.append("  limit												");
		sql.append("  		?,?											");

		
		
		
		List<MainDTO> list=new ArrayList<>();
		ResultSet rs=null;
		try( PreparedStatement pstmt=conn.prepareStatement(sql.toString());
				
				){
				int num = 1;
				
				pstmt.setString(num++, boardType);
				if(!petAddr.equals("all")) { //petAddr 이름이 동서남북 이면
					pstmt.setString(num++, petAddr);
				}
				
				if(!pdto.getSearch().equals("")&&!pdto.getSearchtxt().equals("")) {
					pstmt.setString(num++, "%"+pdto.getSearchtxt()+"%");
				}
					pstmt.setInt(num++, pdto.getStartrow()-1);
					pstmt.setInt(num++, pdto.getPageSize());

			
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
		sql.append("						, boardState		"); //미해결
		sql.append("						, boardReadNo )		"); //조회수
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
			
			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys(); //방금 생성된 게시판 번호 받기
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
	        sql.append("  set boardReadNo = boardReadNo+1   ");
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
			sql.append("  where					");
			sql.append("  		boardNum=?		");

			try(
					PreparedStatement pstmt=conn.prepareStatement(sql.toString());
					){
				pstmt.setString(1, dto.getBoardTitle());
				pstmt.setString(2, dto.getBoardContent());
				pstmt.setString(3, dto.getCategoryName());
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
		public List<BoardDTO> findMyWrite(Connection conn,String userId, PageDTO pdto) {
			StringBuilder sql = new StringBuilder();
			sql.append("   select  boardTitle    		 ");
			sql.append("     	   ,boardDate      		 ");
			sql.append("           ,boardState    		 "); 
			sql.append("           ,boardNum    		 "); 	
			sql.append("   	from one_board    			 ");
			sql.append("	where userId = ?    		 ");
			sql.append("  order by boardNum desc        ");
		    sql.append("  limit                          ");
		    sql.append("          ?,?                    ");
		    
		    
			ResultSet rs = null;
			List<BoardDTO> list = new ArrayList<BoardDTO>();
			try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
				pstmt.setString(1, userId);
				pstmt.setInt(2, pdto.getStartrow()-1);
		        pstmt.setInt(3, pdto.getPageSize());
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
		
		// 게시글 총 글수 (검색) + 메인에 쓸 데이터(3.총 글 수)
		public int getTotalCount(Connection conn, String search, String searchtxt) {
			// TODO Auto-generated method stub
			StringBuilder sql=new StringBuilder();
			sql.append("  select count(*)		");
			sql.append("  from one_board		");
			
			if(!search.equals("") && !searchtxt.equals("")) {
				if(search.equals("boardTitle")) {
					sql.append("  where boardTitle like ?  ");
				}else if(search.equals("boardContent")) {
					sql.append("  where boardContent like ?  ");
				}
			}
			
			int totalcount=0;
			ResultSet rs=null;
			try(
					PreparedStatement pstmt=conn.prepareStatement(sql.toString());
					
					){
					
					if(!search.equals("") && !searchtxt.equals("")) {
						pstmt.setString(1, "%"+searchtxt+"%");
					}
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
		
		
		//해당 게시글번호의 댓글 갯수 가져오기
		public int replyCount(Connection conn, int boardNum){
			StringBuilder sql = new StringBuilder();
			sql.append(" select count(replyNum) ");
			sql.append("  from one_board as b inner join one_reply as r ");
			sql.append("  on b.boardNum = r.boardNum ");
			sql.append("  where r.boardNum = ?       ");
			sql.append("  group by r.boardNum        ");
	
			ResultSet rs = null;
			int replyCount = 0;
			try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
				pstmt.setInt(1, boardNum);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					replyCount = rs.getInt(1);
				}
				
			}catch (SQLException e) {
				System.out.println(e);
			}finally {
				if(rs!=null) try {rs.close();} catch(SQLException e) {}
			}
			return replyCount;
			
		}
		
		//게시물자료 갯수 불러오기(유저 기준으로)
		public int getUserBoardTotalCount(Connection conn, String userId) {
			// TODO Auto-generated method stub
			StringBuilder sql=new StringBuilder();
			sql.append("  select count(*)		");
			sql.append("  from one_board		");
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

		//메인에 쓸 게시글 자료 가져오기 (1.오늘 등록 된 글)
		public int getTodayData(Connection conn){
			StringBuilder sql = new StringBuilder();
			sql.append(" select count(boardDate)                           ");
			sql.append(" from one_board                                    ");
			sql.append("  where boardDate like DATE_FORMAT(now(),'%Y-%m-%d%')  ");
			
			int todayDate = 0;
			try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				ResultSet rs = pstmt.executeQuery();){
				
				if(rs.next())
					todayDate = rs.getInt(1);
				
			}catch (SQLException e) {
				System.out.println(e);
			}
			return todayDate;
		}
		//메인에 쓸 게시글 자료 가져오기 (2.총 해결된 글)
		public int getFinData(Connection conn){
			StringBuilder sql = new StringBuilder();
			sql.append(" select count(boardState)     ");
			sql.append(" from one_board               ");
			sql.append("  where boardState = 1        ");
			
			int finDate = 0;
			try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				ResultSet rs = pstmt.executeQuery();){
				
				if(rs.next())
					finDate = rs.getInt(1);
				
			}catch (SQLException e) {
				System.out.println(e);
			}
			return finDate;
			
		}

		
		
}
