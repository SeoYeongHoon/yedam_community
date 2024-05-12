package com.yedam.app.yedam_homework.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class HomeWorkVO {

	// 과제
	private int homeworkId;
	private String homeworkContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date homeworkDate;
	private String homeworkTitle;
	private int curriculumId;
	private int subjectId;
	private String classId;
	
}
