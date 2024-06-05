package com.yedam.app.yedam_post.service;

import lombok.Data;

@Data
public class VoteItemVO {

	private int voteItemId;
	private int voteSeq;
	private String voteItemName;
	private int voteCount;
	private int voteId;
	
	public int getVoteItemId() {
		
		return voteItemId;
	}
	public void setVoteItemId(int voteItemId) {
		this.voteItemId = voteItemId;
	}
	public int getVoteSeq() {
		return voteSeq;
	}
	public void setVoteSeq(int voteSeq) {
		this.voteSeq = voteSeq;
	}
	public String getVoteItemName() {
		return voteItemName;
	}
	public void setVoteItemName(String voteItemName) {
		this.voteItemName = voteItemName;
	}
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	public int getVoteId() {
		return voteId;
	}
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}
	
	
}
