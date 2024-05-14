package com.yedam.app.yedam_post.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yedam.app.yedam_post.service.Comment;
import com.yedam.app.yedam_post.service.Post;
import com.yedam.app.yedam_post.service.PostService;
import com.yedam.app.yedam_post.service.Reply;

@Controller
public class ReplyController {

	@Autowired
	private PostService postService;

	@PostMapping("addReply")
	public String addReply(@RequestParam int postId, Reply reply, Model model) {
		reply.setPostId(postId);
		reply.setReplyDate(new Date());
		reply.setUpdateDate(new Date());
		postService.createReply(reply);
		Post updatedPost = postService.getPostDetails(postId);
		model.addAttribute("postInfo", updatedPost);
		return "redirect:postInfo?postId=" + postId;
	}

	@PostMapping("deleteReply")
	public String deleteReply(@RequestParam("replyId") int replyId, @RequestParam Post postId, Model model) {
		postService.deleteReply(replyId);
		Post updatedPost = postService.getPostDetails(postId);
		model.addAttribute("postInfo", updatedPost);
		return "redirect:postInfo?postId=" + postId;
	}

	@PostMapping("updateReply")
	public String updateReply(Reply reply, Model model, Post postId) {
		reply.setUpdateDate(new Date());
		postService.updateReply(reply);
		Post updatedPost = postService.getPostDetails(reply.getPostId());
		model.addAttribute("postInfo", updatedPost);
		return "redirect:postInfo?postId=" + reply.getPostId();
	}

	@PostMapping("addComment")
	public String addComment(@RequestParam("replyId") int replyId, @RequestParam Post postId, Comment comment, Model model) {
		comment.setReplyId(replyId);
		comment.setCommentDate(new Date());
		postService.createComment(comment);
		Post updatedPost = postService.getPostDetails(postId);
		model.addAttribute("postInfo", updatedPost);
		return "redirect:postInfo?postId=" + postId;
	}

	@PostMapping("deleteComment")
	public String deleteComment(@RequestParam("commentId") int commentId, @RequestParam Post postId, Model model) {
		postService.deleteComment(commentId);
		Post updatedPost = postService.getPostDetails(postId);
		model.addAttribute("postInfo", updatedPost);
		return "redirect:postInfo?postId=" + postId;
	}

	@PostMapping("updateComment")
	public String updateComment(Comment comment, Model model) {
		postService.updateComment(comment);
		Post updatedPost = postService.getPostDetails(comment.getPostId());
		model.addAttribute("postInfo", updatedPost);
		return "redirect:postInfo?postId=" + comment.getPostId();
	}
}
