package com.yedam.app.yedam_homework.upload.service;

import lombok.Data;

@Data
public class HomeWorkFilesVO {

	private int homeworkfileId;
	private String homeworkfileName;
	private int homeworkfileSize;
	private String homeworkfileLocation;
	private String homeworkfileExt;
	private int replyId;
}
