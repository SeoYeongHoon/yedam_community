package com.yedam.app.yedam_examstudent.service;

import java.util.Date;

import lombok.Data;

@Data
public class TestVO { //시험출제 테이블
	private int testId;
	private int curriculumId;
	private String testContent;
	private Date testTime;
	private Date testDate;
	private String testName;
	private int classId;
	private int userId;
	
	private int quizScoreSum; //시험목록 >> 시험총점
	private int quizIdCnt; // 시험목록 >> 문제개수
	private String curriculumName; //과정명
	private int subjectId; //과목번호
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
	public Date getTestTime() {
		return testTime;
	}
	public void setTestTime(Date testTime) {
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
	public int getQuizScoreSum() {
		return quizScoreSum;
	}
	public void setQuizScoreSum(int quizScoreSum) {
		this.quizScoreSum = quizScoreSum;
	}
	public int getQuizIdCnt() {
		return quizIdCnt;
	}
	public void setQuizIdCnt(int quizIdCnt) {
		this.quizIdCnt = quizIdCnt;
	}
	public String getCurriculumName() {
		return curriculumName;
	}
	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
	
	
}
