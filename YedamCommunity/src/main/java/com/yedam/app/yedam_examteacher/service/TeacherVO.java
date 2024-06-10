package com.yedam.app.yedam_examteacher.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TeacherVO {
	private int userId;
	private String companyInfo;
	// tests 테이블
	private int testId;
	private String testName;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date testDate;
	private int testTime;
	private String testContent;
	
	// quizes 테이블
	private int quizId;
	private String quizContent;
	private String quizContentJu;
	private String quizSolution;
	private String quizSolutionJu;
	private String quizAnswer;
	private String quizAnswerJu;
	
	// answers 테이블
	private String textContent;
	private String textContent2;
	private String textContent3;
	private String textContent4;
	private String textContent5;
	private String textContentJu;
	private int exampleNum;
	private int exampleAnswer;
	private int exampleAnswer2;
	private int exampleAnswer3;
	private int exampleAnswer4;
	private int exampleAnswer5;
	private int exampleAnswerJu;
	
	// subjects 테이블
	private int subjectId;
	private String subjectName;
	
	// curriculum 테이블
	private int curriculumId;
	private String curriculumName;
	private int classId;
	@DateTimeFormat(pattern = "yyyy.MM.dd")
	private Date curriculumStartDate;
	@DateTimeFormat(pattern = "yyyy.MM.dd")
	private Date curriculumEndDate;
	private int isComplete;
	
	private String name;	
	private int subjectAvg;
	private int resultId;
	private int resultScore;
	private String feedback;
	private int isReexam;
	private int passScore;
	private int testTargetId;
	private int quizScore;
	
	
	
	
	public String getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
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
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public int getTestTime() {
		return testTime;
	}
	public void setTestTime(int testTime) {
		this.testTime = testTime;
	}
	public String getTestContent() {
		return testContent;
	}
	public void setTestContent(String testContent) {
		this.testContent = testContent;
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
	public String getQuizContentJu() {
		return quizContentJu;
	}
	public void setQuizContentJu(String quizContentJu) {
		this.quizContentJu = quizContentJu;
	}
	public String getQuizSolution() {
		return quizSolution;
	}
	public void setQuizSolution(String quizSolution) {
		this.quizSolution = quizSolution;
	}
	public String getQuizSolutionJu() {
		return quizSolutionJu;
	}
	public void setQuizSolutionJu(String quizSolutionJu) {
		this.quizSolutionJu = quizSolutionJu;
	}
	public String getQuizAnswer() {
		return quizAnswer;
	}
	public void setQuizAnswer(String quizAnswer) {
		this.quizAnswer = quizAnswer;
	}
	public String getQuizAnswerJu() {
		return quizAnswerJu;
	}
	public void setQuizAnswerJu(String quizAnswerJu) {
		this.quizAnswerJu = quizAnswerJu;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public String getTextContent2() {
		return textContent2;
	}
	public void setTextContent2(String textContent2) {
		this.textContent2 = textContent2;
	}
	public String getTextContent3() {
		return textContent3;
	}
	public void setTextContent3(String textContent3) {
		this.textContent3 = textContent3;
	}
	public String getTextContent4() {
		return textContent4;
	}
	public void setTextContent4(String textContent4) {
		this.textContent4 = textContent4;
	}
	public String getTextContent5() {
		return textContent5;
	}
	public void setTextContent5(String textContent5) {
		this.textContent5 = textContent5;
	}
	public String getTextContentJu() {
		return textContentJu;
	}
	public void setTextContentJu(String textContentJu) {
		this.textContentJu = textContentJu;
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
	public int getExampleAnswer2() {
		return exampleAnswer2;
	}
	public void setExampleAnswer2(int exampleAnswer2) {
		this.exampleAnswer2 = exampleAnswer2;
	}
	public int getExampleAnswer3() {
		return exampleAnswer3;
	}
	public void setExampleAnswer3(int exampleAnswer3) {
		this.exampleAnswer3 = exampleAnswer3;
	}
	public int getExampleAnswer4() {
		return exampleAnswer4;
	}
	public void setExampleAnswer4(int exampleAnswer4) {
		this.exampleAnswer4 = exampleAnswer4;
	}
	public int getExampleAnswer5() {
		return exampleAnswer5;
	}
	public void setExampleAnswer5(int exampleAnswer5) {
		this.exampleAnswer5 = exampleAnswer5;
	}
	public int getExampleAnswerJu() {
		return exampleAnswerJu;
	}
	public void setExampleAnswerJu(int exampleAnswerJu) {
		this.exampleAnswerJu = exampleAnswerJu;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getCurriculumId() {
		return curriculumId;
	}
	public void setCurriculumId(int curriculumId) {
		this.curriculumId = curriculumId;
	}
	public String getCurriculumName() {
		return curriculumName;
	}
	public void setCurriculumName(String curriculumName) {
		this.curriculumName = curriculumName;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public Date getCurriculumStartDate() {
		return curriculumStartDate;
	}
	public void setCurriculumStartDate(Date curriculumStartDate) {
		this.curriculumStartDate = curriculumStartDate;
	}
	public Date getCurriculumEndDate() {
		return curriculumEndDate;
	}
	public void setCurriculumEndDate(Date curriculumEndDate) {
		this.curriculumEndDate = curriculumEndDate;
	}
	public int getIsComplete() {
		return isComplete;
	}
	public void setIsComplete(int isComplete) {
		this.isComplete = isComplete;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSubjectAvg() {
		return subjectAvg;
	}
	public void setSubjectAvg(int subjectAvg) {
		this.subjectAvg = subjectAvg;
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
	public int getTestTargetId() {
		return testTargetId;
	}
	public void setTestTargetId(int testTargetId) {
		this.testTargetId = testTargetId;
	}
	public int getQuizScore() {
		return quizScore;
	}
	public void setQuizScore(int quizScore) {
		this.quizScore = quizScore;
	}
	
	
	
	
	
	
}
