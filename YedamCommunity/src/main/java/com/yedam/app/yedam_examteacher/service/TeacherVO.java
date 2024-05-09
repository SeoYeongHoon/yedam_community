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
	private int subjectId;
	private String quizContent;
	private String quizSolution;
	private String quizAnswer;
	
	private String textContent;
	
	
}
