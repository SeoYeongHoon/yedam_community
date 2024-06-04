package com.yedam.app.yedam_post.mapper;

import java.util.List;

import com.yedam.app.yedam_homework.service.ReplyVO;
import com.yedam.app.yedam_post.service.PostReplyVO;

public interface ReplyMapper {

	// --------------------------------------------
	// 댓글 등록
	// --------------------------------------------
	public int insertReply(PostReplyVO postreplyVO);

	// --------------------------------------------
	// 댓글 삭제
	// --------------------------------------------
	public int deleteReply1(int replyId);

	public int deleteReply2(int replyId);

	// --------------------------------------------
	// 댓글 수정
	// --------------------------------------------
	public int replyUpdate(PostReplyVO postreplyVO);

	// --------------------------------------------
	// 댓글 조회
	// --------------------------------------------
	public List<PostReplyVO> getReplies(int postId);
	
	// --------------------------------------------
	// 마이페이지 내 댓글 조회
	// --------------------------------------------
	public List<PostReplyVO> getMyReply(int userId);
}
