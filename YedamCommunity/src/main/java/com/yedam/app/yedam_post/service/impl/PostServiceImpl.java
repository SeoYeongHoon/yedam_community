package com.yedam.app.yedam_post.service.impl;

import java.util.List;

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
    public int updatePost(Post post) {
        return postMapper.updatePost(post);
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
    public Post getPostDetails(int postId) {
        return postMapper.getPostDetails(postId);
    }

	@Override
	public int deletePost(int postId) {
		// TODO Auto-generated method stub
		return 0;
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


}
