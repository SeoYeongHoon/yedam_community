package com.yedam.app.yedam_examstudent.service;

import lombok.Data;

@Data
public class TestResultVO {
	private int testResultId;
	private String testAnswer;
	private int quizId;
	private int isCorrect;
	private int userId;
	//기타
	private int testId;
	private int resultId;
	public int getTestResultId() {
		return testResultId;
	}
	public void setTestResultId(int testResultId) {
		this.testResultId = testResultId;
	}
	public String getTestAnswer() {
		return testAnswer;
	}
	public void setTestAnswer(String testAnswer) {
		this.testAnswer = testAnswer;
	}
	public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	public int getIsCorrect() {
		return isCorrect;
	}
	public void setIsCorrect(int isCorrect) {
		this.isCorrect = isCorrect;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	
	
}
