package com.yedam.app.yedam_post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.yedam_post.service.Post;

public interface PostMapper {

	  // 전체 조회
    public List<Post> getAllPosts();
    
      // 단건조회 ;
	public Post getPostDetails(Post post);
	
	  // 조회수
    public int updatePostViewCNT(int postId);
    
      // 추천확인
    public int likeCheck(Map<String, Object> map);
    
      // 개추 (추천수 증가)
    public int updatePostLikePlus(int postId);
    
      // 개추 (추천수 감소)
    public int updatePostLikeMinus(int postId);
    
      // 개추 (추천수 1) 중복방지
    public int updatePostLikeOne(Map<String, Object> map);
    
  	  // 개추 (추천수 0) 중복방지
    public int updatePostLikeZero(Map<String, Object> map);
    
	  // 게시글 등록
    public int insertPost(Post post);
    
	
	  // 게시글 수정
    public int updatePost(Post post);
	
	  // 게시글 삭제
    public int deletePost1(int postId);
    public int deletePost2(int postId);
    public int deletePost3(int postId);
    public int deletePost5(int postId);
    
      // 페이지네이션
    public List<Post> getPosts(@Param("startRow") int startRow, @Param("endRow") int endRow);
      
      // 게시글 개수
    public int getPostCount();

}
