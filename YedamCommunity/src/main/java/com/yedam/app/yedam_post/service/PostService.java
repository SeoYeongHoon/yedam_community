package com.yedam.app.yedam_post.service;

import java.util.List;

public interface PostService {
	
	public int createPost(Post post);
    
    List<Post> getAllPosts();
    
    public int updatePost(Post post);
    
    public int deletePost(int postId);
    
    public Post getPostDetails(int postId);
    
    
    public int createReply(Reply reply);
    
    List<Reply> getRepliesByPostId(int postId);
    
    public int updateReply(Reply reply);
    
    public int deleteReply(int replyId);
    
    
    public int createComment(Comment comment);
    
    public int updateComment(Comment comment);
    
    public int deleteComment(int comment);
}

