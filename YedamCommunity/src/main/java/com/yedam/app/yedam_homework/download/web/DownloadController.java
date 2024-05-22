package com.yedam.app.yedam_homework.download.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.yedam_homework.download.Service.DownloadService;

@RestController // responseBody랑 controller랑 합한거
public class DownloadController {
	
	@Autowired
	DownloadService downloadService;
	
	@GetMapping("/download/{downloadLocation}")
	public ResponseEntity<Object> download (@PathVariable String downloadLocation){
		return downloadService.downloadFile(downloadLocation);
	}
}
