package com.yedam.app.yedam_post.mapper;

import java.util.List;

import com.yedam.app.yedam_post.service.Comment;
import com.yedam.app.yedam_post.service.Post;
import com.yedam.app.yedam_post.service.Reply;

public interface PostMapper {

	  // 전체 조회
    public List<Post> getAllPosts();
    
      // 단건조회 int 타입
	public Post getPostDetails(int postId);
	
	  // 게시글 등록
    public int insertPost(Post post);
	
	  // 게시글 수정
    public int updatePost(Post post);
	
	  // 게시글 삭제
    public int deletePost1(int postId);
    public int deletePost2(int postId);
    public int deletePost3(int postId);
    public int deletePost5(int postId);
    
    // 조회수
    public int updatePostViewCNT(Post post);
    
    // 개추 (추천수)
    public int updatePostLikeCNT(Post post);
    
    // 댓글 및 대댓글 관련 메서드
    
    
     // 대댓글들 조회
   public Post getComments(Post post);
	
	  // 댓글 추가
    public int insertReply(Reply reply);
	
	  // 댓글 수정
    public int updateReply(Reply reply);
	
	  // 댓글 삭제
    public int deleteReply(int replyId);

	  // 대댓글 등록
    public int insertComment(Comment comment);
	
	  // 대댓글 수정
    public int updateComment(Comment comment);
	
	  // 대댓글 삭제
    public int deleteComment(int commentId);
	
    
    
}
