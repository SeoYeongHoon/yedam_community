package com.yedam.app.yedam_homework.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yedam.app.yedam_homework.service.HomeWorkReplyService;
import com.yedam.app.yedam_homework.service.HomeWorkVO;

@Controller
public class HomeWorkReplyController {

	/*
	 * @Autowired HomeWorkReplyService homeworkReplyService;
	 * 
	 * //댓글 등록 - 처리
	 * 
	 * @PostMapping("insertReply") public String insertReply (HomeWorkVO homeworkVO,
	 * Model model) { int homeworkId = homeworkReplyService.replyInsert(homeworkVO);
	 * String uri = null; if(homeworkId > -1) {
	 * uri="redirect:homeworkInfo?homeworkId=" + homeworkId; }else {
	 * uri="homeworkList"; }
	 * 
	 * return uri; }
	 */
}
