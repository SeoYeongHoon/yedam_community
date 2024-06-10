package com.yedam.app.yedam_post.service;

import java.util.List;
import java.util.Map;

import com.yedam.app.yedam_homework.service.ReplyVO;

import com.yedam.app.yedam_curriculum.service.CurriculumVO;

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
    // 댓글 수정
    //--------------------------------------------
    public  Map<String, Object> updateReply(PostReplyVO postreplyVO);
    
    //--------------------------------------------
    // 댓글 삭제
    //--------------------------------------------
    public Map<String, Object> deleteReply(PostReplyVO postreplyVO);
    
    //--------------------------------------------
    // 대댓글 등록
    //--------------------------------------------
    public int createComment(PostCommentVO postcommentVO);
    
    //--------------------------------------------
    // 대댓글 등록
    //--------------------------------------------
    public Map<String, Object> updateComment(PostCommentVO postcommentVO);
    
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
	
	//수료과정 파일 조회
	public List<BoardFilesVO> successFileList(int boardType);
	
	//--------------------------------------------
    // 메인페이지 조회
    //--------------------------------------------
	public List<PostVO> getPostAll(int boardId);
	
	//--------------------------------------------
    // 마이페이지 내 게시글 조회
    //--------------------------------------------
	public List<PostVO> getMyPost(int userId);
	
	//--------------------------------------------
    // 마이페이지 내 댓글 조회
    //--------------------------------------------
	public List<PostReplyVO> getMyReply(int userId);
  
	//--------------------------------------------
    // 수료과정
    //--------------------------------------------
	public List<CurriculumVO> curriculumList();
	
	//--------------------------------------------
    // 게시글 전체 조회
    //--------------------------------------------
	public List<PostVO> postlist();
	
	//--------------------------------------------
    // 해당커리큘럼 게시글 조회
    //--------------------------------------------
	public List<PostVO> selectCurriculum(int curriculumId);
	
	//--------------------------------------------
    // 게시글 페이징 및 필터링
    //--------------------------------------------
	public List<PostVO> getPostsByFilter (int filter, int page, String searchQuery);
	
	//--------------------------------------------
	// 페이징용 카운트
	//--------------------------------------------
	public int getTotalCnt(int filter, String searchQuery);
	
	//--------------------------------------------
	// 접속유저id로 boardId 조회
	//--------------------------------------------
	public int boardId(int userId);	

	//--------------------------------------------
	// 질문 토론 단건조회 Yes
	//--------------------------------------------
	public PostVO getPostVotedetail(PostVO postVO);

	//--------------------------------------------
	// boardType로 boardId 조회
	//--------------------------------------------
	public int boardTypeSet(int boardType);
	
	//--------------------------------------------
	
	//--------------------------------------------
	public PostVO getPostVotedetailNo(PostVO postVO);
	
	//--------------------------------------------
	
	//--------------------------------------------
	public int createVote(PostVO postVO);
	
	//--------------------------------------------
	//--------------------------------------------
	public int createVoteUser(VoteUserVO voteUser);
    
    //--------------------------------------------
    //--------------------------------------------
	public Integer voteExists(Map<String, Object> map);
    
    //--------------------------------------------
    //--------------------------------------------
	public int cancelVote(Map<String, Object> map);
    
    //--------------------------------------------
    //--------------------------------------------
	public int submitVote(Map<String, Object> map);
	
    //--------------------------------------------
    //--------------------------------------------
	public int VoteCountUP(Map<String, Object> map);

	public int VoteCountDOWN(Map<String, Object> map);

	public int findByCurriculum(int curriculumId);
	
	public int findIdByCurriculum(int curriculumId);
	
	//--------------------------------------------
  	// 추천 조회
  	//--------------------------------------------
	int selectLikeStatus(int postId, int userId);
}
