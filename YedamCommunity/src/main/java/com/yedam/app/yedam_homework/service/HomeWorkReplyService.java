package com.yedam.app.yedam_homework.service;

import java.util.List;
import java.util.Map;

public interface HomeWorkReplyService {

	// 댓글 등록
	public int replyInsert(ReplyVO replyId);

	// 댓글 삭제
	public int replyDelete(int replyid);

	// 댓글 수정
	public Map<String, Object> replyUpdate(ReplyVO replyVO);

	// 댓글 조회
	public List<ReplyVO> replyList(int homeworkTargetId);


	// 대댓글 등록
	public int commentInsert(CommentVO comment);

	// 대댓글 수정
	public Map<String, Object> commentUpdate(CommentVO commentVO);

	// 대댓글 삭제
	public int commentDelete(int comment);

}
