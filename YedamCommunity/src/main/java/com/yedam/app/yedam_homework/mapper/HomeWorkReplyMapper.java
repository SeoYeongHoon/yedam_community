package com.yedam.app.yedam_homework.mapper;

import java.util.List;


import com.yedam.app.yedam_homework.service.CommentVO;
import com.yedam.app.yedam_homework.service.HomeWorkVO;
import com.yedam.app.yedam_homework.service.ReplyVO;

public interface HomeWorkReplyMapper {
	// 댓글등록
	public int insertReply(ReplyVO replyVO);

	// 댓글 조회
	public List<ReplyVO> selectReplyAll(int homeworkTargetId);

	// 대댓글 조회
	public List<CommentVO> selectComment(int replyId);

	// 대댓글 등록
	public int insertComment(CommentVO comment);
}
