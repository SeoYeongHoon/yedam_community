package com.yedam.app.yedam_post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.yedam_post.service.Post;

public interface PostMapper {

	  // 전체 조회
    public List<Post> getAllPosts();
    
      // 단건조회 int 타입 ;
	public Post getPostDetails(int postId);
	
	  // 조회수
    public int updatePostViewCNT(Post post);
    
      // 개추 (추천수) - 사용중 아님
    public int updatePostLikeCNT(Post post);
    
	  // 게시글 등록
    public int insertPost(Post post);
	
	  // 게시글 수정
    public int updatePost(Post post);
	
	  // 게시글 삭제
    public int deletePost1(int postId);
    public int deletePost2(int postId);
    public int deletePost3(int postId);
    public int deletePost5(int postId);
    
      // 페이지 네이션
    public List<Post> getPosts(@Param("startRow") int startRow, @Param("endRow") int endRow);
      
      // 게시글 개수
    public int getPostCount();
    
}
