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
    
    // 게시글 상세조회
	public Post getPostReplies(Post post);
	
	// 게시글 조회수
	public int PostViewCnt(int post);
	
	/*=====================================================*/
	
	// 추천 확인
	int likeCheck(int postId, String userId);
	
	// 추천수 + 1
	public int updateLike(int postId);

	// 추천수 - 1
	public int updateLikeCancel(int postId);

    // 게시글 추천 시 Check를 1로 만들어서 중복방지
	public int updateLikeCheck(int postId, String userId);
    
    // 게시글 추천 시 Check를 0으로 만들어서 중복방지
	public int updateLikeCheckCancel(int postId, String userId);
	
	/*=====================================================*/
	
	// 댓글 조회
	public List<Reply> getPostReply(Post post);
	
	// 대댓글 조회
	public List<Comment> getPostComment(Reply replyId);
	
    // 댓글 등록
    public int createReply(Reply reply);
    
    // 댓글 삭제
    public Map<String, Object> deleteReply(Reply reply);
    
    // 대댓글 등록
    public int createComment(Comment comment);
    
    // 대댓글 삭제
    public Map<String, Object> deleteComment(Comment comment);

}

