package com.yedam.app.yedam_homework.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_homework.mapper.HomeWorkReplyMapper;
import com.yedam.app.yedam_homework.service.CommentVO;
import com.yedam.app.yedam_homework.service.HomeWorkReplyService;
import com.yedam.app.yedam_homework.service.ReplyVO;

@Service
public class HomeWorkReplyServiceImp implements HomeWorkReplyService {

	@Autowired
	HomeWorkReplyMapper homeworkReplyMapper;

	// 댓글 등록
	@Override
	public int replyInsert(ReplyVO replyVO) {
		return homeworkReplyMapper.insertReply(replyVO);
	}

	// 댓글 조회
	@Override
	public List<ReplyVO> replyList(int homeworkTargetId) {
		List<ReplyVO> list =  homeworkReplyMapper.selectReplyAll(homeworkTargetId);
		for(ReplyVO reply : list) {
			reply.setComments(homeworkReplyMapper.selectComment(reply.getReplyId()));
		}
		
		return list;
	}

	// 대댓글 등록
	@Override
	public int commentInsert(CommentVO comment) {
		return homeworkReplyMapper.insertComment(comment);
	}

	// 대댓글 삭제
	@Override
	public int commentDelete(int comment) {
		return homeworkReplyMapper.deleteComment(comment);
	}

	// 댓글 삭제
	@Override
	public int replyDelete(int replyid) {
		return homeworkReplyMapper.deleteReply(replyid);
	}

	// 댓글 수정
	@Override
	public Map<String, Object> replyUpdate(ReplyVO replyVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = homeworkReplyMapper.updateReply(replyVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result",isSuccessed);
		map.put("reply", replyVO);
		
		return map;
	}

	@Override
	public Map<String, Object> commentUpdate(CommentVO commentVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = homeworkReplyMapper.updateComment(commentVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result",isSuccessed);
		map.put("reply", commentVO);
		
		return map;
	}

	// 댓글, 대댓글 총 개수
	@Override
	public ReplyVO getTotalCount(int homeworkTargetId) {
		ReplyVO replyVO = new ReplyVO();
		int count = homeworkReplyMapper.getTotalCount(homeworkTargetId);
		replyVO.setTotalCount(count);
		return replyVO;
	}

}
