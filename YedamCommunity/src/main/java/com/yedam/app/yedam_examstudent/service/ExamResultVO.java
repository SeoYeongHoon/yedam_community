package com.yedam.app.yedam_examstudent.service;

public class ExamResultVO {
	private int resultId;
	private int resultScore;
	private String feedback;
	private int isReexam;
	private int subjectId;
	private int passScore;
	private int testTargetId;
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public int getResultScore() {
		return resultScore;
	}
	public void setResultScore(int resultScore) {
		this.resultScore = resultScore;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public int getIsReexam() {
		return isReexam;
	}
	public void setIsReexam(int isReexam) {
		this.isReexam = isReexam;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getPassScore() {
		return passScore;
	}
	public void setPassScore(int passScore) {
		this.passScore = passScore;
	}
	public int getTestTargetId() {
		return testTargetId;
	}
	public void setTestTargetId(int testTargetId) {
		this.testTargetId = testTargetId;
	}
	
	
}
