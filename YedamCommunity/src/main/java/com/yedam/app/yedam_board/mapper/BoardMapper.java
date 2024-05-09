package com.yedam.app.yedam_board.mapper;

import java.util.List;

import com.yedam.app.yedam_board.service.BoardVO;

public interface BoardMapper {
	
	// 전체 조회
	public List<BoardVO> selectBoardAll();
	
	// 단건 조회
	public BoardVO selectboard(BoardVO boardVO);
	
	// 등록
	public int insertBoard(BoardVO boardVO);
	
	// 삭제
	public int deleteBoard(int boardId);

	// 수정
	public int updateBoard(BoardVO boardId);

	public BoardVO getPostId();
}
