package com.yedam.app.yedam_homework.download.Service;

import org.springframework.http.ResponseEntity;

public interface DownloadService {
	//과제 파일 다운로드
	public ResponseEntity<Object> homeworkfileDownloadFile( String downloadLocation);
	//댓글 파일 다운로드
	public ResponseEntity<Object> replyfileDownloadFile( String downloadLocation);

}
