package com.yedam.app.yedam_board.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.yedam_board.service.BoardService;
import com.yedam.app.yedam_board.service.BoardVO;

@Controller
public class BoardController {
	
	@Autowired 
	BoardService boardService;
	
	@GetMapping("boardList")
	public String boardList(Model model) {
		List<BoardVO> list = boardService.boardList();
		model.addAttribute("boardList", list);
		return "board/boardlist";	
	}
	
	@GetMapping("boardInfo")
	public String boardInfo(BoardVO boardVO, Model model) {
		BoardVO findVO = boardService.boardInfo(boardVO);
		model.addAttribute("boardInfo", findVO);
		return "board/boardinfo";
	}
	
	@GetMapping("boardInsert")
	public String boardInsertForm(Model model) {
		BoardVO boardVO = boardService.getPostId();
		model.addAttribute("boardVO", boardVO);
		return "board/boardinsert";
	}
	
	@PostMapping("boardInsert")
	public String boardInsertProcess(BoardVO boardVO) {
		int pId = boardService.addBoard(boardVO);
		String uri = null;
	  if(pId > -1) {
		  uri = "redirect:boardInfo?board"+pId;
	  } else {
		  uri = "boardList";
	  }
	  return uri;
	}
	
	@GetMapping("boardDelete")
	public String boardDelete(BoardVO boardVO) {
		boardService.boardDelete(boardVO);
		return "redirect:boardList";
	}
	
	@GetMapping("boardUpdate")
	public String boardUdateForm(@RequestParam Integer postId, Model model) {
		BoardVO boardVO = new BoardVO();
		boardVO.setPostId(postId);
		
		BoardVO findVO = new BoardVO();
		model.addAttribute("boardInfo", findVO);
		return "board/boardUpdate";
	}
	@ResponseBody
	public Map<String, Object> empUPdateProcess(@RequestBody BoardVO boardVO) {
		return boardService.boardUpdate(boardVO);
	}
}
