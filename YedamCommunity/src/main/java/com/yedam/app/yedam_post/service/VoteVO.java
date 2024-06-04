package com.yedam.app.yedam_post.service;

import java.util.Date;

import lombok.Data;

@Data
public class VoteVO {
	
	private int voteId;
	private Date voteendDate;
	private int isProcessing;
	private int boardId;
	private int postId;
	public int getVoteId() {
		return voteId;
	}
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}
	public Date getVoteendDate() {
		return voteendDate;
	}
	public void setVoteendDate(Date voteendDate) {
		this.voteendDate = voteendDate;
	}
	public int getIsProcessing() {
		return isProcessing;
	}
	public void setIsProcessing(int isProcessing) {
		this.isProcessing = isProcessing;
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
	
	
}
