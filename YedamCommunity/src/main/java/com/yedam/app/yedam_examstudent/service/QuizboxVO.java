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
	//기타
	private int userId;
	private String quizSolution;
	private int isCorrect;
	private int testAnswer;
	private int exampleAnswer;
	private int exampleNum;
	private String one;
	private String two;
	private String three;
	private String four;
	private String five;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getQuizSolution() {
		return quizSolution;
	}
	public void setQuizSolution(String quizSolution) {
		this.quizSolution = quizSolution;
	}
	public int getIsCorrect() {
		return isCorrect;
	}
	public void setIsCorrect(int isCorrect) {
		this.isCorrect = isCorrect;
	}
	public int getTestAnswer() {
		return testAnswer;
	}
	public void setTestAnswer(int testAnswer) {
		this.testAnswer = testAnswer;
	}
	public int getExampleAnswer() {
		return exampleAnswer;
	}
	public void setExampleAnswer(int exampleAnswer) {
		this.exampleAnswer = exampleAnswer;
	}
	public String getOne() {
		return one;
	}
	public void setOne(String one) {
		this.one = one;
	}
	public String getTwo() {
		return two;
	}
	public void setTwo(String two) {
		this.two = two;
	}
	public String getThree() {
		return three;
	}
	public void setThree(String three) {
		this.three = three;
	}
	public String getFour() {
		return four;
	}
	public void setFour(String four) {
		this.four = four;
	}
	public String getFive() {
		return five;
	}
	public void setFive(String five) {
		this.five = five;
	}
	public int getExampleNum() {
		return exampleNum;
	}
	public void setExampleNum(int exampleNum) {
		this.exampleNum = exampleNum;
	}
	
	
}
