package com.yedam.app.yedam_post.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.yedam_post.mapper.CommentMapper;
import com.yedam.app.yedam_post.mapper.PostMapper;
import com.yedam.app.yedam_post.service.Comment;
import com.yedam.app.yedam_post.service.Post;
import com.yedam.app.yedam_post.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
    private PostMapper postMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    @Transactional
    public int createPost(Post post) {
        return postMapper.insertPost(post);
    }

    @Override
    public Post getPostById(int postId) {
        Post post = postMapper.getPostById(postId);
        List<Comment> comments = commentMapper.getCommentsByPostId(postId);
        for (Comment comment : comments) {
            List<Comment> replies = commentMapper.getRepliesByParentId(comment.getCommentId());
            comment.setReplies(replies);
        }
        post.setComments(comments);
        return post;
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
    public int deletePost(int postId) {
        commentMapper.deleteCommentsByPostId(postId);
        return postMapper.deletePostById(postId);
    }

    @Override
    @Transactional
    public int createComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(int postId) {
        return commentMapper.getCommentsByPostId(postId);
    }

    @Override
    public List<Comment> getRepliesByParentId(int parentId) {
        return commentMapper.getRepliesByParentId(parentId);
    }

    @Override
    @Transactional
    public int updateComment(Comment comment) {
        return commentMapper.updateComment(comment);
    }

    @Override
    @Transactional
    public int deleteComment(int commentId) {
        return commentMapper.deleteComment(commentId);
    }
}
