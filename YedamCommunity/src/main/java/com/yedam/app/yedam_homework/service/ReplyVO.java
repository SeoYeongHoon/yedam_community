package com.yedam.app.yedam_homework.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.yedam.app.yedam_homework.upload.service.ReplyFileVO;

import lombok.Data;

@Data
public class ReplyVO {
	private int replyId;
	private String replyContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date replyDate;
	private Date updateDate;
	private String replyWriter;
	private int homeworkTargetId;
	private List<CommentVO> commentList;
	private List<ReplyFileVO> replyfileList;
	//다운로드 경로
	private String downloadLocation;
}
