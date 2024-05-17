package com.yedam.app.yedam_homework.service;

import java.util.List;

public interface HomeWorkReplyService {

	//댓글 등록
	public int replyInsert(HomeWorkVO homeworkVO);
	
	//댓글 조회
	public List<HomeWorkVO> replyInfo(HomeWorkVO homeworkVO);
	
	//대댓글 조회
	public HomeWorkVO commentList(HomeWorkVO homeworkVO);
	
	//대댓글 등록
	public int commentInsert (HomeWorkVO homeworkVO);
	
	
}
