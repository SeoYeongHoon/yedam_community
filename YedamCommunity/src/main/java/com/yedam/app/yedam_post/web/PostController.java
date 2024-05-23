package com.yedam.app.yedam_post.web;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
/**
 * 
 * 임우열
 *
 */
@Controller
public class PostController {
	
	// --------------------------------------------
	// 로컬 저장 경로
	// --------------------------------------------
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Autowired
	private PostService postService;

	// --------------------------------------------
	// 게시글 리스트 조회
	// --------------------------------------------
	@GetMapping("/postList")
	public String postList(@RequestParam(defaultValue = "1") int page, 
			               @RequestParam(defaultValue = "10") int pageSize,
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

	// --------------------------------------------
	// 게시글 단건 조회
	// --------------------------------------------
	@GetMapping("/postInfo")
	public String getPostDetail(Post post, int postId, Model model) {

		// 조회수 업데이트
		postService.PostViewCnt(postId);
		
		// 게시글 단건 조회
		Post postfind = postService.getPostReplies(postId);
		
		// 댓글 조회
		List<Reply> replylist = postService.getPostReply(postId);
		for (Reply reply : replylist) {
			
			// 대댓글 조회
			List<Comment> commentlist = postService.getPostComment(reply);
			reply.setComments(commentlist);
		}
		postfind.setReplies(replylist);

		model.addAttribute("postInfo", postfind);
		return "posts/postInfo";
	}

	// --------------------------------------------
	// 게시글 등록
	// --------------------------------------------
	@GetMapping("/postInsert")
	public String postInsertForm(Model model) {

		Post post = new Post();
		post.setBoardId(1);
		; // 게시판 1번
		post.setUserId(1); // 유저 1번
		model.addAttribute("post", post);
		return "posts/postinsert";
	}

	// --------------------------------------------
	// 게시글 등록 처리
	// --------------------------------------------
	@PostMapping("/postInsert")
	public String postInsertProcess(Post post, @RequestParam("file") MultipartFile file) {

		try {
			if (!file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				
				
				String filePath = uploadPath + fileName;
				File dest = new File(filePath);
				file.transferTo(dest);

				post.setBoardfileName(fileName);
				post.setBoardfileSize(file.getSize());
				post.setBoardfileLocation(fileName);
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

	// --------------------------------------------
	// 게시글 삭제 처리
	// --------------------------------------------
	@GetMapping("/postDelete")
	public String postDelete(@RequestParam("postId") int postId) {

		Post post = new Post();
		post.setPostId(postId);
		postService.PostDelete(post);

		return "redirect:postList";
	}

	// --------------------------------------------
	// 게시글 업데이트
	// --------------------------------------------
	@GetMapping("/postUpdate")
	public String postUpdateForm(Integer postId, Model model) {

		if (postId == null) {
			return "redirect:postList";
		}
		Post post = postService.getPostReplies(postId);
		model.addAttribute("postInfo", post);
		return "posts/postUpdate";
	}

	// --------------------------------------------
	// 게시글 업데이트 처리
	// --------------------------------------------
	@PostMapping("/postUpdate")
	@ResponseBody
	public Map<String, Object> postUPdateProcess(@RequestBody Post post) {

		post.setUpdateDate(new Date());
		return postService.PostUpdate(post);
	}

	// --------------------------------------------
	// 게시글 신고 처리
	// --------------------------------------------
	@PostMapping("/reportPost")
	@ResponseBody
	public String reportPost(Report report) {
		try {
			report.setProcess(0);
			postService.createReport(report);
		} catch (Exception e) {
			e.getStackTrace();
		}
		return "success";
	}

	// --------------------------------------------
	// 추천버튼 작업중
	// --------------------------------------------
	@PostMapping("/boardLike")
	@ResponseBody
	public int updateLike(@RequestParam("postId") int postId, 
			              @RequestParam("userId") int userId) {
		
		Integer likeCheck = postService.likeCheck(postId, userId);
		if (likeCheck == null) {
			postService.createLike(postId, userId); // 게시판의 추천을 눌렀을시 boardlikes 테이블에 insert
			postService.updateLike(postId); // 게시판 테이블 +1
		} else {
			postService.LikeDelete(postId, userId); // boardlikes 테이블에 DELETE
			postService.updateLikeCancel(postId); // 게시판 테이블 -1
		}
		return likeCheck;
	}

}
