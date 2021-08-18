package kr.or.mn.dto;

public class ImageDTO {
	private int imageNum;
	private String imageName;
	private String imagePath;
	private int boardNum;
	private int replyNum;
	
	public int getImageNum() {
		return imageNum;
	}
	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
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
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	
}
