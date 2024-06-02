package com.yedam.app.yedam_post.service;

import lombok.Data;

@Data
public class BoardFilesVO {
	private int boardfileId;
	private int boardId;
	private int postId;
	private String boardfileName;
	private long boardfileSize;
	private String boardfileLocation;
	private String boardfileExt;
	
    private boolean exists;

    public boolean isExists() {
        return exists;
    }
    public void setExists(boolean exists) {
        this.exists = exists;
    }
	
	public int getBoardfileId() {
		return boardfileId;
	}
	public void setBoardfileId(int boardfileId) {
		this.boardfileId = boardfileId;
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
	public String getBoardfileName() {
		return boardfileName;
	}
	public void setBoardfileName(String boardfileName) {
		this.boardfileName = boardfileName;
	}
	public long getBoardfileSize() {
		return boardfileSize;
	}
	public void setBoardfileSize(long fileSize) {
		this.boardfileSize = fileSize;
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
	
	
}
