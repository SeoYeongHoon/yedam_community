package com.yedam.app.yedam_post.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class PostReplyVO {
	
	private int replyId;
	private int boardId;
    private int postId;
    private String replyContent;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date replyDate;
    private String replyWriter;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updateDate;
    
    // 내가 쓴 댓글, 대댓글 불러오기 위함
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date addDate;
    private String addContent;
    private int addId;
    
    private int commentId;
    private String commentContent;
    
    
    
    public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public int getReplyId() {
		return replyId;
	}

	// 대댓글을 끌고올려는곳
    private List<PostCommentVO> comments;

    public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	
	public void setAddId(int addId) {
		this.addId = addId;
	}

	public int getAddId() {
		return addId;
	}
	
	public String getAddContent() {
		return addContent;
	}
	
	public void setAddContent(String addContent) {
		this.addContent = addContent;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<PostCommentVO> getComments() {
		return comments;
	}

	public void setComments(List<PostCommentVO> comments) {
		this.comments = comments;
	}

    
    
}
