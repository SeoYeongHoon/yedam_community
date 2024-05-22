package com.yedam.app.yedam_homework.upload.service;


import lombok.Data;

@Data
public class ReplyFileVO {

	private int replyfileId;
	private String replyfileName;
	private int replyfileSize;
	private String replyfileLocation;
	private String downloadLocation;
	private String replyfileExt;
	private int replyId;
}
