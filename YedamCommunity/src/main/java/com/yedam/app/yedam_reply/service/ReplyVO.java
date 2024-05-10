package com.yedam.app.yedam_reply.service;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	private int replyId;
	private int postId;
	private String replyContent;
	private Date replyDate;
	private Date updateDate;
	private String replyWriter;
}
