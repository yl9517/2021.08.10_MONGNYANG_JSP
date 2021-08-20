package kr.or.mn.dto;

public class AlertDTO {
	private int boardNum;
	private String boardTitle;
	private int replyNum;
	private int alertCheck;
	private String replyDate;
	
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
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	public int getAlertCheck() {
		return alertCheck;
	}
	public void setAlertCheck(int alertCheck) {
		this.alertCheck = alertCheck;
	}
	public String getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
	
	
}
