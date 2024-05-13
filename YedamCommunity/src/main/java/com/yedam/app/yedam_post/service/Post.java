package com.yedam.app.yedam_post.service;

import java.util.Date;
import java.util.List;

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
    private List<BoardFiles> boardfiles; // 파일들
    private List<Reply> replies; // 댓글 리스트 추가
    private List<Comment> comments; // 대댓글 리스트 추가
 
}
