package com.yedam.app.yedam_post.mapper;

import java.util.List;

import com.yedam.app.yedam_post.service.Reply;

public interface ReplyMapper {

	public List<Reply> getRepliesByPostId(int postId);

	public List<Reply> getCommentsByParentId(int parentId);

	public int insertReply(Reply reply);

	public int deleteReply(int replyId);

	public int deleteRepliesByPostId(int postId);

	public int updateReply(Reply reply);
}
