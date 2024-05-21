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
}
