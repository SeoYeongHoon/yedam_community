package com.yedam.app.yedam_post.mapper;

import java.util.List;

import com.yedam.app.yedam_post.service.Post;

public interface PostMapper {
	
	// 전체 조회
	public List<Post> getAllPosts();
	
	// 단건 조회
	Post getPostById(int postId);
	
	// 등록
	public int insertPost(Post post);
	
	// 삭제 
	public int deletePost(int postId);
	
	// 수정
	public int updatePost(Post post);

	
	public int deletePostById(int postId);
}
