package com.yedam.app.yedam_post.service;

import java.util.Date;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class PostVO {
	
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
    private String keyword;
    
    //댓글
    private int replyId;
    private String replyContent;
    private int replyCount;
    //
    private int commentCount;
    //좋아요
    private int totalCount;
    //파일
	private int boardfileId;
    private String boardfileName;
    private long boardfileSize;
    private String boardfileLocation;
    private String boardfileExt;
    
    //--------------------------------------------
    // 추천 출력을 위한 리스트
    //--------------------------------------------
    private List<BoardLikeVO> boardlike;
    
    //--------------------------------------------
    // 댓글 출력을 위한 리스트
    //--------------------------------------------
    private List<PostReplyVO> replies;
    
    //--------------------------------------------
    // 대댓글을 출력하기 위한 리스트
    //--------------------------------------------
    private List<PostCommentVO> comments;
    
    //--------------------------------------------
    // 첨부 파일 리스트 추가
    //--------------------------------------------
    private List<BoardFilesVO> boardFiles;
    

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getPostView() {
		return postView;
	}

	public void setPostView(int postView) {
		this.postView = postView;
	}

	public int getPostLike() {
		return postLike;
	}

	public void setPostLike(int postLike) {
		this.postLike = postLike;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getBoardfileId() {
		return boardfileId;
	}

	public void setBoardfileId(int boardfileId) {
		this.boardfileId = boardfileId;
	}

	public String getBoardfileName() {
		return boardfileName;
	}

	public void setBoardfileName(String boardfileName) {
		this.boardfileName = boardfileName;
	}

	public long getBoardfileSize() {
		return boardfileSize;
	}

	public void setBoardfileSize(long boardfileSize) {
		this.boardfileSize = boardfileSize;
	}

	public String getBoardfileLocation() {
		return boardfileLocation;
	}

	public void setBoardfileLocation(String boardfileLocation) {
		this.boardfileLocation = boardfileLocation;
	}

	public String getBoardfileExt() {
		return boardfileExt;
	}

	public void setBoardfileExt(String boardfileExt) {
		this.boardfileExt = boardfileExt;
	}

	public List<BoardLikeVO> getBoardlike() {
		return boardlike;
	}

	public void setBoardlike(List<BoardLikeVO> boardlike) {
		this.boardlike = boardlike;
	}

	public List<PostReplyVO> getReplies() {
		return replies;
	}

	public void setReplies(List<PostReplyVO> replies) {
		this.replies = replies;
	}

	public List<PostCommentVO> getComments() {
		return comments;
	}

	public void setComments(List<PostCommentVO> comments) {
		this.comments = comments;
	}

	public List<BoardFilesVO> getBoardFiles() {
		return boardFiles;
	}

	public void setBoardFiles(List<BoardFilesVO> boardFiles) {
		this.boardFiles = boardFiles;
	}
    public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	
    
}
