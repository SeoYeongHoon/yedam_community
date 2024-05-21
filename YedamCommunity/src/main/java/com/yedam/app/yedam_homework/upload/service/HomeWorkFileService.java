package com.yedam.app.yedam_homework.upload.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.yedam_homework.service.HomeWorkTargetVO;
import com.yedam.app.yedam_homework.service.HomeWorkVO;




public interface HomeWorkFileService {

	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles, HomeWorkTargetVO homeworktargetVO);
}
