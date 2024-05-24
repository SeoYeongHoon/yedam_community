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
    
    private int boardfileId;
    private String boardfileName;
    private long boardfileSize;
    private String boardfileLocation;
    private String boardfileExt;
    
    //--------------------------------------------
    // 추천 출력을 위한 리스트
    //--------------------------------------------
    private List<BoardLike> boardlike;
    
    //--------------------------------------------
    // 댓글 출력을 위한 리스트
    //--------------------------------------------
    private List<Reply> replies;
    
    //--------------------------------------------
    // 대댓글을 출력하기 위한 리스트
    //--------------------------------------------
    private List<Comment> comments;
    
    //--------------------------------------------
    // 첨부 파일 리스트 추가
    //--------------------------------------------
    private List<BoardFiles> boardFiles;

}
