package com.yedam.app.yedam_post.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ReportVO {
	 private int reportId;
	 private String reporter;
	 private int process;
	 private int reportType;
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date reportDate;
	 private int boardId;
	 private int postId;
	 
	 
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public int getProcess() {
		return process;
	}
	public void setProcess(int process) {
		this.process = process;
	}
	public int getReportType() {
		return reportType;
	}
	public void setReportType(int reportType) {
		this.reportType = reportType;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
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
