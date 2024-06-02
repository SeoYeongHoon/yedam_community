package com.yedam.app.yedam_homework.upload.service;

import lombok.Data;

@Data
public class HomeWorkFileVO {

	private int homeworkfileId;
	private String homeworkfileName;
	private int homeworkfileSize;
	private String homeworkfileLocation;
	private String downloadLocation;
	private String homeworkfileExt;
	private int homeworkId;
	public int getHomeworkfileId() {
		return homeworkfileId;
	}
	public void setHomeworkfileId(int homeworkfileId) {
		this.homeworkfileId = homeworkfileId;
	}
	public String getHomeworkfileName() {
		return homeworkfileName;
	}
	public void setHomeworkfileName(String homeworkfileName) {
		this.homeworkfileName = homeworkfileName;
	}
	public int getHomeworkfileSize() {
		return homeworkfileSize;
	}
	public void setHomeworkfileSize(int homeworkfileSize) {
		this.homeworkfileSize = homeworkfileSize;
	}
	public String getHomeworkfileLocation() {
		return homeworkfileLocation;
	}
	public void setHomeworkfileLocation(String homeworkfileLocation) {
		this.homeworkfileLocation = homeworkfileLocation;
	}
	public String getDownloadLocation() {
		return downloadLocation;
	}
	public void setDownloadLocation(String downloadLocation) {
		this.downloadLocation = downloadLocation;
	}
	public String getHomeworkfileExt() {
		return homeworkfileExt;
	}
	public void setHomeworkfileExt(String homeworkfileExt) {
		this.homeworkfileExt = homeworkfileExt;
	}
	public int getHomeworkId() {
		return homeworkId;
	}
	public void setHomeworkId(int homeworkId) {
		this.homeworkId = homeworkId;
	}
	
	
	
	
}
