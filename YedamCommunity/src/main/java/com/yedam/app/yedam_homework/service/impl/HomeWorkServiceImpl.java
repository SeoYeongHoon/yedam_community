package com.yedam.app.yedam_homework.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// 과제 페이징 및 필터링(교수)
	@Override
	public List<HomeWorkVO> getHomeworksByFilter(int filter, int page, String searchQuery) {
		Map<String, Object> params = new HashMap<>();
		params.put("filter", filter);
		params.put("page", page);
		params.put("searchQuery", searchQuery);
		
		return homeworkMapper.getHomeworksByFilter(params);
	}
	
	// 과제 페이징 및 필터링(학생)
	@Override
	public List<HomeWorkVO> getHomeworksByFilterStudent(int filter, int page, String searchQuery, int userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("filter", filter);
		params.put("page", page);
		params.put("searchQuery", searchQuery);
		params.put("userId", userId);
		
		return homeworkMapper.getHomeworksByFilterStudent(params);
	}
	
	// 페이징용 카운트(교수)
	@Override
	public int getTotalCnt(int filter, String searchQuery) {
		return homeworkMapper.getTotalCnt(filter, searchQuery);
	}

	//과제 수정
	@Override
	public Map<String, Object> homeworkUpdate(HomeWorkVO homeworkVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = homeworkMapper.updateHomework(homeworkVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result",isSuccessed);
		map.put("reply", homeworkVO);
		
		return map;
	}

	// 페이징용 카운트(학생)
	@Override
	public int getTotalCntStudent(int filter, String searchQuery, int userId) {
		return homeworkMapper.getTotalCntStudent(filter,searchQuery,userId);
	}


	
	
}
