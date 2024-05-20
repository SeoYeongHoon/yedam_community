package com.yedam.app.yedam_examstudent.service;

import lombok.Data;

@Data
public class AnswerVO {
	private int testId; //지문번호
	private int exampleNum; //보기번호
	private int exampleAnswer; //보기정답유무
	private String textContent; //보기내용
	private int quizId; //문제번호
	private int subjectId; //과목번호
	private String quizAnswer; //문제정답
	private int page;
	private int quisScore;
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getExampleNum() {
		return exampleNum;
	}
	public void setExampleNum(int exampleNum) {
		this.exampleNum = exampleNum;
	}
	public int getExampleAnswer() {
		return exampleAnswer;
	}
	public void setExampleAnswer(int exampleAnswer) {
		this.exampleAnswer = exampleAnswer;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
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
	public String getQuizAnswer() {
		return quizAnswer;
	}
	public void setQuizAnswer(String quizAnswer) {
		this.quizAnswer = quizAnswer;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getQuisScore() {
		return quisScore;
	}
	public void setQuisScore(int quisScore) {
		this.quisScore = quisScore;
	}
	
	
}
