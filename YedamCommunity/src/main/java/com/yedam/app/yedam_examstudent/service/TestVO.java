package com.yedam.app.yedam_examstudent.service;

import java.util.Date;

import lombok.Data;

@Data
public class TestVO {
	//tests 테이블
	private int testId;
	private int curriculumId;
	private String testContent;
	private int testTime;
	private Date testDate;
	private String testName;
	private int classId;
	
	//test_targets 테이블
	private int userId;
	private int testTargetId;
	
	//users 테이블
	private String name;
	
	//quizbox 테이블
	private int quizScore;
	private int subjectId;
	private int quizId;
	private String quizContent;
	
	//answers 테이블
	private int exampleNum;
	private String textContent;
	
	//curriculum 테이블
	private String curriculumName;
	
	//exam_results 테이블
	private int resultId;
	private int resultScore;
	private String feedback;
	private int isReexam;
	private int passScore;
	private String subjectName;
	
	//기타 필드
	private int quizIdCnt; //시험문제개수
	private int quizCnt;
	private int page;
	private int rn;
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getCurriculumId() {
		return curriculumId;
	}
	public void setCurriculumId(int curriculumId) {
		this.curriculumId = curriculumId;
	}
	public String getTestContent() {
		return testContent;
	}
	public void setTestContent(String testContent) {
		this.testContent = testContent;
	}
	public int getTestTime() {
		return testTime;
	}
	public void setTestTime(int testTime) {
		this.testTime = testTime;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTestTargetId() {
		return testTargetId;
	}
	public void setTestTargetId(int testTargetId) {
		this.testTargetId = testTargetId;
	}
	public int getQuizScore() {
		return quizScore;
	}
	public void setQuizScore(int quizScoreSum) {
		this.quizScore = quizScoreSum;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	public String getQuizContent() {
		return quizContent;
	}
	public void setQuizContent(String quizContent) {
		this.quizContent = quizContent;
	}
	public int getExampleNum() {
		return exampleNum;
	}
	public void setExampNum(int exampNum) {
		this.exampleNum = exampNum;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public String getCurriculumName() {
		return curriculumName;
	}
	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}
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
	public int getPassScore() {
		return passScore;
	}
	public void setPassScore(int passScore) {
		this.passScore = passScore;
	}
	public int getQuizIdCnt() {
		return quizIdCnt;
	}
	public void setQuizIdCnt(int quizIdCnt) {
		this.quizIdCnt = quizIdCnt;
	}
	public int getQuizCnt() {
		return quizCnt;
	}
	public void setQuizCnt(int quizCnt) {
		this.quizCnt = quizCnt;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setExampleNum(int exampleNum) {
		this.exampleNum = exampleNum;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	
	
	
	
	
	
}
