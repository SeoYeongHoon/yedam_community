package com.yedam.app.yedam_homework.service;

public interface HomeWorkReplyService {

	//댓글 등록
	public int replyInsert(HomeWorkVO homeworkVO);
	
	//댓글 조회
	public HomeWorkVO replyInfo(HomeWorkVO homeworkVO);
	
	
}
