package com.yedam.app.yedam_post.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.yedam_post.mapper.PostMapper;
import com.yedam.app.yedam_post.mapper.ReplyMapper;
import com.yedam.app.yedam_post.service.Comment;
import com.yedam.app.yedam_post.service.Post;
import com.yedam.app.yedam_post.service.PostService;
import com.yedam.app.yedam_post.service.Reply;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostMapper postMapper;

	@Autowired
	private ReplyMapper replyMapper;

	@Override
	@Transactional
	public int createPost(Post post) {
		return postMapper.insertPost(post);
	}

	@Override
	public List<Post> getAllPosts() {
		return postMapper.getAllPosts();
	}

	@Override
	@Transactional
	public int createReply(Reply reply) {
		return replyMapper.insertReply(reply);
	}

	@Override
	public List<Reply> getRepliesByPostId(int postId) {
		return replyMapper.getRepliesByPostId(postId);
	}

	@Override
	@Transactional
	public int updateReply(Reply reply) {
		return replyMapper.updateReply(reply);
	}

	@Override
	@Transactional
	public int deleteReply(int replyId) {
		return replyMapper.deleteReply(replyId);
	}

	@Override
	public Post getPostDetails(Post post) {
		return postMapper.getPostDetails(post);
	}

	@Override
	public Post getPostDetails(int postId) {
		return postMapper.getPostDetails(postId);
	}

	@Override
	public int createComment(Comment comment) {
		return postMapper.insertComment(comment);
	}

	@Override
	public int updateComment(Comment comment) {
		return postMapper.updateComment(comment);
	}

	@Override
	public int deleteComment(int comment) {
		return postMapper.deleteComment(comment);
	}

	@Override
	public Map<String, Object> PostDelete(Post post) {
		Map<String, Object> map = new HashMap<>();
		int result = postMapper.deletePost1(post.getPostId());
		if(result == 1) {
			map.put("postId", post.getPostId());
		}
		return map;
	}

	@Override
	public Map<String, Object> PostUpdate(Post post) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = postMapper.updatePost(post);
		
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", post);
		return map;
	}

}
