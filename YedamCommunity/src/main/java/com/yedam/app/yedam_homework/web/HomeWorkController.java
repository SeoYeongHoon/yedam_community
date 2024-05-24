package com.yedam.app.yedam_homework.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.yedam_curriculum.service.CurriculumService;
import com.yedam.app.yedam_curriculum.service.CurriculumVO;
import com.yedam.app.yedam_homework.service.CommentVO;
import com.yedam.app.yedam_homework.service.HomeWorkReplyService;
import com.yedam.app.yedam_homework.service.HomeWorkService;
import com.yedam.app.yedam_homework.service.HomeWorkTargetVO;
import com.yedam.app.yedam_homework.service.HomeWorkVO;
import com.yedam.app.yedam_homework.service.ReplyVO;
import com.yedam.app.yedam_homework.upload.service.HomeWorkFileService;
import com.yedam.app.yedam_homework.upload.service.HomeWorkFileVO;
import com.yedam.app.yedam_homework.upload.service.ReplyFileVO;
import com.yedam.app.yedam_subjects.service.SubjectService;

/**
 * 과제 관리
 * 
 * @author 전영재 개발 일자 202405-23
 * 
 */
// /teacher/~~
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

	@Autowired
	CurriculumService curriculumService;

	// ----------------
	// 과제 목록(교수)
	// ----------------
	@GetMapping("homeworkT")
	public String homework(Model model) {
		List<CurriculumVO> classList = curriculumService.CurriculumList();
		model.addAttribute("classId", classList);
		return "homework/homeworkList_t";
	}
	
	@GetMapping("classCategoryT")
	@ResponseBody
	public List<HomeWorkVO> homeworks (@RequestParam("vals") int classId) {
		List<HomeWorkVO> list = homeworkService.classCategory(classId);
		return list;
	}
	
	@GetMapping("homeworkListT")
	@ResponseBody
	public List<HomeWorkVO> homeworkList(@RequestParam("homeworkAll") String homeworkAll) {
		List<HomeWorkVO> homeworkList	= homeworkService.homeworkList();
		return homeworkList;
	}

	
	// ----------------
	// 과제 목록(학생)
	// ----------------
	@GetMapping("homeworkListS")
	public String homeworksList(Model model) {
		// 과제 조회
		//List<HomeWorkVO> list = homeworkService.homeworkList();
		//model.addAttribute("homeworklist", list);
		return "homework/homeworkList_s"; // 출력할 페이지
	}

	// ----------------
	// 과제 등록 - 페이지
	// ----------------
	@GetMapping("homeworkInsert")
	public String homeworkInsertForm(HomeWorkVO homeworkVO, Model model) {
		// 과목 조회
		List<HomeWorkVO> subjectNameList = homeworkService.subjectNameList(homeworkVO);
		model.addAttribute("subject", subjectNameList);
		model.addAttribute("homeworkVO", homeworkVO);
		return "homework/homeworkInsert";
	}

	// ----------------
	// 과제 등록 -처리
	// ----------------
	@PostMapping("homeworkInsert")
	@ResponseBody
	public String homeworkInsertProcess(@RequestPart MultipartFile[] uploadFiles, HomeWorkVO homeworkVO,
			HomeWorkTargetVO homeworktargetVO) {

		// 과제등록
		homeworkService.homeworkInsert(homeworkVO);

		// 타겟 등록
		HomeWorkTargetVO targetVo = homeworkService.homeworktargetList(homeworkVO);
		homeworktargetVO.setHomeworkId(targetVo.getHomeworkId());
		homeworktargetVO.setCurriculumId(targetVo.getCurriculumId());
		homeworkService.homeworkTargetInsert(homeworktargetVO);

		// 과제 파일 업로드
		homeworkfileService.homeworkUploadFile(uploadFiles, targetVo);

		return null;
	}

	// ----------------
	// 과제상세페이지
	// ----------------
	@GetMapping("homeworkInfo")
	public String homeworkInfo(HomeWorkVO homeworkVO, Model model) {
		// 과제 상세 조회
		HomeWorkVO findVO = homeworkService.homeworkInfo(homeworkVO);
		model.addAttribute("homeworkList", findVO);
		ReplyVO reply = new ReplyVO();
		reply.setReplyId(findVO.getReplyId());
		// 과제 파일 조회
		List<HomeWorkFileVO> file = homeworkfileService.homeworkfileList(homeworkVO);
		model.addAttribute("files", file);

		return "homework/homeworkInfo";
	}

	// ----------------
	// 댓글 조회
	// ----------------
	@GetMapping("replyList")
	@ResponseBody
	public List<ReplyVO> replyList(@RequestParam("targetId") int homeworkTargetId) {

	    List<ReplyVO> replies = homeworkReplyService.replyList(homeworkTargetId);
	    
	    return replies;
	}
	
	// ----------------
	// 댓글 파일 다운로드
	// ----------------
	@GetMapping("replyfile")
	@ResponseBody
	public List<ReplyFileVO> replyfile (@RequestParam("replyId") int replyId){
		List<ReplyFileVO> replyfile = homeworkfileService.replyfileList(replyId);
		return replyfile;
	}
	
	
	// ----------------
	// 대댓글 조회
	// ----------------
	@GetMapping("commentList")
	@ResponseBody
	public List<CommentVO> commentList(@RequestParam("replyId") int replyId){
		
		 List<CommentVO> comments = homeworkReplyService.commentList(replyId); 
		
		return comments;
	}
	
	
	// ----------------
	// 댓글(파일) 등록 - 처리
	// ----------------
	@PostMapping("insertReply")
	@ResponseBody
	public ReplyVO insertReply(@RequestPart MultipartFile[] uploadFiles, ReplyVO replyVO, Model model) {
		replyVO.setReplyWriter("dudwo");
		// 댓글등록
		homeworkReplyService.replyInsert(replyVO);
		int replyId = replyVO.getReplyId();
		homeworkfileService.replyUploadFile(uploadFiles, replyId); 
		return replyVO;
	}

	// ---------------- 
	// 대댓글 등록 - 처리
	// ----------------
	@PostMapping("insertComment")
	@ResponseBody
	public CommentVO insertComment(@RequestParam String content, 
								@RequestParam int replyId) {
		CommentVO comment = new CommentVO(); 
		comment.setReplyId(replyId);
		comment.setCommentContent(content); 
		comment.setCommentWriter("dudwo");
		  // 대댓글 등록
		  homeworkReplyService.commentInsert(comment);
		return comment;
	}
}
