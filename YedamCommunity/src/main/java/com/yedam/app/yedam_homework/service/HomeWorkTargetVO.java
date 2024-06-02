package com.yedam.app.yedam_homework.service;

import lombok.Data;

@Data

public class HomeWorkTargetVO {

	private int homeworkId;
	private int curriculumId;
	public int getHomeworkId() {
		return homeworkId;
	}
	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}
	public int getCurriculumId() {
		return curriculumId;
	}
	public void setCurriculumId(int curriculumId) {
		this.curriculumId = curriculumId;
	}
	
	
}
