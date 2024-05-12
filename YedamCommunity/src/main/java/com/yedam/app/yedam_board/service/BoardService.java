package com.yedam.app.yedam_board.service;

import java.util.List;
import java.util.Map;

public interface BoardService {
	
	// 전체조회
	public List<BoardVO> boardList();
	
	// 단건조회
	public BoardVO boardInfo(BoardVO boardVO);
	
	// 등록
	public int addBoard(BoardVO boardVO);
	
	// 삭제
	public Map<String, Object> boardDelete(BoardVO boardVO);
	
	// 수정
	public Map<String, Object> boardUpdate(BoardVO boardVO);
	
	// 등록할 게시글 번호 조회
	public BoardVO getPostId();
}
