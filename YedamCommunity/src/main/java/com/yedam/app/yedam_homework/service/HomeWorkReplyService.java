package com.yedam.app.yedam_homework.service;

import java.util.List;


public interface HomeWorkReplyService {

	//댓글 등록
	public int replyInsert(ReplyVO replyId);
	
	//댓글 조회
	public List<ReplyVO> replyList(int homeworkTargetId);
	
	//대댓글 조회
	public List<CommentVO> commentList(int replyId);
	
	//대댓글 등록
	public int commentInsert (CommentVO comment);
	
	
}
