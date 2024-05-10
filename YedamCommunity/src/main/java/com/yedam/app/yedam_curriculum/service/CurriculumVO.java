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
	private String classId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date curriculumEndDate;
	
}
