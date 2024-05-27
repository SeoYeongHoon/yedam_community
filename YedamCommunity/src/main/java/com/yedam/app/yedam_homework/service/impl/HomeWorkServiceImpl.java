package com.yedam.app.yedam_homework.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.yedam_homework.mapper.HomeWorkMapper;
import com.yedam.app.yedam_homework.service.HomeWorkService;
import com.yedam.app.yedam_homework.service.HomeWorkTargetVO;
import com.yedam.app.yedam_homework.service.HomeWorkVO;

@Service
public class HomeWorkServiceImpl implements HomeWorkService{

	@Autowired
	HomeWorkMapper homeworkMapper; //mapper 불러옴

	// 과제 목록 조회
	
	 @Override public List<HomeWorkVO> homeworkList() { 
		 return	 homeworkMapper.selectHomeworkAll(); 
		 }
	 
	
	// 과제 등록
	@Override
	public int homeworkInsert(HomeWorkVO homeworkVO) {
		return homeworkMapper.insertHomework(homeworkVO);
	}

	// 과제 상세 조회
	@Override
	public HomeWorkVO homeworkInfo(HomeWorkVO homeworkVO) {
		return homeworkMapper.selectHomework(homeworkVO);
	}

	//과제명 조회
	@Override
	public List<HomeWorkVO> subjectNameList(HomeWorkVO homeworkVO) {
		return homeworkMapper.selectSubjectName(homeworkVO);
	}
	//과제 대상자 등록
	@Override
	public int homeworkTargetInsert(HomeWorkTargetVO homeworktargetVO) {
		return homeworkMapper.insertHomeworkTarget(homeworktargetVO);
	}
	//과제 대상자 조회
	@Override
	public HomeWorkTargetVO homeworktargetList(HomeWorkVO homeworkVO) {
		return homeworkMapper.selectHomeworktarget(homeworkVO);
	}
	
	//강의실 카테고리
	@Override
	public List<HomeWorkVO> classCategory(int curriculumId) {
		return homeworkMapper.selectClass(curriculumId);
	}

	//해당 학생 과제 조회
	@Override
	public List<HomeWorkVO> userHomeworkList(int userid) {
		return homeworkMapper.selectUserHomework(userid);
	}

	//과목명 카테고리
	@Override
	public List<HomeWorkVO> subjectCategory(int subjectId, int userId) {
		return homeworkMapper.selectSubject(subjectId, userId);
	}

	// 과제 삭제
	@Override
	public int homeworkDelete(int homeworkId) {
		return homeworkMapper.deleteHomework(homeworkId);
	}

	// 최근 과제 출력
	@Override
	public List<HomeWorkVO> getRecentTest(int userId) {
		return homeworkMapper.selectRecentHomework(userId);
	}
	
}
