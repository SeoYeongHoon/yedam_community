package com.yedam.app.yedam_post.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.yedam_post.mapper.CommentMapper;
import com.yedam.app.yedam_post.mapper.PostMapper;
import com.yedam.app.yedam_post.mapper.ReplyMapper;
import com.yedam.app.yedam_post.mapper.ReportMapper;
import com.yedam.app.yedam_post.service.Comment;
import com.yedam.app.yedam_post.service.Post;
import com.yedam.app.yedam_post.service.PostService;
import com.yedam.app.yedam_post.service.Reply;
import com.yedam.app.yedam_post.service.Report;

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
	public int createPost(Post post) {
		return postMapper.insertPost(post);
	}
	
	//--------------------------------------------
	// 게시글 전체 조회
	//--------------------------------------------
	@Override
	public List<Post> getAllPosts() {
		return postMapper.getAllPosts();
	}

	//--------------------------------------------
	// 게시글 단건조회
	//--------------------------------------------
	@Override                  
	public Post getPostReplies(int postId) {
		return postMapper.getPostDetails(postId);
	}
	
	//--------------------------------------------
	// 게시글 수정
	//--------------------------------------------
	@Override
	public Map<String, Object> PostUpdate(Post post) {
		Map<String, Object> result = new HashMap<>();
		try {
			postMapper.updatePost(post);
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
	public Map<String, Object> PostDelete(Post post) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			int postId = post.getPostId();
			postMapper.deletePost1(postId); // 대댓글 삭제
			postMapper.deletePost2(postId); // 댓글 삭제
			postMapper.deletePost3(postId); // 파일 삭제
			                                   
			                                // 사이에 들어갈 삭제목록 자리들
			postMapper.deletePost5(postId); // 게시글 삭제

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
    public List<Post> getPosts(int page, int pageSize) {
        int startRow = (page - 1) * pageSize;
        int endRow = page * pageSize;
        return postMapper.getPosts(startRow, endRow);
    }
    
    //--------------------------------------------
    // 게시글 개수
    //--------------------------------------------
    @Override
    public int getPostCount() {
        return postMapper.getPostCount();
    }
    
    //--------------------------------------------
	// 댓글 등록
    //--------------------------------------------
	@Override
	public int createReply(Reply reply) {
		return replyMapper.insertReply(reply);
	}
	
	
	//--------------------------------------------
	// 댓글 삭제
	//--------------------------------------------
	@Override
	@Transactional
	public Map<String, Object> deleteReply(Reply reply) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			int replyId = reply.getReplyId();
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
	public int createComment(Comment comment) {
		return commentMapper.insertComment(comment);
	}
	
	//--------------------------------------------
	// 대댓글 삭제
	//--------------------------------------------
    @Override
    @Transactional
    public Map<String, Object> deleteComment(Comment comment) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
        	int commentId = comment.getCommentId();
            commentMapper.deleteComment(commentId);
            
            resultMap.put("status", "success");
        } catch (Exception e) {
            resultMap.put("status", "error");
            resultMap.put("error", e.getMessage());
        }
        return resultMap;
    }
    
    //--------------------------------------------
    // 대글조회
    //--------------------------------------------
	@Override
	public List<Reply> getPostReply(int postId) {
		return replyMapper.getReplies(postId);
	}

	//--------------------------------------------
	// 대댓글 조회
	//--------------------------------------------
	@Override
	public List<Comment> getPostComment(Reply replyId) {
		return commentMapper.getComments(replyId);
	}
	
	//--------------------------------------------
	// 조회수
	//--------------------------------------------
	@Override
	public int PostViewCnt(int postId) {
		return postMapper.updatePostViewCNT(postId);
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

//	//--------------------------------------------
//	// 게시글 추천 시 Check를 1로 만들어서 중복방지
//	//--------------------------------------------
//	@Override
//	public int updateLikeCheck(int postId, int userId){
//		Map<String, Object> map = new HashMap<>();
//        map.put("postId", postId);
//        map.put("userId", userId);
//        return postMapper.updatePostLikeOne(map);
//	}
//
//	//--------------------------------------------
//	// 게시글 추천 시 Check를 0로 만들어서 중복방지
//	//--------------------------------------------
//	@Override
//	public int updateLikeCheckCancel(int postId, int userId){
//		Map<String, Object> map = new HashMap<>();
//        map.put("postId", postId);
//        map.put("userId", userId);
//        return postMapper.updatePostLikeZero(map);
//	}

	//--------------------------------------------
	// 추천 확인
	//--------------------------------------------
	@Override
	public int likeCheck(int postId, int userId) {
		Map<String, Object> map = new HashMap<>();
        map.put("postId", postId);
        map.put("userId", userId);
        return postMapper.likeCheck(map);
	}
	
	//--------------------------------------------
	// 신고 등록
	//--------------------------------------------
	@Override
	public int createReport(Report report) {
		return reportMapper.insertReport(report);
	}

	@Override
	public int createLike(int postId, int userId) {
		Map<String,Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("postId", postId);
		return postMapper.insertLike(map);
	}

	@Override
	public Map<String, Object> LikeDelete(int postId, int userId) {
		Map<String,Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("postId", postId);
		return postMapper.deleteLike(map);
	}

}
