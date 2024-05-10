package com.yedam.app.yedam_examteacher.service.impl;

import java.util.List;

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
	
	@Override
	public List<TeacherVO> testList() {
		return examMapper.selectExamAll();
	}
	
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
	
	@Override
	public List<TeacherVO> quizList() {
		return examMapper.selectQuizAll();
	}
	
	@Override
	public List<TeacherVO> answerList() {
		return examMapper.selectAnswerAll();
	}
}
