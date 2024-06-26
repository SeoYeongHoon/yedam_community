package com.yedam.app.yedam_examteacher.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.yedam_examteacher.mapper.ExamMapper;
import com.yedam.app.yedam_examteacher.service.ExamService;
import com.yedam.app.yedam_examteacher.service.QuizVO;
import com.yedam.app.yedam_examteacher.service.TeacherVO;

@Service
public class ExamServiceImpl implements ExamService {
	@Autowired
	ExamMapper examMapper;
	
	
	//--------------------------------------------
	// 시험목록 페이지 기능 모음
	//--------------------------------------------
	// 시험목록 출력
	//--------------------------------------------
	@Override
	public List<TeacherVO> testList(int cId, int page, String searchQuery) {
		Map<String, Object> params = new HashMap<>();
		params.put("classId", cId);
		params.put("page", page);
		params.put("searchQuery", searchQuery);
		return examMapper.selectExamAll(params);
	}
	@Override
	public int testListCnt(int cId, String searchQuery) {
		return examMapper.testTotalCnt(cId, searchQuery);
	}
	
	// 가장 최신에 등록된 test_id
	@Override
	public int CurrTestId() {
		return examMapper.currentTestId();
	}
	
	//--------------------------------------------
	// 시험 등록
	//--------------------------------------------
	@Override
	public int testInsert(TeacherVO teacherVO) {
		int result = examMapper.insertTest(teacherVO);

		if (result == 1) {
			return teacherVO.getTestId();
		} else {
			return -1;
		}
	} 
	//--------------------------------------------
	// 시험에 출제될 문제 등록
	//--------------------------------------------
	@Override
	public int quizboxInsert(QuizVO quizVO) {
		TeacherVO teacherVO = new TeacherVO();
		int quizSize = quizVO.getQuizContent().length;
		for(int i=0; i<quizSize; i++) {
			teacherVO.setQuizContent(quizVO.getQuizContent()[i]);
			teacherVO.setQuizId(quizVO.getQuizId()[i]);
			teacherVO.setQuizScore(quizVO.getQuizScore()[i]);
			teacherVO.setSubjectName(quizVO.getSubjectName()[i]);
			teacherVO.setCurriculumId(quizVO.getCurriculumId());
			teacherVO.setTestId(quizVO.getTestId());
			
			examMapper.insertQuizbox(teacherVO);
		}
		return 1;
	}
	//--------------------------------------------
	// 시험 대상자 등록
	//--------------------------------------------
	@Override
	public int testUserInsert(QuizVO quizVO) {
		TeacherVO teacherVO = new TeacherVO();
		int userSize = quizVO.getUserId().length;
		for(int i=0; i<userSize; i++) {
			teacherVO.setUserId(quizVO.getUserId()[i]);
			teacherVO.setTestId(quizVO.getTestId());
			
			examMapper.insertTestUser(teacherVO);
		}
		return 1;
	}
	//--------------------------------------------
	// 시험 대상자 출력
	//--------------------------------------------
	@Override
	public List<TeacherVO> userList(TeacherVO teacherVO) {
		return examMapper.selectUserAll(teacherVO);
	}
	// 재시험 대상자 출력 
	@Override
	public List<TeacherVO> reTestUserList(int tId) {
		return examMapper.reTestUser(tId);
	}
	
	//--------------------------------------------
	// 문제 조회/등록 페이지 기능 모음
	//--------------------------------------------
	// 문제 및 지문 등록
	//--------------------------------------------
	@Transactional
	@Override
	public void quizInsert(TeacherVO teacherVO) { // 객관식
		examMapper.insertQuiz(teacherVO);
		examMapper.insertAnswer(teacherVO);
		examMapper.insertAnswer2(teacherVO);
		examMapper.insertAnswer3(teacherVO);
		examMapper.insertAnswer4(teacherVO);
		examMapper.insertAnswer5(teacherVO);
	}
	@Transactional
	@Override
	public void quizInsertJu(TeacherVO teacherVO) { // 주관식
		examMapper.insertQuizJu(teacherVO);
		examMapper.insertAnswerJu(teacherVO);
	}

	//--------------------------------------------
	// 문제 지문 출력
	//--------------------------------------------
	@Override
	public List<TeacherVO> answerList(int qId) {
		return examMapper.quizAnswer(qId);
	}

