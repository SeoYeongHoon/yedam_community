package com.yedam.app.yedam_homework.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class HomeWorkVO {

	// 과제
	private int homeworkId;
	private String homeworkContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date homeworkDate;
	private String homeworkTitle;
	private int curriculumId;
	private int subjectId;
	private String classId;
	
	// 과목
	private String subjectName;
	
	//댓글
	private int replyId;
	private String replyContent;
	private Date replyDate;
	private Date updateDate;
	private String replyWriter;
	private int homeworkTargetId;
	
	//대댓글
	private int commentId;
	private String commentWriter;
	private String commentContent;
	private Date commentDate;
	
	//댓글목록
	private List<ReplyVO> replyList;
	//대댓글목록
	private List<CommentVO> commentList;
}
