package com.yedam.app.yedam_homework.download.Service.impl;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_homework.download.Service.DownloadService;
import com.yedam.app.yedam_homework.upload.mapper.HomeWorkFileMapper;


@Service
public class DownloadServiceImpl implements DownloadService{
	
	@Autowired
	HomeWorkFileMapper homeworkfileMapper;
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Override
	public ResponseEntity<Object> downloadFile(String downloadLocation) {
		
		// 업로드된 파일경로 가져오기
		String path = homeworkfileMapper.selectFileName(downloadLocation);
		try {
			Path filePath = Paths.get(path);
			Resource resource = new InputStreamResource(Files.newInputStream(filePath)); 

			File file = new File(path);
			
			//파일명에서 uuid 제거
			String makeName = URLEncoder.encode(file.getName(),"UTF-8");
			// _기준으로 짜르기
			String NameAry[] = makeName.split("_");
			String DownName = NameAry[1];
			
			//파일생성
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename(DownName).build());

			return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
		}
	}
		
	}


