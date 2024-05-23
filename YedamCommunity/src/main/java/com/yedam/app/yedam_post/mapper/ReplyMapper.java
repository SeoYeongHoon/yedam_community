package com.yedam.app.yedam_post.mapper;

import java.util.List;

import com.yedam.app.yedam_post.service.Post;
import com.yedam.app.yedam_post.service.Reply;

public interface ReplyMapper {

	//--------------------------------------------
	// 댓글 등록
	//--------------------------------------------
	public int insertReply(Reply reply);
	
	//--------------------------------------------
	// 댓글 삭제
	//--------------------------------------------
	public int deleteReply1(int replyId);
	public int deleteReply2(int replyId);
	
	//--------------------------------------------
	// 댓글 조회
	//--------------------------------------------
	public List<Reply> getReplies(int postId);
}
