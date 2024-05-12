package com.yedam.app.yedam_post.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class Comment {
	
	private int commentId;
    private int postId;
    private int parentId; // null이면 일반 댓글, 값이 있으면 대댓글
    private int userId;
    private String content;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
    private String writer;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updateDate;
    private List<Comment> replies; // 대댓글 리스트 추가
}
