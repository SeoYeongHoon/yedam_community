package com.yedam.app.yedam_homework.upload.mapper;

import java.util.List;

import com.yedam.app.yedam_homework.upload.service.HomeWorkFileVO;

public interface HomeWorkFileMapper {
	
	 //과제 파일 등록
	public List<String> insertHomeWorkFile(HomeWorkFileVO homeworkfileVO);
}
