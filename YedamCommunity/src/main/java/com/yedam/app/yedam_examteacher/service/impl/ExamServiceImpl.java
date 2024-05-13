package com.yedam.app.yedam_examteacher.service.impl;

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
	
	// 시험목록 출력
	@Override
	public List<TeacherVO> testList() {
		return examMapper.selectExamAll();
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
	
	// 등록된 문제 출력
	 @Override 
	 public List<TeacherVO> quizList() { 
		return examMapper.selectQuizAll(); 
	}
	 
	// 등록된 지문 출력
	@Override
	public List<TeacherVO> answerList() {
		return examMapper.selectAnswerAll();
	}
	
	// 문제 상세 보기
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
}
