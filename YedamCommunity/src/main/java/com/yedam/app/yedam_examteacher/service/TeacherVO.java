package com.yedam.app.yedam_examteacher.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TeacherVO {
	private int userId;
	
	// tests 테이블
	private int testId;
	private String testName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
	
}
