package com.yedam.app.yedam_post.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class Reply {
	
	private int replyId;
    private int postId;
    private int userId;
    private String replyContent;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date replyDate;
    private String writer;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updateDate;
    private List<Comment> comments; // 대댓글 리스트 추가
    
    
}
