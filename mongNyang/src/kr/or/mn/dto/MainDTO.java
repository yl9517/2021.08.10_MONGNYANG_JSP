package kr.or.mn.dto;

public class MainDTO {
	private int boardNum;
	private String boardTitle;
	private String boardContent;
	private String userId;		//글쓴이아이디
	private String boardDate;	//필요X
	private String categoryName;
	private boolean boardState; //필요X
	private int boardReadNo;    //필요X
	private String boardType;
	private String petAddr;
    private String petType;
    private int imageNum;
    private String imageName;
    private String imagePath;
    private String loginId;		//로그인아이디 (필요없긴 함)
    
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
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
	public int getImageNum() {
		return imageNum;
	}
	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
	}
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
	public String getPetAddr() {
		return petAddr;
	}
	public void setPetAddr(String petAddr) {
		this.petAddr = petAddr;
	}
	public String getPetType() {
		return petType;
	}
	public void setPetType(String petType) {
		this.petType = petType;
	}
    
    
}
