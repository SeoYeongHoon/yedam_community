package com.yedam.app.yedam_homework.upload.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.yedam_homework.upload.service.HomeWorkFileService;

@Controller
public class UploadController {

	@Autowired
	HomeWorkFileService homeworkfileService;
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	
	@PostMapping("/uploadsAjax")
	@ResponseBody
	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles){
		return homeworkfileService.uploadFile(uploadFiles);
	}
}
