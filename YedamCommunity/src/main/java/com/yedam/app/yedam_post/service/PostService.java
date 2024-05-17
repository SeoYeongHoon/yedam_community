package com.yedam.app.yedam_post.service;

import java.util.List;
import java.util.Map;

public interface PostService {
	
	// 게시글 등록
	public int createPost(Post post);
    
	// 게시글 전체 조회
	public List<Post> getAllPosts();
    
    // 게시글 삭제
    public Map<String, Object> PostDelete(Post post);
    
    // 게시글 수정
    public Map<String, Object> PostUpdate(Post post);
    
    // 게시글 단건 조회 - post 타임
    public Post getPostDetails(Post post);
    
    // 게시글 단건 조회 - int 타입
    public Post getPostDetails(int postId); 
    
    
    
    // 댓글 등록
    public int createReply(Reply reply);
    
    // 댓글 수정
    public int updateReply(Reply reply);
    
    // 댓글 삭제
    public int deleteReply(int replyId);
    
    
    
    // 대댓글 등록
    public int createComment(Comment comment);
    
    // 대댓글 수정
    public int updateComment(Comment comment);
    
    // 대댓글 삭제
    public int deleteComment(int comment);
    
    
	// 댓글 조회
	public Post getPostReplies(int postId);
	  
	// 대댓글 조회
	public Post getPostComments(int replyId);

	
    
}

