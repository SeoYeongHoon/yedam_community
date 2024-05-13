package com.yedam.app.yedam_board.service;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardVO {

	private int postId;
	private int boardId;
	private int userId;
	private String title;
	private String postContent;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createDate;
	private String writer;
	private int postView;
	private int postLike;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateDate;
	
	private String boardfileName;



}
