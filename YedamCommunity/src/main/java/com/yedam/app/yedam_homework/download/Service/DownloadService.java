package com.yedam.app.yedam_homework.download.Service;

import org.springframework.http.ResponseEntity;

public interface DownloadService {
	public ResponseEntity<Object> downloadFile( String downloadLocation);

}
