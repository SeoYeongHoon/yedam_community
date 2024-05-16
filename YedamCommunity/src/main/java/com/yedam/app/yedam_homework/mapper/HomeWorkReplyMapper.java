package com.yedam.app.yedam_homework.mapper;

import com.yedam.app.yedam_homework.service.HomeWorkVO;

public interface HomeWorkReplyMapper {
	//댓글등록
	public int insertReply(HomeWorkVO homeworkVO);
	
	// 댓글 조회
	public HomeWorkVO selectReply (HomeWorkVO homeworkVO);
}
