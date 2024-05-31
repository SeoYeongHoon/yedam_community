package com.yedam.app.yedam_examteacher.service;

import lombok.Data;

@Data
public class QuizVO {
	String[] quizContent;
	int [] quizId;
	int [] quizScore;
	String[] subjectName;
	int curriculumId;
  int testId;
	int [] userId;

	public String[] getQuizContent() {
		return quizContent;
	}
	public void setQuizContent(String[] quizContent) {
		this.quizContent = quizContent;
	}
	public int[] getQuizId() {
		return quizId;
	}
	public void setQuizId(int[] quizId) {
		this.quizId = quizId;
	}
	public int[] getQuizScore() {
		return quizScore;
	}
	public void setQuizScore(int[] quizScore) {
		this.quizScore = quizScore;
	}
	public String[] getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String[] subjectName) {
		this.subjectName = subjectName;
	}
	public int getCurriculumId() {
		return curriculumId;
	}
	public void setCurriculumId(int curriculumId) {
		this.curriculumId = curriculumId;
	}

}
