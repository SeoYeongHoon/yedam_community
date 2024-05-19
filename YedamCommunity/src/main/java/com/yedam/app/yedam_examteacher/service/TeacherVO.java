package com.yedam.app.yedam_examteacher.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TeacherVO {
	private int userId;
	
	// 시험문제 출력
	private int testId;
	private String testName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date testDate;
	private Date testTime;
	// 문제저장소
	private int quizId;
	private String quizContent;
	private String quizContentJu;
	private String quizSolution;
	private String quizSolutionJu;
	private String quizAnswer;
	
	private String textContent;
	private String textContent2;
	private String textContent3;
	private String textContent4;
	private String textContent5;
	private String textContentJu;
	private int exampleNum;
	
	private int subjectId;
	private String subjectName;
	
	private int curriculumId;
	private String curriculumName;
	private String name;
	private int classId;
	private Date curriculumStartDate;
	private Date curriculumEndDate;
	private int isComplete;
	
	private int subjectAvg;
}
