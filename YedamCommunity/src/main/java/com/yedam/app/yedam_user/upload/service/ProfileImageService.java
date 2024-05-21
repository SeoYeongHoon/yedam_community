package com.yedam.app.yedam_user.upload.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileImageService {
	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles);
	
	public ProfileImageVO getProfileImageByLogId(String id);
}
