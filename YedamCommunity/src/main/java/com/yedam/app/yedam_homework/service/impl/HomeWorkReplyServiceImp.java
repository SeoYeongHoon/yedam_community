package com.yedam.app.yedam_homework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_homework.mapper.HomeWorkReplyMapper;
import com.yedam.app.yedam_homework.service.HomeWorkReplyService;
import com.yedam.app.yedam_homework.service.HomeWorkVO;

@Service
public class HomeWorkReplyServiceImp implements HomeWorkReplyService {

	@Autowired
	HomeWorkReplyMapper homeworkReplyMapper;
	//댓글 등록
	@Override
	public int replyInsert(HomeWorkVO homeworkVO) {
		return homeworkReplyMapper.insertReply(homeworkVO);
	}
	//댓글 조회
	@Override
	public List<HomeWorkVO> replyInfo(HomeWorkVO homeworkVO) {
		return homeworkReplyMapper.selectReply(homeworkVO);
	}
	
	//대댓글 조회
	@Override
	public HomeWorkVO commentList(HomeWorkVO homeworkVO) {
		return homeworkReplyMapper.selectComment(homeworkVO);
	}
	//대댓글 등록
	@Override
	public int commentInsert(HomeWorkVO homeworkVO) {
		return homeworkReplyMapper.insertComment(homeworkVO);
	}

}
