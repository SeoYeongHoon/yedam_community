package com.yedam.app.yedam_post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.yedam_post.service.Post;

public interface PostMapper {
	
    //--------------------------------------------
    // 전체 조회
    //--------------------------------------------
    List<Post> getAllPosts(@Param("boardId") int boardId);

    //--------------------------------------------
    // 단건조회
    //--------------------------------------------
    Post getPostDetails(@Param("postId") int postId
    		          , @Param("boardId") int boardId);
    
    //--------------------------------------------
    // 게시글 검색
    //--------------------------------------------
    List<Post> searchPosts(@Param("boardId") int boardId
    		             , @Param("keyword") String keyword);

    //--------------------------------------------
    // 조회수
    //--------------------------------------------
    int updatePostViewCNT(@Param("postId") int postId
    		            , @Param("boardId") int boardId);

    //--------------------------------------------
    // 추천확인
    //--------------------------------------------
    int likeCheck(Map<String, Object> map);

    //--------------------------------------------
    // 추천등록
    //--------------------------------------------
    int insertLike(Map<String, Object> map);

    //--------------------------------------------
    // 추천 삭제
    //--------------------------------------------
    Map<String, Object> deleteLike(Map<String, Object> map);

    //--------------------------------------------
    // 개추 (추천수 증가)
    //--------------------------------------------
    int updatePostLikePlus(int postId);

    //--------------------------------------------
    // 개추 (추천수 감소)
    //--------------------------------------------
    int updatePostLikeMinus(int postId);

    //--------------------------------------------
    // 게시글 등록
    //--------------------------------------------
    int insertPost(Post post);

    //--------------------------------------------
    // 게시글 수정
    //--------------------------------------------
    int updatePost(Post post);

    //--------------------------------------------
    // 게시글 삭제
    //--------------------------------------------
    int deletePost1(int postId);
    int deletePost2(int postId);
    int deletePost3(int postId);
    int deletePost5(int postId);

    //--------------------------------------------
    // 페이지네이션
    //--------------------------------------------
    List<Post> getPosts(@Param("boardId") int boardId
    		          , @Param("startRow") int startRow
    		          , @Param("endRow") int endRow);

    //--------------------------------------------
    // 게시글 개수
    //--------------------------------------------
    int getPostCount(@Param("boardId") int boardId);
}
