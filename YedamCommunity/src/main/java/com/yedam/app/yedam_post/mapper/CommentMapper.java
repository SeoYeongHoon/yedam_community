package com.yedam.app.yedam_post.mapper;

import java.util.List;

import com.yedam.app.yedam_post.service.Comment;

public interface CommentMapper {

	public List<Comment> getCommentsByPostId(int postId);

	public List<Comment> getRepliesByParentId(int parentId);

	public int insertComment(Comment comment);

	public int deleteComment(int commentId);

	public int deleteCommentsByPostId(int postId);

	public int updateComment(Comment comment);
}
