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
	
	@Override
	public int replyInsert(HomeWorkVO homeworkVO) {
		return homeworkReplyMapper.insertReply(homeworkVO);
	}

	@Override
	public List<HomeWorkVO> replyInfo(HomeWorkVO homeworkVO) {
		return homeworkReplyMapper.selectReply(homeworkVO);
	}

}
