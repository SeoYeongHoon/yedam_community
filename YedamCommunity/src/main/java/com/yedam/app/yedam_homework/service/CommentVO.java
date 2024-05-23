package com.yedam.app.yedam_homework.service;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class CommentVO {
	private int commentId;
	private int replyId;
	private String commentWriter;
	private String commentContent;
	private Date commentDate;
	private List<CommentVO> comments;
}
