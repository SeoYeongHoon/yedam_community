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
    
    // 게시글 페이지네이션
    public List<Post> getPosts(int page, int pageSize);
    
    // 게시글 개수 체크
    public int getPostCount();
    
    // 댓글 조회
	public Post getPostReplies(int postId);
	
    // 댓글 등록
    public int createReply(Reply reply);
    
    // 댓글 삭제
    public Map<String, Object> deleteReply(Reply reply);
    
    // 대댓글 등록
    public int createComment(Comment comment);
    
    // 대댓글 삭제
    public Map<String, Object> deleteComment(int commentId);

    
}

