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
	
	// 게시글 리스트 조회
	@GetMapping("postList")
	public String postList(Model model) {
		List<Post> list = postService.getAllPosts();
		model.addAttribute("postList", list);
		return "posts/postlist";
	}

	// 게시글 단건조회
	@GetMapping("postInfo")
    public String getPostDetail(@RequestParam("postId") int postId, Model model) {
        // 게시글과 댓글 정보를 조회합니다.
        Post post = postService.getPostReplies(postId);
        model.addAttribute("postInfo", post);
        return "posts/postInfo";
    }

	// 게시글 등록
	@GetMapping("postInsert")
	public String postInsertForm(Model model) {
		Post post = new Post();
		post.setBoardId(1); // 게시판 1번
		post.setUserId(1); // 유저 1번
		model.addAttribute("post", post);
		return "posts/postinsert";
	}
	
	// 게시글 등록 처리 
	@PostMapping("postInsert")
	public String postInsertProcess(Post post) {
		post.setCreateDate(new Date());
		post.setUpdateDate(new Date());
		postService.createPost(post);
		return "redirect:postList";
	}
	
	// 게시글 삭제
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

	// 게시글 업데이트
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
	
	// 게시글 업데이트 처리
	@PostMapping("postUpdate")
	@ResponseBody
	public Map<String, Object> postUPdateProcess(@RequestBody Post post) {
		post.setUpdateDate(new Date());
		return postService.PostUpdate(post);
	}

}
