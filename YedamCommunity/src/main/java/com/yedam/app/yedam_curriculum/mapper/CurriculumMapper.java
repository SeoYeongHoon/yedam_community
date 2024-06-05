package com.yedam.app.yedam_curriculum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yedam.app.yedam_curriculum.service.CurriculumVO;
import com.yedam.app.yedam_user.service.UserVO;

@Mapper
public interface CurriculumMapper {

	// 수강과정 리스트
	public List<CurriculumVO> cSelectAll();
	
	// 수강과정 등록
	public int insertCurriculum(CurriculumVO curriculumVO);
	
	// 과정별 유저 목록 출력
	public List<UserVO> selectCurriculumStd(@Param("curriculumId") int curriculumId);

	public int removeCurriculum(int curriculumId);
	
	// 과정 선택 리스트(회원가입 때 사용)
	public List<CurriculumVO> selectAllCurriculums();
	
	//해당 과정 과목명 조회
	public List<CurriculumVO> subjectSelectAll(int userid);
	
	// 과정별 과목명 조회
	public List<CurriculumVO> selectSubject(CurriculumVO curriculumVO);
}
