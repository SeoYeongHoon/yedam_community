package com.yedam.app.yedam_examstudent.service;

import lombok.Data;

@Data
public class QuizboxVO { //선택된문제 테이블
	private int quizboxId;
	private String quizContent;
	private int quizId;
	private int subjectId;
	private int testId;
	private int quizScore;
	public int getQuizboxId() {
		return quizboxId;
	}
	public void setQuizboxId(int quizboxId) {
		this.quizboxId = quizboxId;
	}
	public String getQuizContent() {
		return quizContent;
	}
	public void setQuizContent(String quizContent) {
		this.quizContent = quizContent;
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
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getQuizScore() {
		return quizScore;
	}
	public void setQuizScore(int quizScore) {
		this.quizScore = quizScore;
	}
	
	
}
