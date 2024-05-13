package com.yedam.app.yedam_post.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Comment {
	
	  private int commentId;
    private int replyId;
    private String commentContent;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date commentDate;
    private String commentWriter;
}
