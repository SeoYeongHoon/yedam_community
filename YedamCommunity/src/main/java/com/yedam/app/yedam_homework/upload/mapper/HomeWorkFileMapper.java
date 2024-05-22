package com.yedam.app.yedam_homework.upload.mapper;

import java.util.List;

import com.yedam.app.yedam_homework.service.HomeWorkVO;
import com.yedam.app.yedam_homework.service.ReplyVO;
import com.yedam.app.yedam_homework.upload.service.HomeWorkFileVO;
import com.yedam.app.yedam_homework.upload.service.ReplyFileVO;

public interface HomeWorkFileMapper {

	// 과제 파일 등록
	public int insertHomeWorkFile(HomeWorkFileVO homeworkfileVO);

	// 과제 파일 조회
	public List<HomeWorkFileVO> selectHomeworkfile(HomeWorkVO homeworkVO);

	// 과제 파일 이름 조회
	public String selectHomeworkFileName(String downloadLocation);

	// 댓글 파일 등록
	public int insertReplyfFile(ReplyFileVO replyfileVO);

	// 댓글 파일 조회
	public List<ReplyFileVO> selectReplyfile(ReplyVO reply);

	// 과제 파일 이름 조회
	public String selectReplyFileName(String downloadLocation);
}
