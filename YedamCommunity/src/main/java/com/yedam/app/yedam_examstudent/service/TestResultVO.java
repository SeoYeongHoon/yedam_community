package com.yedam.app.yedam_examstudent.service;

import lombok.Data;

@Data
public class TestResultVO {
	private int testResultId;
	private int testAnswer;
	private int quizId;
	private int isCorrect;
	private int userId;
	public int getTestResultId() {
		return testResultId;
	}
	public void setTestResultId(int testResultId) {
		this.testResultId = testResultId;
	}
	public int getTestAnswer() {
		return testAnswer;
	}
	public void setTestAnswer(int testAnswer) {
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
	
	
}
