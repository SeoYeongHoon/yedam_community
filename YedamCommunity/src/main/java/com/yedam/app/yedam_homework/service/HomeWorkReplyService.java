package com.yedam.app.yedam_homework.service;

import java.util.List;

public interface HomeWorkReplyService {

	//댓글 등록
	public int replyInsert(HomeWorkVO homeworkVO);
	
	//댓글 조회
	public List<ReplyVO> replyList(HomeWorkVO homeworkVO);
	
	//대댓글 조회
	public List<CommentVO> commentList(ReplyVO replyId);
	
	//대댓글 등록
	public int commentInsert (HomeWorkVO homeworkVO);
	
	
}
