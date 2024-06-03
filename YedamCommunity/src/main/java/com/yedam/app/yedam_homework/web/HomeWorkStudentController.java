package com.yedam.app.yedam_homework.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.yedam_common.LoginUserVO;
import com.yedam.app.yedam_common.PageDTO;
import com.yedam.app.yedam_curriculum.service.CurriculumService;
import com.yedam.app.yedam_curriculum.service.CurriculumVO;
import com.yedam.app.yedam_homework.service.CommentVO;
import com.yedam.app.yedam_homework.service.HomeWorkReplyService;
import com.yedam.app.yedam_homework.service.HomeWorkService;
import com.yedam.app.yedam_homework.service.HomeWorkVO;
import com.yedam.app.yedam_homework.service.ReplyVO;
import com.yedam.app.yedam_homework.upload.service.HomeWorkFileService;
import com.yedam.app.yedam_homework.upload.service.HomeWorkFileVO;
import com.yedam.app.yedam_homework.upload.service.ReplyFileVO;
import com.yedam.app.yedam_subjects.service.SubjectService;

@Controller
@RequestMapping("/all")
public class HomeWorkStudentController {
	
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
		// 과제 목록(학생)
		// ----------------
		@GetMapping("/homework_S")
		public String homeworksList(Model model,
				 					Authentication authentication) {
			LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
			System.err.println("유저 아이디 = "+userVO.getuserId());
			model.addAttribute("userId",userVO.getuserId());
			List<CurriculumVO> subjects = curriculumService.subjectList(userVO.getuserId());
			model.addAttribute("subject", subjects);
			return "homework/homeworkList_s"; // 출력할 페이지
		}

		// 과제 목록 출력
		@GetMapping("/homeworkList_S")
		@ResponseBody
		public List<HomeWorkVO> homeworkListS(@RequestParam("userid") int userid) {
			List<HomeWorkVO> userhomework = homeworkService.userHomeworkList(userid);
			System.err.println(userhomework);
			return userhomework;
		}

		// 과목 카테고리
		@GetMapping("/subjectCategory")
		@ResponseBody
		public List<HomeWorkVO> subjects(@RequestParam("vals") int classId, @RequestParam("userid") int userId) {
			List<HomeWorkVO> list = homeworkService.subjectCategory(classId, userId);
			return list;
		}
		
		// ----------------
		// 과제상세페이지
		// ----------------
		@GetMapping("/homeworkInfo")
		public String homeworkInfo(HomeWorkVO homeworkVO, 
								   Model model,
								   Authentication authentication) {
			LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
			System.err.println("유저타입= "+userVO.getUserType());
			model.addAttribute("userType",userVO.getUserType());
			model.addAttribute("userName",userVO.getUsername());
			// 과제 상세 조회
			HomeWorkVO findVO = homeworkService.homeworkInfo(homeworkVO);
			model.addAttribute("homeworkList", findVO);
			ReplyVO reply = new ReplyVO();
			reply.setReplyId(findVO.getReplyId());

			return "homework/homeworkInfo";
		}
		
		// 과제 파일 조회
		@GetMapping("/homeworkFileList")
		@ResponseBody
		public List<HomeWorkFileVO> homeworkInfoFile(HomeWorkVO homeworkVO) {
			return homeworkfileService.homeworkfileList(homeworkVO);
		}
		
		

		// ----------------
		// 댓글 조회
		// ----------------
		@GetMapping("/replyList")
		@ResponseBody
		public List<ReplyVO> replyList(@RequestParam("targetId") int homeworkTargetId) {
			List<ReplyVO> replies = homeworkReplyService.replyList(homeworkTargetId);

			return replies;
		}

		// ----------------
		// 댓글 파일 다운로드
		// ----------------
		@GetMapping("/replyfile")
		@ResponseBody
		public List<ReplyFileVO> replyfile(@RequestParam("replyId") int replyId) {
			List<ReplyFileVO> replyfile = homeworkfileService.replyfileList(replyId);
			return replyfile;
		}

