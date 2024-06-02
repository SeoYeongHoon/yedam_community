package com.yedam.app.yedam_user.upload.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.yedam_user.upload.service.ProfileImageService;

@Service
public class ProfileImageServiceImpl implements ProfileImageService {
	
	@Value("${file.upload.path}")
	private String uploadPath;

	@Override
	@ResponseBody
	public List<String> uploadFile(MultipartFile[] uploadFiles) {
		
		List<String> imageList = new ArrayList<>();
		
		for (MultipartFile uploadFile : uploadFiles) {
			System.err.println("업로드파일: " + uploadFiles);
			
			String originalName = uploadFile.getOriginalFilename();
			System.err.println("파일 원래 이름: " + originalName);
			
			String fileName = originalName.substring(originalName.lastIndexOf("//") + 1);
			System.err.println("바뀐 파일 이름: " + fileName);
			
			String fileExt = originalName.substring(originalName.lastIndexOf(".") + 1);
			System.err.println("파일 확장자: " + fileExt);
			
			String folderPath = makeFolder();
			System.err.println("날짜 폴더 생성, 경로: " + folderPath);
			
			String uuid = UUID.randomUUID().toString();
			System.err.println("시간기준 랜덤값: " + uuid);
			
			String uploadFileName = folderPath + File.separator + uuid + "_" + fileName;
			System.err.println("파일이름 중간에 _ 넣어서 구분: " + uploadFileName);
			
			String downloadFileName = uuid + "_" + fileName;
			
			String saveName = uploadPath + uploadFileName;
			System.err.println("파일 저장시 이름: " + saveName);
			
			// 특정 경로의 파일 정보 가져오는 메소드
			Path savePath = Paths.get(saveName);
			
			try {
				// uploadFile에 파일을 업로드하는 메소드
				uploadFile.transferTo(savePath);
				imageList.add(setFilePath(uploadFileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.err.println("파일크기: " + uploadFile.getSize());
			
			System.err.println("이미지 리스트: " + imageList);

		}
		
		return imageList;
	}

	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		String folderPath = str.replace("/", File.separator);
		
		File uploadPathFolder = new File(uploadPath, folderPath);
		
		if (uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdir();
		}
		return folderPath;
	}
	
	private String setFilePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}

}
