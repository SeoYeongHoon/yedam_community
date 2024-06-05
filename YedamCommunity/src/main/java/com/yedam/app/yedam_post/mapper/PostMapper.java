package com.yedam.app.yedam_post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.yedam_curriculum.service.CurriculumVO;
import com.yedam.app.yedam_post.service.BoardFilesVO;
import com.yedam.app.yedam_post.service.PostVO;

public interface PostMapper {

    //--------------------------------------------
    // 단건조회
    //--------------------------------------------
    PostVO getPostDetails(@Param("postId") int postId
    		          , @Param("boardId") int boardId);

    //--------------------------------------------
    // 조회수
    //--------------------------------------------
    int updatePostViewCNT(@Param("postId") int postId
    		            , @Param("boardId") int boardId);

    //--------------------------------------------
    // 추천확인
    //--------------------------------------------
    Integer likeCheck(Map<String, Object> map);

    //--------------------------------------------
    // 추천등록
    //--------------------------------------------
    int insertLike(Map<String, Object> map);

    //--------------------------------------------
    // 추천 삭제
    //--------------------------------------------
    int deleteLike(Map<String, Object> map);

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
    int insertPost(PostVO postVO);

    //--------------------------------------------
    // 게시글 수정
    //--------------------------------------------
    int updatePost1(PostVO postVO); // 제목, 내용 수정
    int updatePost2(PostVO postVo); // 파일 수정

    //--------------------------------------------
    // 게시글 삭제 
    //--------------------------------------------
    int deletePost1(int postId, int boardId);
    int deletePost2(int postId, int boardId);
    int deletePost3(int postId, int boardId);
    int deletePost5(int postId, int boardId);

    //--------------------------------------------
    // 페이지네이션
    //--------------------------------------------
    List<PostVO> getPosts(PostVO postVO
    		          , @Param("startRow") int startRow
    		          , @Param("endRow") int endRow);

    //--------------------------------------------
    // 게시글 개수
    //--------------------------------------------
    int getPostCount(PostVO postVO);

    //--------------------------------------------
    // 파일 불러오기
    //--------------------------------------------
	public List<BoardFilesVO> getBoardFiles(int postId, int boardId);
	
	//수료과정 파일 조회
	public List<BoardFilesVO> successFile(int boardType);

	//--------------------------------------------
    // 메인페이지 조회
    //--------------------------------------------
	public List<PostVO> getpostAll(int boardId);
	
	//--------------------------------------------
    // 마이페이지 내 게시글 조회
    //--------------------------------------------
	public List<PostVO> getMyPost(int userId);

	// --------------------------------------------
	// 수료과정
	// --------------------------------------------
	public List<CurriculumVO> curriculumAll();
	
	//--------------------------------------------
    // 게시글 전체 조회
    //--------------------------------------------
	public List<PostVO> postAll();
	
	//--------------------------------------------
    // 커리큘럼별 게시글 조회
    //--------------------------------------------
	public List<PostVO> curriculumSelect(int curriculumId);
	
	// 게시글 페이징 및 필터링
	public List<PostVO> getPostsByFilter (int filter, int page, String searchQuery);
	
	// 페이징용 카운트
	int getTotalCnt(int filter, String searchQuery);
	
	// 접속유저id로 boardId 조회
	public int getBoardId(int userId);
	
	// boardType로 boardId 조회
	public int setBoardType(int boardType);
}
