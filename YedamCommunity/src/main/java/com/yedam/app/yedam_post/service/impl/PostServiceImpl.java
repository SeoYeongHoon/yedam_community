package com.yedam.app.yedam_post.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.yedam_homework.service.ReplyVO;

import com.yedam.app.yedam_curriculum.service.CurriculumVO;

import com.yedam.app.yedam_post.mapper.CommentMapper;
import com.yedam.app.yedam_post.mapper.PostMapper;
import com.yedam.app.yedam_post.mapper.ReplyMapper;
import com.yedam.app.yedam_post.mapper.ReportMapper;
import com.yedam.app.yedam_post.service.BoardFilesVO;
import com.yedam.app.yedam_post.service.PostCommentVO;
import com.yedam.app.yedam_post.service.PostVO;
import com.yedam.app.yedam_post.service.PostService;
import com.yedam.app.yedam_post.service.PostReplyVO;
import com.yedam.app.yedam_post.service.ReportVO;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostMapper postMapper;

	@Autowired
	private ReplyMapper replyMapper;

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private ReportMapper reportMapper;

	//--------------------------------------------
	// 게시글 등록
	//--------------------------------------------
	@Override
	public int createPost(PostVO postVO) {
		return postMapper.insertPost(postVO);
	}
	
	//--------------------------------------------
	// 게시글 단건조회
	//--------------------------------------------
	@Override                  
	public PostVO getPostReplies(int postId, int boardId) {
		return postMapper.getPostDetails(postId, boardId);
	}
	
	//--------------------------------------------
	// 게시글 수정
	//--------------------------------------------
	@Override
	@Transactional
	public Map<String, Object> PostUpdate(PostVO postVO) {
		Map<String, Object> result = new HashMap<>();
		try {
			postMapper.updatePost1(postVO); // 게시글
			postMapper.updatePost2(postVO); // 파일 수정
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
		}
		return result;
	}
	
	//--------------------------------------------
	// 게시글 삭제
	//--------------------------------------------
	@Override
	@Transactional
	public Map<String, Object> PostDelete(PostVO postVO) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			int postId = postVO.getPostId();
			int boardId = postVO.getBoardId();
			postMapper.deletePost1(postId, boardId); // 대댓글 삭제
			postMapper.deletePost2(postId, boardId); // 댓글 삭제
			postMapper.deletePost3(postId, boardId); // 파일 삭제
			postMapper.deletePost5(postId, boardId); // 게시글 삭제

			resultMap.put("status", "success");
		} catch (Exception e) {
			resultMap.put("status", "error");
			resultMap.put("error", e.getMessage());
		}
		return resultMap;
	}
	
	//--------------------------------------------
	// 게시글 페이지 네이션
	//--------------------------------------------
    @Override
    public List<PostVO> getPosts(PostVO postVO, int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;
        return postMapper.getPosts(postVO, startRow, endRow);
    }
    
    //--------------------------------------------
    // 게시글 개수
    //--------------------------------------------
    @Override
    public int getPostCount(PostVO postVO) {
        return postMapper.getPostCount(postVO);
    }
    
    //--------------------------------------------
	// 댓글 등록
    //--------------------------------------------
	@Override
	public int createReply(PostReplyVO postreplyVO) {
		return replyMapper.insertReply(postreplyVO);
	}
	
	
	//--------------------------------------------
	// 댓글 삭제
	//--------------------------------------------
	@Override
	@Transactional
	public Map<String, Object> deleteReply(PostReplyVO postreplyVO) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			int replyId = postreplyVO.getReplyId();
			replyMapper.deleteReply1(replyId);
			replyMapper.deleteReply2(replyId);

			resultMap.put("status", "success");
		} catch (Exception e) {
			resultMap.put("status", "error");
			resultMap.put("error", e.getMessage());
		}
		return resultMap;
	}
	
	//--------------------------------------------
	// 대댓글 등록
	//--------------------------------------------
	@Override
	public int createComment(PostCommentVO postcommentVO) {
		return commentMapper.insertComment(postcommentVO);
	}
	
	//--------------------------------------------
	// 대댓글 삭제
	//--------------------------------------------
    @Override
    @Transactional
    public Map<String, Object> deleteComment(PostCommentVO postcommentVO) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
        	int commentId = postcommentVO.getCommentId();
            commentMapper.deleteComment(commentId);
            
            resultMap.put("status", "success");
        } catch (Exception e) {
            resultMap.put("status", "error");
            resultMap.put("error", e.getMessage());
        }
        return resultMap;
    }
    
    //--------------------------------------------
    // 댓글조회
    //--------------------------------------------
	@Override
	public List<PostReplyVO> getPostReply(int postId) {
		return replyMapper.getReplies(postId);
	}

	//--------------------------------------------
	// 대댓글 조회
	//--------------------------------------------
	@Override
	public List<PostCommentVO> getPostComment(PostReplyVO replyId) {
		return commentMapper.getComments(replyId);
	}
	
	//--------------------------------------------
	// 조회수
	//--------------------------------------------
	@Override
	public int PostViewCnt(int postId, int boardId) {
		return postMapper.updatePostViewCNT(postId, boardId);
	}

	//--------------------------------------------
	// 추천수 + 1
	//--------------------------------------------
	@Override
	public int updateLike(int postId){
		return postMapper.updatePostLikePlus(postId);
	}
	
	//--------------------------------------------
	// 추천수 - 1 
	//--------------------------------------------
	@Override
	public int updateLikeCancel(int postId){
		return postMapper.updatePostLikeMinus(postId);
	}

	//--------------------------------------------
	// 추천 확인
	//--------------------------------------------
	@Override
	public Integer likeCheck(int postId, int userId) {
		Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);
        map.put("userId", userId);
        return postMapper.likeCheck(map);
	}
	
	//--------------------------------------------
	// 신고 등록
	//--------------------------------------------
	@Override
	public int createReport(ReportVO reportVO) {
		return reportMapper.insertReport(reportVO);
	}

	@Override
	public int createLike(int postId, int userId) {
		Map<String,Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("postId", postId);
		return postMapper.insertLike(map);
	}

	@Override
	public int LikeDelete(int postId, int userId) {
		Map<String,Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("postId", postId);
		return postMapper.deleteLike(map);
	}
	
	//--------------------------------------------
	// 파일 출력
	//--------------------------------------------
	
	public List<BoardFilesVO> getBoardFiles(int postId, int boardId) {
	    return postMapper.getBoardFiles(postId, boardId);
	}

