package com.yedam.app.yedam_post.service;

import java.util.List;

public interface PostService {
	
	public int createPost(Post post);
	
    Post getPostById(int postId);
    
    List<Post> getAllPosts();
    
    public int updatePost(Post post);
    
    public int deletePost(int postId);
    
    public int createComment(Comment comment);
    
    List<Comment> getCommentsByPostId(int postId);
    
    List<Comment> getRepliesByParentId(int parentId);
    
    public int updateComment(Comment comment);
    
    public int deleteComment(int commentId);
    
}

