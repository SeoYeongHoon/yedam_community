package com.yedam.app.yedam_homework.upload.service;


import lombok.Data;

@Data
public class ReplyFileVO {

	private int replyfileId;
	private String replyfileName;
	private int replyfileSize;
	private String replyfileLocation;
	private String downloadLocation;
	private String replyfileExt;
	private int replyId;
	public int getReplyfileId() {
		return replyfileId;
	}
	public void setReplyfileId(int replyfileId) {
		this.replyfileId = replyfileId;
	}
	public String getReplyfileName() {
		return replyfileName;
	}
	public void setReplyfileName(String replyfileName) {
		this.replyfileName = replyfileName;
	}
	public int getReplyfileSize() {
		return replyfileSize;
	}
	public void setReplyfileSize(int replyfileSize) {
		this.replyfileSize = replyfileSize;
	}
	public String getReplyfileLocation() {
		return replyfileLocation;
	}
	public void setReplyfileLocation(String replyfileLocation) {
		this.replyfileLocation = replyfileLocation;
	}
	public String getDownloadLocation() {
		return downloadLocation;
	}
	public void setDownloadLocation(String downloadLocation) {
		this.downloadLocation = downloadLocation;
	}
	public String getReplyfileExt() {
		return replyfileExt;
	}
	public void setReplyfileExt(String replyfileExt) {
		this.replyfileExt = replyfileExt;
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	
	
}
