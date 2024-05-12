package com.yedam.app.yedam_post.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.yedam_post.service.Comment;
import com.yedam.app.yedam_post.service.PostService;

@Controller
public class CommentController {
	
	@Autowired
	private PostService postService;

	@PostMapping("addComment")
	public String addComment(@RequestParam("postId") int postId, Comment comment) {
		comment.setPostId(postId);
		comment.setCreateDate(new Date());
		comment.setUpdateDate(new Date());
		postService.createComment(comment);
		return "redirect:postInfo?postId=" + postId;
	}

	@PostMapping("addReply")
	public String addReply(@RequestParam("commentId") int commentId, Comment reply) {
		reply.setParentId(commentId);
		reply.setCreateDate(new Date());
		reply.setUpdateDate(new Date());
		postService.createComment(reply);
		return "redirect:postInfo?postId=" + reply.getPostId();
	}

	@PostMapping("deleteComment")
	public String deleteComment(@RequestParam("commentId") int commentId, @RequestParam("postId") int postId) {
		postService.deleteComment(commentId);
		return "redirect:postInfo?postId=" + postId;
	}

	@PostMapping("updateComment")
	@ResponseBody
	public String updateComment(Comment comment) {
		comment.setUpdateDate(new Date());
		postService.updateComment(comment);
		return "redirect:postInfo?postId=" + comment.getPostId();
	}
}
