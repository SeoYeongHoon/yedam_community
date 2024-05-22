package com.yedam.app.yedam_examstudent.service;

public class ExamResultVO {
	private int resultId;
	private int resultScore;
	private String feedback;
	private int isReexam;
	private int quizId;
	private int subjectId;
	private int passScore;
	private int testTargetId;
	
	//기타
	private int testId;
	private int userId;
	private String name;
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
	public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
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
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
