package com.yedam.app.yedam_post.service;

import java.util.List;
import java.util.Map;

public interface PostService {
	
	
	//--------------------------------------------
	// 게시글 등록
	//--------------------------------------------
	public int createPost(PostVO postVO);
    
	//--------------------------------------------
    // 게시글 삭제
	//--------------------------------------------
    public Map<String, Object> PostDelete(PostVO postVO);
    
    //--------------------------------------------
    // 게시글 수정
    //--------------------------------------------
    public Map<String, Object> PostUpdate(PostVO postVO);
    
    //--------------------------------------------
    // 게시글 페이지네이션
    //--------------------------------------------
    public List<PostVO> getPosts(PostVO postVO, int page, int pageSize);
    
    //--------------------------------------------
    // 게시글 개수 체크
    //--------------------------------------------
    public int getPostCount(PostVO postVO);
    
    //--------------------------------------------
    // 게시글 상세조회
    //--------------------------------------------
	public PostVO getPostReplies(int postId, int boardId);
	
	//--------------------------------------------
	// 게시글 조회수
	//--------------------------------------------
	public int PostViewCnt(int postVO, int boardId);
	
	//--------------------------------------------
	// 추천 확인
	//--------------------------------------------
	public Integer likeCheck(int postId, int userId);
	
	//--------------------------------------------
	// 추천 등록
	//--------------------------------------------
	public int createLike(int postId, int userId);
	
	//--------------------------------------------
	// 추천 삭제
	//--------------------------------------------
	public int LikeDelete(int postId, int userId);
	
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
	public List<PostReplyVO> getPostReply(int postId);
	
	//--------------------------------------------
	// 대댓글 조회
	//--------------------------------------------
	public List<PostCommentVO> getPostComment(PostReplyVO replyId);
	
	//--------------------------------------------
    // 댓글 등록
	//--------------------------------------------
    public int createReply(PostReplyVO postreplyVO);
    
    //--------------------------------------------
    // 댓글 삭제
    //--------------------------------------------
    public Map<String, Object> deleteReply(PostReplyVO postreplyVO);
    
    //--------------------------------------------
    // 대댓글 등록
    //--------------------------------------------
    public int createComment(PostCommentVO postcommentVO);
    
    //--------------------------------------------
    // 대댓글 삭제
    //--------------------------------------------
    public Map<String, Object> deleteComment(PostCommentVO postcommentVO);
    
    //--------------------------------------------
    // 게시글 신고 등록
    //--------------------------------------------
    public int createReport(ReportVO reportVO);

    //--------------------------------------------
    // 파일 조회
    //--------------------------------------------
	public List<BoardFilesVO> getBoardFiles(int postId, int boardId);

	



}

