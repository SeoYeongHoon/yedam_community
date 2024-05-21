package com.yedam.app.yedam_homework.service;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ReplyVO {
	private int replyId;
	private String replyContent;
	private Date replyDate;
	private Date updateDate;
	private String replyWriter;
	private int homeworkTargetId;
	private List<CommentVO> commentList;
}