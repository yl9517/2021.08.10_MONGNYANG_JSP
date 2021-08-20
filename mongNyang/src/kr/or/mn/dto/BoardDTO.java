package kr.or.mn.dto;

public class BoardDTO {
	private int boardNum;
	private String boardTitle;
	private String boardContent;
	private String userId;
	private String boardDate;
	private String categoryName;
//	private int imageNum;
	private boolean boardState;
	private int boardReadNo;
	
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
//	public int getImageNum() {
//		return imageNum;
//	}
//	public void setImageNum(int imageNum) {
//		this.imageNum = imageNum;
//	}
	public boolean isBoardState() {
		return boardState;
	}
	public void setBoardState(boolean boardState) {
		this.boardState = boardState;
	}
	public int getBoardReadNo() {
		return boardReadNo;
	}
	public void setBoardReadNo(int boardReadNo) {
		this.boardReadNo = boardReadNo;
	}
	
	
}
