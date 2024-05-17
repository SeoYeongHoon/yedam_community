package com.yedam.app.yedam_post.mapper;

import java.util.List;

import com.yedam.app.yedam_post.service.Comment;

public interface CommentMapper {
	
	// 대댓글 조회
	public List<Comment> getComments(int postId);
}
