package com.yedam.app.yedam_examstudent.service;

import lombok.Data;

@Data
public class QuizboxVO { //선택된문제 테이블
	private int quizboxId; //문제관리번호
	private String quizContent; //문제내용
	private int quizId; //문제번호
	private int subjectId; //과목번호
	private int testId; //시험출제번호
	private int quizScore; //배점
	private int page; //문제페이지번호
	private int quizCnt; //문제개수
	private int rn; //문제랜덤 일련번호
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
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getQuizCnt() {
		return quizCnt;
	}
	public void setQuizCnt(int quizCnt) {
		this.quizCnt = quizCnt;
	}
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	
	
}
