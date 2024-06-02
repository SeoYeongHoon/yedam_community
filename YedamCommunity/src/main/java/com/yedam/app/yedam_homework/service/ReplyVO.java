package com.yedam.app.yedam_homework.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.yedam.app.yedam_homework.upload.service.ReplyFileVO;

import lombok.Data;

@Data
public class ReplyVO {
	//댓글
	private int replyId;
	private String replyContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date replyDate;
	private Date updateDate;
	private String replyWriter;
	private int homeworkTargetId;
	private List<ReplyVO> replies;
	
	//대댓글
	private int commentId;
	private String commentWriter;
	private String commentContent;
	private Date commentDate;
	private List<CommentVO> comments;
	
	//다운로드 경로
	private String downloadLocation;

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public int getHomeworkTargetId() {
		return homeworkTargetId;
	}

	public void setHomeworkTargetId(int homeworkTargetId) {
		this.homeworkTargetId = homeworkTargetId;
	}

	public List<ReplyVO> getReplies() {
		return replies;
	}

	public void setReplies(List<ReplyVO> replies) {
		this.replies = replies;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentWriter() {
		return commentWriter;
	}

	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public List<CommentVO> getComments() {
		return comments;
	}

	public void setComments(List<CommentVO> comments) {
		this.comments = comments;
	}

	public String getDownloadLocation() {
		return downloadLocation;
	}

	public void setDownloadLocation(String downloadLocation) {
		this.downloadLocation = downloadLocation;
	}
	
	
}
