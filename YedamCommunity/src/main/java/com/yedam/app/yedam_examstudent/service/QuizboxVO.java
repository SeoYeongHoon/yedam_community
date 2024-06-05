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
	private int testResultId;
	private int userId;
	private String quizSolution;
	private int isCorrect;
	private int testAnswer;
	private int exampleAnswer;
	private int exampleNum;
	private String textContent;
	private String answerContent;
	private String oneContent; //1번보기내용
	private String twoContent; //2번보기내용
	private String threeContent; //3번보기내용
	private String fourContent; //4번보기내용
	private String fiveContent; //5번보기내용
	private int one; //1번보기번호
	private int two; //2번보기번호
	private int three; //3번보기번호
	private int four; //4번보기번호
	private int five; //5번보기번호
	private String exampleOne;
	private String exampleTwo;
	private String exampleThree;
	private String exampleFour;
	private String exampleFive;
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
	public int getExampleNum() {
		return exampleNum;
	}
	public void setExampleNum(int exampleNum) {
		this.exampleNum = exampleNum;
	}
	public String getOneContent() {
		return oneContent;
	}
	public void setOneContent(String oneContent) {
		this.oneContent = oneContent;
	}
	public String getTwoContent() {
		return twoContent;
	}
	public void setTwoContent(String twoContent) {
		this.twoContent = twoContent;
	}
	public String getThreeContent() {
		return threeContent;
	}
	public void setThreeContent(String threeContent) {
		this.threeContent = threeContent;
	}
	public String getFourContent() {
		return fourContent;
	}
	public void setFourContent(String fourContent) {
		this.fourContent = fourContent;
	}
	public String getFiveContent() {
		return fiveContent;
	}
	public void setFiveContent(String fiveContent) {
		this.fiveContent = fiveContent;
	}
	public int getOne() {
		return one;
	}
	public void setOne(int one) {
		this.one = one;
	}
	public int getTwo() {
		return two;
	}
	public void setTwo(int two) {
		this.two = two;
	}
	public int getThree() {
		return three;
	}
	public void setThree(int three) {
		this.three = three;
	}
	public int getFour() {
		return four;
	}
	public void setFour(int four) {
		this.four = four;
	}
	public int getFive() {
		return five;
	}
	public void setFive(int five) {
		this.five = five;
	}
	public String getExampleOne() {
		return exampleOne;
	}
	public void setExampleOne(String exampleOne) {
		this.exampleOne = exampleOne;
	}
	public String getExampleTwo() {
		return exampleTwo;
	}
	public void setExampleTwo(String exampleTwo) {
		this.exampleTwo = exampleTwo;
	}
	public String getExampleThree() {
		return exampleThree;
	}
	public void setExampleThree(String exampleThree) {
		this.exampleThree = exampleThree;
	}
	public String getExampleFour() {
		return exampleFour;
	}
	public void setExampleFour(String exampleFour) {
		this.exampleFour = exampleFour;
	}
	public String getExampleFive() {
		return exampleFive;
	}
	public void setExampleFive(String exampleFive) {
		this.exampleFive = exampleFive;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public int getTestResultId() {
		return testResultId;
	}
	public void setTestResultId(int testResultId) {
		this.testResultId = testResultId;
	}
	
	
}
