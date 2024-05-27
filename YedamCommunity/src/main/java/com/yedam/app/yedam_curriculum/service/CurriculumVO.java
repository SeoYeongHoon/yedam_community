package com.yedam.app.yedam_curriculum.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CurriculumVO {

	private int curriculumId;
	private String curriculumName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date curriculumStartDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date curriculumEndDate;
	private int isComplete;
	private int classId;
	private String curriculumImg;
	
	//과목명
	private int subjectId;
	private String subjectName;
	
	//회원
	private int userId;
}
