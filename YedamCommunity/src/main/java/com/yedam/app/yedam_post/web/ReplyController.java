package com.yedam.app.yedam_post.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yedam.app.yedam_post.service.Comment;
import com.yedam.app.yedam_post.service.PostService;
import com.yedam.app.yedam_post.service.Reply;

@Controller
public class ReplyController {
	
	 @Autowired
	    private PostService postService;

	    @GetMapping("addReply")
	    public String addReply(@RequestParam("postId") int postId, Reply reply) {
	        reply.setPostId(postId);
	        reply.setUpdateDate(new Date());
	        postService.createReply(reply);
	        return "redirect:postInfo?postId=" + postId;
	    }

	    @PostMapping("deleteReply")
	    public String deleteReply(@RequestParam("replyId") int replyId, @RequestParam("postId") int postId) {
	        postService.deleteReply(replyId);
	        return "redirect:postInfo?postId=" + postId;
	    }

	    @PostMapping("updateReply")
	    public String updateReply(Reply reply) {
	        reply.setUpdateDate(new Date());
	        postService.updateReply(reply);
	        return "redirect:postInfo?postId=" + reply.getPostId();
	    }
	    
	    @GetMapping("addComment")
	    public String addComment(@RequestParam("replyId") int replyId, @RequestParam("postId") int postId, Comment comment) {
	        comment.setReplyId(replyId);
	        comment.setCommentDate(new Date());
	        postService.createComment(comment);
	        return "redirect:postInfo?postId=" + postId;
	    }

	    @PostMapping("deleteComment")
	    public String deleteComment(@RequestParam("commentId") int commentId, @RequestParam("postId") int postId) {
	        postService.deleteComment(commentId);
	        return "redirect:postInfo?postId=" + postId;
	    }

	    @PostMapping("updateComment")
	    public String updateComment(Comment comment) {
	        postService.updateComment(comment);
	        return "redirect:postInfo?postId=" + comment.getCommentId();
	    }
}
