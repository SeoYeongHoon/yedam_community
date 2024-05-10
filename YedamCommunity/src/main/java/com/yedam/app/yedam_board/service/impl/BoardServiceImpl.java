package com.yedam.app.yedam_board.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_board.mapper.BoardMapper;
import com.yedam.app.yedam_board.service.BoardService;
import com.yedam.app.yedam_board.service.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardmapper;
	
	@Override
	public List<BoardVO> boardList() {
		return boardmapper.selectBoardAll();
	}

	@Override
	public BoardVO boardInfo(BoardVO boardVO) {
		return boardmapper.selectboard(boardVO);
	}

	@Override
	public int addBoard(BoardVO boardVO) {
		int result = boardmapper.insertBoard(boardVO);
		if (result == 1) {
			return boardVO.getPostId();
		} else {
			return -1;
		}
	}

	@Override
	public Map<String, Object> boardDelete(BoardVO boardVO) {
		Map<String, Object> map = new HashMap<>();
		int result = boardmapper.deleteBoard(boardVO.getBoardId());
		if(result == 1) {
			map.put("boardId", boardVO.getPostId());
		}
		return map;
	}

	@Override
	public Map<String, Object> boardUpdate(BoardVO boardVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = boardmapper.updateBoard(boardVO);
		
		if (result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", boardVO);
		return map;
	}

	@Override
	public BoardVO getPostId() {
		return boardmapper.getPostId();
	}

}
