package com.jomelon.domain;

public class QnABoardVO {

	private int QnAID;
	private String QnATitle;
	private String userId;
	private String QnADate;
	private String QnAContent;
	private int QnAAvailable;

	public int getQnAID() {
		return QnAID;
	}

	public void setQnAID(int qnAID) {
		QnAID = qnAID;
	}

	public String getQnATitle() {
		return QnATitle;
	}

	public void setQnATitle(String qnATitle) {
		QnATitle = qnATitle;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQnADate() {
		return QnADate;
	}

	public void setQnADate(String qnADate) {
		QnADate = qnADate;
	}

	public String getQnAContent() {
		return QnAContent;
	}

	public void setQnAContent(String qnAContent) {
		QnAContent = qnAContent;
	}

	public int getQnAAvailable() {
		return QnAAvailable;
	}

	public void setQnAAvailable(int qnAAvailable) {
		QnAAvailable = qnAAvailable;
	}

	
}
