package com.yedam.app.yedam_reply.mapper;

import java.util.List;

import com.yedam.app.yedam_reply.service.ReplyVO;

public interface ReplyMapper {
		
		// 목록
		List<ReplyVO> selectReplyList(ReplyVO replyVO);
		
		// 등록
		int insertReply(ReplyVO replyVO);
		
		// 삭제
		int deleteReply(int replyId);
		
		// 수정
		int modifyReply(int replyId);
}
