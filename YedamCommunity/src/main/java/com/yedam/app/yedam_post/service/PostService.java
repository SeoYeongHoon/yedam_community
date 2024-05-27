package com.yedam.app.yedam_post.service;

import java.util.List;
import java.util.Map;

public interface PostService {
	
	//--------------------------------------------
	// 게시글 등록
	//--------------------------------------------
	public int createPost(Post post);
    
	//--------------------------------------------
    // 게시글 삭제
	//--------------------------------------------
    public Map<String, Object> PostDelete(Post post);
    
    //--------------------------------------------
    // 게시글 수정
    //--------------------------------------------
    public Map<String, Object> PostUpdate(Post post);
    
    //--------------------------------------------
    // 게시글 페이지네이션
    //--------------------------------------------
    public List<Post> getPosts(Post post, int page, int pageSize);
    
    //--------------------------------------------
    // 게시글 개수 체크
    //--------------------------------------------
    public int getPostCount(Post post);
    
    //--------------------------------------------
    // 게시글 상세조회
    //--------------------------------------------
	public Post getPostReplies(int postId, int boardId);
	
	//--------------------------------------------
	// 게시글 조회수
	//--------------------------------------------
	public int PostViewCnt(int post, int boardId);
	
	//--------------------------------------------
	// 추천 확인
	//--------------------------------------------
	public int likeCheck(int postId, int userId);
	
	//--------------------------------------------
	// 추천 등록
	//--------------------------------------------
	public int createLike(int postId, int userId);
	
	//--------------------------------------------
	// 추천 삭제
	//--------------------------------------------
	public Map<String, Object> LikeDelete(int postId, int userId);
	
	//--------------------------------------------
	// 추천수 + 1
	//--------------------------------------------
	public int updateLike(int postId);

	//--------------------------------------------
	// 추천수 - 1
	//--------------------------------------------
	public int updateLikeCancel(int postId);
	
	//--------------------------------------------
	// 댓글 조회
	//--------------------------------------------
	public List<Reply> getPostReply(int postId);
	
	//--------------------------------------------
	// 대댓글 조회
	//--------------------------------------------
	public List<Comment> getPostComment(Reply replyId);
	
	//--------------------------------------------
    // 댓글 등록
	//--------------------------------------------
    public int createReply(Reply reply);
    
    //--------------------------------------------
    // 댓글 삭제
    //--------------------------------------------
    public Map<String, Object> deleteReply(Reply reply);
    
    //--------------------------------------------
    // 대댓글 등록
    //--------------------------------------------
    public int createComment(Comment comment);
    
    //--------------------------------------------
    // 대댓글 삭제
    //--------------------------------------------
    public Map<String, Object> deleteComment(Comment comment);
    
    //--------------------------------------------
    // 게시글 신고 등록
    //--------------------------------------------
    public int createReport(Report report);

	public List<BoardFiles> getBoardFiles(int postId, int boardId);





}

