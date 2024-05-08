package com.yedam.app.yedam_reply.service;

import java.util.List;

public interface ReplyService {
	
    List<ReplyVO> replyList(ReplyVO repleVO);
	
	int addReply(ReplyVO repleVO);
	
	int deleteReply(int replyId);
	
	int updateReply(int replyId);
}
