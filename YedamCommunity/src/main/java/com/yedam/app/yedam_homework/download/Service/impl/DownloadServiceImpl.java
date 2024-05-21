package com.yedam.app.yedam_homework.download.Service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_homework.download.Service.DownloadService;


@Service
public class DownloadServiceImpl implements DownloadService{
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Override
	public ResponseEntity<Object> downloadFile(String downloadLocation) {

	
			return null;
		
	}

}
