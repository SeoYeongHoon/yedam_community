package com.yedam.app.yedam_post.service;

import java.util.Date;

public class VoteUserVO {

	private int voteUserId;
	private Date voteDate;
	private int voteItemId;
	private int userId;
	public int getVoteUserId() {
		return voteUserId;
	}
	public void setVoteUserId(int voteUserId) {
		this.voteUserId = voteUserId;
	}
	public Date getVoteDate() {
		return voteDate;
	}
	public void setVoteDate(Date voteDate) {
		this.voteDate = voteDate;
	}
	public int getVoteItemId() {
		return voteItemId;
	}
	public void setVoteItemId(int voteItemId) {
		this.voteItemId = voteItemId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
