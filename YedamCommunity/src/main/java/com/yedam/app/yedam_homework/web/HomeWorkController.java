package com.yedam.app.yedam_homework.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.yedam_homework.service.CommentVO;
import com.yedam.app.yedam_homework.service.HomeWorkReplyService;
import com.yedam.app.yedam_homework.service.HomeWorkService;
import com.yedam.app.yedam_homework.service.HomeWorkTargetVO;
import com.yedam.app.yedam_homework.service.HomeWorkVO;
import com.yedam.app.yedam_homework.service.ReplyVO;
import com.yedam.app.yedam_homework.upload.service.HomeWorkFileService;
import com.yedam.app.yedam_subjects.service.SubjectService;

@Controller
public class HomeWorkController {

	// 로컬 저장 경로
	@Value("${file.upload.path}")
	private String uploadPath;

	// 서비스 호출
	@Autowired
	HomeWorkService homeworkService;

	@Autowired
	SubjectService subjectService;

	@Autowired
	HomeWorkReplyService homeworkReplyService;
	
	@Autowired
	HomeWorkFileService homeworkfileService;

	// 과제 전체조회
	@GetMapping("homeworkList")
	public String homeworkList(Model model) {
		List<HomeWorkVO> list = homeworkService.homeworkList();
		model.addAttribute("homeworklist", list);
		return "homework/homeworkList_t"; // 출력할 페이지
	}

	// 과제 등록 - 페이지
	@GetMapping("homeworkInsert")
	public String homeworkInsertForm(HomeWorkVO homeworkVO, Model model) {
		List<HomeWorkVO> subjectNameList = homeworkService.subjectNameList(homeworkVO);
		model.addAttribute("subject", subjectNameList);
		model.addAttribute("homeworkVO", homeworkVO);
		return "homework/homeworkInsert";
	}

	// 과제 등록 -처리
	@PostMapping("homeworkInsert")
	public String homeworkInsertProcess(@RequestPart MultipartFile[] uploadFiles,  HomeWorkVO homeworkVO, HomeWorkTargetVO homeworktargetVO) {
		homeworkService.homeworkInsert(homeworkVO);
		HomeWorkTargetVO list = homeworkService.homeworktargetList(homeworkVO);
		homeworktargetVO.setHomeworkId(list.getHomeworkId());
		homeworktargetVO.setCurriculumId(list.getCurriculumId());
		homeworkService.homeworkTargetInsert(homeworktargetVO);
		homeworkfileService.uploadFile(uploadFiles, list);
		
		return "redirect:homeworkList";
	}

	// 과제상세페이지
	@GetMapping("homeworkInfo")
	public String homeworkInfo(HomeWorkVO homeworkVO, Model model) {

		// 과제 상세 조회
		HomeWorkVO findVO = homeworkService.homeworkInfo(homeworkVO);

		// 댓글조회
		List<ReplyVO> replyList = homeworkReplyService.replyList(findVO);
		for (ReplyVO reply : replyList) {
			// 대댓글 조회
			List<CommentVO> commentList = homeworkReplyService.commentList(reply);
			reply.setCommentList(commentList);
		}
		findVO.setReplyList(replyList);
		
		model.addAttribute("homeworkList", findVO);
		System.err.println("findVO = "+findVO);
		return "homework/homeworkInfo";
	}

	// 댓글 등록 - 처리
	@PostMapping("insertReply")
	public String insertReply(HomeWorkVO homeworkVO, Model model) {
		homeworkReplyService.replyInsert(homeworkVO);
		return "redirect:homeworkInfo?homeworkId=" + homeworkVO.getHomeworkId();
	}

	// 대댓글 등록 - 처리
	@PostMapping("insertComment")
	public String insertComment(HomeWorkVO homeworkVO, Model model) {
		System.err.println("과제번호 들고있니? " + homeworkVO);
		homeworkReplyService.commentInsert(homeworkVO);
		return "redirect:homeworkInfo?homeworkId=" + homeworkVO.getHomeworkId();
	}
}
