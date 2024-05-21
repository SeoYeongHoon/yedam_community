package com.yedam.app.yedam_homework.upload.service;

import lombok.Data;

@Data
public class HomeWorkFileVO {

	private int homeworkfileId;
	private String homeworkfileName;
	private int homeworkfileSize;
	private String homeworkfileLocation;
	private String homeworkfileExt;
	private String downloadLocation;
	private int replyId;
	private int homeworkId;
	
}
