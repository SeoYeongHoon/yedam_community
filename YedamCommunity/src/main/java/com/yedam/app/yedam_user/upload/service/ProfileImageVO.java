package com.yedam.app.yedam_user.upload.service;

import lombok.Data;

@Data
public class ProfileImageVO {
	private int profileImageId;
	private String profileImageName;
	private int profileImageSize;
	private String profileImageLocation;
	private String profileImageExt;
	private String downloadLocation;
	private String id;
}
