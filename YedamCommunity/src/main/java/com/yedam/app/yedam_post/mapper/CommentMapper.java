package com.yedam.app.yedam_post.mapper;

import java.util.List;

import com.yedam.app.yedam_post.service.Comment;

public interface CommentMapper {
	
	  // 대댓글 조회
	public List<Comment> getComments(int postId);
	
	  // 대댓글 등록
    public int insertComment(Comment comment);
	
	  // 대댓글 삭제
    public int deleteComment(int commentId);
}
