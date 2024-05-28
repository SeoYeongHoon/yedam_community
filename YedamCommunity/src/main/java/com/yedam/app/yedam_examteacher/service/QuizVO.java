package com.yedam.app.yedam_examteacher.service;

import lombok.Data;

@Data
public class QuizVO {
	String[] quizContent;
	int [] quizId;
	int [] quizScore;
	String[] subjectName;
	int curriculumId;
}
