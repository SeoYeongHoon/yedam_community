package com.yedam.app.yedam_post.mapper;

import java.util.List;

import com.yedam.app.yedam_post.service.VoteItemVO;
import com.yedam.app.yedam_post.service.VoteUserVO;
import com.yedam.app.yedam_post.service.VoteVO;


public interface VoteMapper {
	
	List<VoteVO> selectVotes();

	int insertVote(VoteVO vote);

	List<VoteItemVO> selectVoteItemsByVoteId(int voteId);

	int insertVoteItem(VoteItemVO voteItem);

	int insertVoteUser(VoteUserVO voteUser);
}
