package com.yedam.app.yedam_homework.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.app.yedam_homework.service.CommentVO;
import com.yedam.app.yedam_homework.service.ReplyVO;

public interface HomeWorkReplyMapper {
	// 댓글등록
	public int insertReply(ReplyVO replyVO);

	// 댓글 삭제
	public int deleteReply(int replyId);
	
	// 댓글 수정
	public int updateReply(ReplyVO replyVO);

	// 댓글 조회
	public List<ReplyVO> selectReplyAll(int homeworkTargetId);

	// 대댓글 조회
	public List<CommentVO> selectComment(int replyId);

	// 대댓글 등록
	public int insertComment(CommentVO comment);

	// 대댓글 삭제
	public int deleteComment(int comment);

}