	//--------------------------------------------
	// 과정명 출력(과목명 추가할때 필요)
	//--------------------------------------------
	@Override
	public List<TeacherVO> currList() {
		return examMapper.selectCurr();
	}
	//--------------------------------------------
	// 과목명 출력(과목 추가/삭제시 필요)
	//--------------------------------------------
	@Override
	public List<TeacherVO> subjectListOfCurr(int cId) {
		return examMapper.subjectOfCurr(cId);
	}
	//--------------------------------------------
	// 과목명 출력(문제 필터링시 사용)
	//--------------------------------------------
	@Override
	public List<TeacherVO> subjectList() {
		return examMapper.selectSubjectAll();
	}
	@Override
	public List<TeacherVO> subjectList2(int cId) {
		return examMapper.selectSubjectAll2(cId);
	}
	//--------------------------------------------
	// 과목 추가
	//--------------------------------------------
	@Override
	public int subjectInsert(TeacherVO teacherVO) {
		return examMapper.insertSubject(teacherVO);
	}
	//--------------------------------------------
	// 과목 삭제
	//--------------------------------------------
	@Override
	public int subjectDelete(TeacherVO teacherVO) {
		return examMapper.deleteSubject(teacherVO);
	}
	//--------------------------------------------
	// 문제 삭제
	//--------------------------------------------
	@Override
	public int quizDelete(int qId) {
		return examMapper.deleteQuiz(qId);
	}
	//--------------------------------------------
	// 문제에 등록된 지문들 수정
	//--------------------------------------------
	@Override
	public Map<String, Object> answerUpdate(TeacherVO teacherVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSucessed = false;

		int result = examMapper.updateAnswer(teacherVO);

		if (result == 1) {
			isSucessed = true;
		}
		map.put("result", isSucessed);
		map.put("target", teacherVO);
		return map;
	}
	
	//--------------------------------------------
	// 문제 필터링 출력
	//--------------------------------------------
	@Override
	public List<TeacherVO> getQuizFilter(int page, String subjectName) {
		Map<String, Object> params = new HashMap<>();
        
		params.put("page", page);
        params.put("subjectName", subjectName);
		return examMapper.filterQuiz(params);
	}
	@Override
	public int getQuizCount(String subjectName) {
		return examMapper.getQuizCnt(subjectName);
	}
	
	//--------------------------------------------
	// 문제 자세히보기 단건조회
	//--------------------------------------------
	@Override
	public List<TeacherVO> getQuizInfo(int qId) {
		return examMapper.infoQuiz(qId);
	}
	
	//--------------------------------------------
	// 교수님 메인페이지 기능 모음
	//--------------------------------------------
	//--------------------------------------------
	// 강의실별 과목평균점수 조회
	//--------------------------------------------
	@Override
	public List<TeacherVO> subjectAvg(int cId) {
		return examMapper.subjectAvg(cId);
	}
	//--------------------------------------------
	// 강의실별 시험리스트 조회
	//--------------------------------------------
	@Override
	public List<TeacherVO> subTestList(int cId) {
		return examMapper.testList(cId);
	}

	@Override
	public TeacherVO testInfo(TeacherVO teacherVO) {
		return examMapper.testInfo(teacherVO);
	}
	//--------------------------------------------
	// 강의실별 수강생 조회
	//--------------------------------------------
	@Override
	public List<TeacherVO> subUserList(int cId) {
		return examMapper.userList(cId);
	}
	//--------------------------------------------
	// 강의실에 따른 과정명 조회
	//--------------------------------------------
	@Override
	public List<TeacherVO> classList(int cId) {
		return examMapper.currList(cId);
	}
	//--------------------------------------------
	// 강의실별 시험결과 조회
	//--------------------------------------------
	@Override
	public List<TeacherVO> userTestResult(int tId) {
		return examMapper.testResult(tId);
	}
	//--------------------------------------------
	// 수강생 개개인의 과정정보 단건조회
	//--------------------------------------------
	@Override
	public TeacherVO userTestInfo(TeacherVO teacherVO) {
		return examMapper.userTestInfo(teacherVO);
	}
	//--------------------------------------------
	// 개개인의 시험 과목점수 리스트 조회
	//--------------------------------------------
	@Override
	public List<TeacherVO> userScoreList(int uId) {
		return examMapper.userScore(uId);
	}
	
	//--------------------------------------------
	// 유저 피드백 작성페이지 기능 모음
	//--------------------------------------------
	// 유저 시험정보 확인
	//--------------------------------------------
	@Override
	public TeacherVO userFeedInfo(TeacherVO teacherVO) {
		return examMapper.userFeed(teacherVO);
	}
	//--------------------------------------------
	// 피드백 작성
	//--------------------------------------------
	@Override
	public Map<String, Object> feedUpdate(TeacherVO teacherVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSucessed = false;

		int result = examMapper.feedAdd(teacherVO);

		if (result == 1) {
			isSucessed = true;
		}
		map.put("result", isSucessed);
		map.put("target", teacherVO);
		return map;
	}
}
