package com.yedam.app.yedam_examteacher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	public int quizInsert(TeacherVO teacherVO) {
		return examMapper.insertQuiz(teacherVO);
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
