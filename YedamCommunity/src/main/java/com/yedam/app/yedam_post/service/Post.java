package com.yedam.app.yedam_post.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Post {
	
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
    
    private int replyId;
    private String replyContent;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date replyDate;
    private String replyWriter;
    
    private int commnetId;
    private String commentContent;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date commentDate;
    private String commentWriter;
    
    private String boardfileName;
 
}
