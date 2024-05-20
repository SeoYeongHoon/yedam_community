package com.yedam.app.yedam_post.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class Reply {
	
	private int replyId;
	private int boardId;
    private int postId;
    private String replyContent;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date replyDate;
    private String replyWriter;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updateDate;
    
    // 대댓글을 끌고올려는곳
    private List<Comment> comments;
    
}
