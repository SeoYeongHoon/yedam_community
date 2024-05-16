package com.yedam.app.yedam_homework.upload.mapper;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public interface HomeWorkFileMapper {
	
	 //과제 파일 등록
	public int insertHomeWorkFile(@RequestPart MultipartFile[] uploadFiles);
}
