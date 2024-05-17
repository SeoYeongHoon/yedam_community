package com.yedam.app.yedam_examteacher.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.yedam_examteacher.mapper.ExamMapper;
import com.yedam.app.yedam_examteacher.service.ExamService;
import com.yedam.app.yedam_examteacher.service.TeacherVO;

@Service
public class ExamServiceImpl implements ExamService{
	@Autowired
	ExamMapper examMapper;
	
	// 전체 출력 - 연구중
	
	@Override
	public List<TeacherVO> allList(TeacherVO teacherVO) {
		List<TeacherVO> list = new ArrayList<TeacherVO>();
		list.addAll(examMapper.selectExamAll());
		list.addAll(examMapper.selectQuizAll());
		list.addAll(examMapper.selectAnswerAll(teacherVO));
		list.addAll(examMapper.selectSubjectAll());

		return list;
	}
	
	// 시험목록 출력
	@Override
	public List<TeacherVO> testList() {
		return examMapper.selectExamAll();
	}
	
	// 시험 등록
	@Override
	public int testInsert(TeacherVO teacherVO) {
		int result = examMapper.insertTest(teacherVO);
		
		if(result == 1) {
			return teacherVO.getTestId();
		} else {
			return -1;
		}
	}
	
	// 문제 및 지문 등록
	@Transactional
	@Override
	public void quizInsert(TeacherVO teacherVO) {
		examMapper.insertQuiz(teacherVO);
		examMapper.insertAnswer(teacherVO);
		examMapper.insertAnswer2(teacherVO);
		examMapper.insertAnswer3(teacherVO);
		examMapper.insertAnswer4(teacherVO);
		examMapper.insertAnswer5(teacherVO);
	}
	
	// 등록된 문제 출력 => 필터출력으로 대체. 나중에 삭제.
	 @Override 
	 public List<TeacherVO> quizList() { 
		return examMapper.selectQuizAll(); 
	}
	 
	// 등록된 지문 출력 -> 나중에 삭제
	@Override
	public List<TeacherVO> answerList1(TeacherVO teacherVO) {
		return examMapper.selectAnswerAll(teacherVO);
	}
	
	// 문제 지문 출력
	@Override
	public List<TeacherVO> answerList(int qId) {
		return examMapper.quizAnswer(qId);
	}
	
	// 문제 단건조회(자세히 보기)
	@Override
	public TeacherVO quizInfo(TeacherVO teacherVO) {
		return examMapper.selectQuiz(teacherVO);
	}
	
	// 과목명 출력
	@Override
	public List<TeacherVO> subjectList() {
		return examMapper.selectSubjectAll();
	}
	
	// 과목 추가
	@Override
	public int subjectInsert(TeacherVO teacherVO) {
		return examMapper.insertSubject(teacherVO);
	}
	
	// 과목 삭제
	@Override
	public Map<String, Object> subjectDelete(TeacherVO teacherVO) {
		Map<String, Object> map = new HashMap<>();
		int result = examMapper.deleteSubject(teacherVO.getSubjectId());
		
		if(result == 1) {
			map.put("subjectId", teacherVO.getSubjectId());
		}
		return map;
	}
	
	// 문제 필터링 출력
	@Override
	public List<TeacherVO> getQuizFilter(String sName) {
		return examMapper.filterQuiz(sName);
	}
	
	// 문제 자세히보기 단건조회
	@Override
	public List<TeacherVO> getQuizInfo(int qId) {
		return examMapper.infoQuiz(qId);
	}
}
