package com.yedam.app.yedam_post.web;

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

import com.yedam.app.yedam_post.service.Post;
import com.yedam.app.yedam_post.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	/**
     * 게시글 리스트 조회
     * @param page 현재 페이지 번호, 기본값은 1
     * @param pageSize 한 페이지당 게시글 수, 기본값은 10
     * @param Model 객체
     * @return 게시글 리스트 페이지
     */
	@GetMapping("postList")
	public String postList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize, Model model) {
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
     * @param postId 조회할 게시글의 ID
     * @param Model 객체
     * @return 게시글 상세 페이지
     */
	@GetMapping("postInfo")
	public String getPostDetail(@RequestParam("postId") int postId, Model model) {
		// 게시글과 댓글 정보를 조회합니다.
		Post post = postService.getPostReplies(postId);
		model.addAttribute("postInfo", post);
		return "posts/postInfo";
	}

	/**
     * 게시글 등록 
     * @param model Spring MVC의 Model 객체
     * @return 게시글 등록 페이지
     */
	@GetMapping("postInsert")
	public String postInsertForm(Model model) {
		Post post = new Post();
		post.setBoardId(1); // 게시판 1번
		post.setUserId(1); // 유저 1번
		model.addAttribute("post", post);
		return "posts/postinsert";
	}

	/**
     * 게시글 등록 처리
     * @param post 등록할 게시글 객체
     * @return 게시글 리스트 페이지로 리다이렉트
     */
	@PostMapping("postInsert")
	public String postInsertProcess(Post post) {
		post.setCreateDate(new Date());
		post.setUpdateDate(new Date());
		postService.createPost(post);
		return "redirect:postList";
	}

	/**
     * 게시글 삭제 처리
     * @param postId 삭제할 게시글의 ID
     * @param Model 객체
     * @return 게시글 리스트 페이지로 리다이렉트
     */
	@GetMapping("postDelete")
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
     * @param postId 업데이트할 게시글의 ID
     * @param Model 객체
     * @return 게시글 업데이트 페이지
     */
	@GetMapping("postUpdate")
	public String postUpdateForm(@RequestParam Integer postId, Model model) {
		if (postId == null) {
			return "redirect:postList";
		}
		Post post = postService.getPostReplies(postId);
		if (post == null) {
			return "redirect:postList";
		}
		model.addAttribute("postInfo", post);
		return "posts/postUpdate";
	}

	/**
     * 게시글 업데이트 처리
     * @param post 업데이트할 게시글 객체
     * @return 성공 또는 오류 메시지를 담은 Map 객체
     */
	@PostMapping("postUpdate")
	@ResponseBody
	public Map<String, Object> postUPdateProcess(@RequestBody Post post) {
		post.setUpdateDate(new Date());
		return postService.PostUpdate(post);
	}
	
}
