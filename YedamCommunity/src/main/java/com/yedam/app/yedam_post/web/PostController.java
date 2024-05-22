package com.yedam.app.yedam_post.web;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.yedam_post.service.Comment;
import com.yedam.app.yedam_post.service.Post;
import com.yedam.app.yedam_post.service.PostService;
import com.yedam.app.yedam_post.service.Reply;
import com.yedam.app.yedam_post.service.Report;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	/**
	 * 게시글 리스트 조회
	 * @param page     현재 페이지 번호, 기본값은 1
	 * @param pageSize 한 페이지당 게시글 수, 기본값은 10
	 * @param Model    객체
	 * @return 게시글 리스트 페이지
	 */
	@GetMapping("/postList")
	public String postList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize,
			Model model) {
		List<Post> list = postService.getAllPosts();
		list = postService.getPosts(page, pageSize);
		int totalCount = postService.getPostCount();

		model.addAttribute("postList", list);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", pageSize);

		return "posts/postlist";
	}

	/**
	 * 게시글 단건 조회
	 * 
	 * @param postId 조회할 게시글의 ID
	 * @param Model  객체
	 * @return 게시글 상세 페이지
	 */
	@GetMapping("/postInfo")
	public String getPostDetail(Post post, int postId, Model model) {
		// 게시글단건 조회
		postService.PostViewCnt(postId);
		Post postfind = postService.getPostReplies(post);

		// 댓글 조회
		List<Reply> replylist = postService.getPostReply(postfind);
		for (Reply reply : replylist) {
			// 대댓글 조회
			List<Comment> commentlist = postService.getPostComment(reply);
			reply.setComments(commentlist);
		}
		postfind.setReplies(replylist);
		
		model.addAttribute("postInfo", postfind);
		return "posts/postInfo";
	}

	/**
	 * 게시글 등록
	 * 
	 * @param model Spring MVC의 Model 객체
	 * @return 게시글 등록 페이지
	 */
	@GetMapping("/postInsert")
	public String postInsertForm(Model model) {
		Post post = new Post();
		post.setBoardId(1);; // 게시판 1번
		post.setUserId(1); // 유저 1번
		model.addAttribute("post", post);
		return "posts/postinsert";
	}

	/**
	 * 게시글 등록 처리
	 * 
	 * @param post 등록할 게시글 객체
	 * @return 게시글 리스트 페이지로 리다이렉트
	 */
	@PostMapping("/postInsert")
	public String postInsertProcess(Post post, @RequestParam("file") MultipartFile file) {
		try {
			if (!file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				String filePath = "C:/upload/picture/" + fileName;
				File dest = new File(filePath);
				file.transferTo(dest);

				post.setBoardfileName(fileName);
				post.setBoardfileSize(file.getSize());
				post.setBoardfileLocation(filePath);
				post.setBoardfileExt(fileName.substring(fileName.lastIndexOf('.') + 1));
			}
			post.setCreateDate(new Date());
			post.setUpdateDate(new Date());
			postService.createPost(post);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:postList";
	}

	/**
	 * 게시글 삭제 처리
	 * 
	 * @param postId 삭제할 게시글의 ID
	 * @param Model  객체
	 * @return 게시글 리스트 페이지로 리다이렉트
	 */
	@GetMapping("/postDelete")
	public String postDelete(@RequestParam("postId") int postId, Model model) {
		Post post = new Post();
		post.setPostId(postId);
		Map<String, Object> resultMap = postService.PostDelete(post);

		if ("success".equals(resultMap.get("status"))) {
			model.addAttribute("message", "게시글이 성공적으로 삭제되었습니다.");
		} else {
			model.addAttribute("message", "게시글 삭제 중 오류가 발생했습니다: " + resultMap.get("error"));
		}

		return "redirect:postList";
	}

	/**
	 * 게시글 업데이트
	 * 
	 * @param postId 업데이트할 게시글의 ID
	 * @param Model  객체
	 * @return 게시글 업데이트 페이지
	 */
	@GetMapping("/postUpdate")
	public String postUpdateForm(Post post, Model model) {
		if (post == null) {
			return "redirect:postList";
		}
		postService.getPostReplies(post);
		if (post == null) {
			return "redirect:postList";
		}
		model.addAttribute("postInfo", post);
		return "posts/postUpdate";
	}

	/**
	 * 게시글 업데이트 처리
	 * 
	 * @param post 업데이트할 게시글 객체
	 * @return 성공 또는 오류 메시지를 담은 Map 객체
	 */
	@PostMapping("/postUpdate")
	@ResponseBody
	public Map<String, Object> postUPdateProcess(@RequestBody Post post) {
		post.setUpdateDate(new Date());
		return postService.PostUpdate(post);
	}
	
	/**
     * 게시글 신고 처리
     * @param postId 신고할 게시글 ID
     * @param reportReason 신고 이유
     * @param userId 신고한 사용자 ID
     * @return 성공 메시지
     * @throws Exception 예외 처리
     */
    @PostMapping("/reportPost")
    @ResponseBody
    public String reportPost(@RequestParam("postId") int postId,
                             @RequestParam("reportType") int reportType,
                             @RequestParam("boardId") int boardId,
                             @RequestParam("reporter") String reporter
                             ){
        Report report = new Report();
        report.setReporter(reporter);
        report.setProcess(0);
        report.setReportType(reportType);
        report.setBoardId(1);
        report.setPostId(postId);
        postService.createReport(report);
        return "success";
    }

	// 작업중
	@PostMapping("")
	@ResponseBody
	public int updateLike(@RequestParam int postId, @RequestParam String userId){
		int likeCheck = postService.likeCheck(postId, userId);
		if (likeCheck == 0) {
			// 좋아요 처음 누름
			postService.updateLike(postId); // 게시판 테이블 +1
			postService.updateLikeCheck(postId, userId); // likes 테이블 구분자 1
		} else if (likeCheck == 1) {
			postService.updateLikeCheckCancel(postId, userId); // likes 테이블 구분자 0
			postService.updateLikeCancel(postId); // 게시판 테이블 -1
		}
		return likeCheck;
	}

}
