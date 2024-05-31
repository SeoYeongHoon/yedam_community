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

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getCurriculumImg() {
		return curriculumImg;
	}

	public void setCurriculumImg(String curriculumImg) {
		this.curriculumImg = curriculumImg;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
}
