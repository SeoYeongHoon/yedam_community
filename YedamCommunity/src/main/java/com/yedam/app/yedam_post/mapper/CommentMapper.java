package com.yedam.app.yedam_post.mapper;

import java.util.List;

import com.yedam.app.yedam_post.service.PostCommentVO;
import com.yedam.app.yedam_post.service.PostReplyVO;

public interface CommentMapper {
	
	//--------------------------------------------
	// 대댓글 조회
	//--------------------------------------------
	public List<PostCommentVO> getComments(PostReplyVO replyId);
	
	//--------------------------------------------
	// 대댓글 등록
	//--------------------------------------------
    public int insertComment(PostCommentVO commentVO);
    
	//--------------------------------------------
	// 대댓글 삭제
    //--------------------------------------------
    public int deleteComment(int commentId);
}
