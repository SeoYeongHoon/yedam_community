package com.yedam.app.yedam_homework.service;

import lombok.Data;

@Data
public class HomeWorkFileVO {

	private int homeworkFileId;
	private String homeworkFileName;
	private int homeworkFileSize;
	private String homeworkFileLocation;
	private String homeworkFileExt;
	private int replyId;
}
