package com.yedam.app.yedam_homework.upload.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.yedam_homework.service.HomeWorkTargetVO;
import com.yedam.app.yedam_homework.service.HomeWorkVO;

public interface HomeWorkFileService {
	// 과제등록 파일 업로드
	public List<String> homeworkUploadFile(@RequestPart MultipartFile[] uploadFiles, HomeWorkTargetVO homeworktargetVO);

	// 과제 파일 조회
	public List<HomeWorkFileVO> homeworkfileList(HomeWorkVO homeworkVO);
	
	// 파일id로 파일 조회
	//public HomeWorkFileVO homeworkfile(HomeWorkFileVO homeworkfileVO);

	// 댓글등록 파일 업로드
	public List<String> replyUploadFile(@RequestPart MultipartFile[] uploadFiles, int replyId);
	
	// 댓글 파일 조회
	public List<ReplyFileVO> replyfileList(int replyId);
	
	// 파일 삭제
	public Map<String, Object> deleteFile(HomeWorkFileVO homeworkfileVO);
}
