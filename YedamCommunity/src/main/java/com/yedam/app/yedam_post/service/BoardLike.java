package com.yedam.app.yedam_post.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardLike {
	
	private int likeId;
	private int postId;
	private int userId;
    private int postLike;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date likeDate;
    private int likeStatus;
}
