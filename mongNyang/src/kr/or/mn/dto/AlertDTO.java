package kr.or.mn.dto;

public class AlertDTO {
	private int boardNum;
	private String boardTitle;
	private boolean alertCheck;
	
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
	public boolean isAlertCheck() {
		return alertCheck;
	}
	public void setAlertCheck(boolean alertCheck) {
		this.alertCheck = alertCheck;
	}
	
	
}