//	@Override
//	public List<PostVO> getPostAll() {
//		return postMapper.MainpagePostList();
//	}

	@Override
	public List<PostVO> getPostAll(int boardId) {
		return postMapper.getpostAll(boardId); 
	}
	
	//======================
	// 댓글 수정
	//=====================
	@Override
	public Map<String, Object> updateReply(PostReplyVO postreplyVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		int result = replyMapper.replyUpdate(postreplyVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("reply", postreplyVO);
		 
		 return map;
	}
	
	//======================
	// 대댓글 수정
	//=====================
	@Override
	public Map<String, Object> updateComment(PostCommentVO postcommentVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		int result = commentMapper.commentUpdate(postcommentVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("comment", postcommentVO);
		return map;
	}

	//--------------------------------------------
    // 마이페이지 내 게시글 조회
    //--------------------------------------------
	@Override
	public List<PostVO> getMyPost(int userId) {
		return postMapper.getMyPost(userId);
	}

	//--------------------------------------------
    // 마이페이지 내 댓글 조회
    //--------------------------------------------
	@Override
	public List<PostReplyVO> getMyReply(int userId) {
		return replyMapper.getMyReply(userId);
	}	
	
	//--------------------------------------------
    // 수료과정별 게시판
    //--------------------------------------------
	@Override
	public List<CurriculumVO> curriculumList() {
		return postMapper.curriculumAll();

	}
	//--------------------------------------------
    // 게시글 전체 조회
    //--------------------------------------------
	@Override
	public List<PostVO> postlist() {
		return postMapper.postAll();
	}

	@Override
	public List<PostVO> selectPost() {
		// TODO Auto-generated method stub
		return null;
	}
}