		// ----------------
		// 댓글(파일) 등록 - 처리
		// ----------------
		@PostMapping("/insertReply")
		@ResponseBody
		public ReplyVO insertReply(@RequestPart MultipartFile[] uploadFiles, 
												ReplyVO replyVO, 
												Model model,
												Authentication authentication) {
			LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
			replyVO.setReplyWriter(userVO.getUsername());
			// 댓글등록
			homeworkReplyService.replyInsert(replyVO);
			int replyId = replyVO.getReplyId();
			homeworkfileService.replyUploadFile(uploadFiles, replyId);
			return replyVO;
		}

		// ----------------
		// 대댓글 등록 - 처리
		// ----------------
		@PostMapping("/insertComment")
		@ResponseBody
		public CommentVO insertComment(@RequestParam String content, 
									   @RequestParam int replyId,
									   Authentication authentication) {
			LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
			CommentVO comment = new CommentVO();
			comment.setReplyId(replyId);
			comment.setCommentContent(content);
			comment.setCommentWriter(userVO.getUsername());
			// 대댓글 등록
			homeworkReplyService.commentInsert(comment);
			return comment;
		}

		// ----------------
		// 대댓글 삭제 - 처리
		// ----------------
		@DeleteMapping("/deleteComment")
		@ResponseBody
		public int deleteComment(@RequestParam int commentId) {
			return homeworkReplyService.commentDelete(commentId);
		}
		

		// ----------------
		// 댓글 삭제 - 처리
		// ----------------
		@DeleteMapping("/deleteReply")
		@ResponseBody
		public int deleteReply(@RequestParam int replyId) {
			return homeworkReplyService.replyDelete(replyId);
		}
		
		
		// ----------------
		// 댓글 업데이트 - 처리
		// ----------------
		@PutMapping("/updateReply")
		@ResponseBody
		public Map<String, Object> updateReply (ReplyVO replyVO ){
			return homeworkReplyService.replyUpdate(replyVO);
		}
		
		// ----------------
		// 대댓글 업데이트 - 처리
		// ----------------
		@PutMapping("/updateComment")
		@ResponseBody
		public Map<String, Object> updateComment (@RequestParam("content")  String content,
												@RequestParam("commentId") int commentId) {
			
			CommentVO commentVO = new CommentVO();
			commentVO.setCommentContent(content);
			commentVO.setCommentId(commentId);
			return homeworkReplyService.commentUpdate(commentVO);
		}
		
		// ----------------
		// 과제 파일 삭제
		// ----------------
		@PostMapping("/deleteFile")
		@ResponseBody
		public  Map<String, Object> fileDelete (HomeWorkFileVO homeworkfileVO) {
			homeworkfileService.deleteFile(homeworkfileVO);
			return null;
		}
		
		// ----------------
		// 댓글과제 파일 삭제
		// ----------------
		@PostMapping("/deleteReplyFile")
		@ResponseBody
		public  Map<String, Object> replyFileDelete (ReplyFileVO replyfileVO) {
			Map<String, Object> map = new HashMap<>();
			map.put("replyId", replyfileVO.getReplyId());
			homeworkfileService.deleteReplyFile(replyfileVO);
			return map;
		}
		
		//--------------
		// 댓글 파일 업로드
		//--------------
			@PostMapping("/updateReplyFile")
			@ResponseBody
			public String updateReplyFile(@RequestPart MultipartFile[] uploadReplyFile,
										 @RequestParam("replyId") int replyId) {
				System.err.println("댓글 업로드 파일 ==" + uploadReplyFile);
				// 과제 파일 업로드
				homeworkfileService.replyUploadFile(uploadReplyFile, replyId);
				return null;
			}
			
			// ----------------
			// 과제 필터링 및 페이징
			// ----------------
			@GetMapping("/filterHomeworkStudent")
			@ResponseBody
			public Map<String, Object> filterHomeworks(@RequestParam("filter") int filter,
													   @RequestParam(defaultValue = "1") int page,
													   @RequestParam(defaultValue = "") String searchQuery,
													   @RequestParam("userId") int userId) {
				List<HomeWorkVO> homeworks = homeworkService.getHomeworksByFilterStudent(filter, page, searchQuery,userId);
				int totalCnt = homeworkService.getTotalCnt(filter, searchQuery);
				
				PageDTO pageDTO = new PageDTO(page, totalCnt, 5);
				
				Map<String, Object> response = new HashMap<>();
				response.put("homeworks", homeworks);
				response.put("page", pageDTO);
				
				
				return response;
			}
}
