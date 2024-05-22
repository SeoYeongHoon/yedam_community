package com.yedam.app.yedam_post.web;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.yedam_post.service.Comment;
import com.yedam.app.yedam_post.service.PostService;
import com.yedam.app.yedam_post.service.Reply;

@Controller
public class ReplyController {

	@Autowired
	private PostService postService;

	/**
     * 댓글 추가 처리
     * @param postId 게시글 ID
     * @param replyContent 댓글 내용
     * @return 성공 메시지
     */
	@PostMapping("addReply")
	@ResponseBody
	public String addReply(@RequestParam("postId") int postId, 
			               @RequestParam("replyContent") String replyContent) {
		Reply reply = new Reply();
		reply.setPostId(postId);
		reply.setBoardId(1); // 게시판 1번유형
		reply.setReplyContent(replyContent);
		reply.setReplyDate(new Date());
		reply.setUpdateDate(new Date());
		reply.setReplyWriter("익명");
		postService.createReply(reply);
		return "success";
	}
	
	/**
     * 댓글 삭제 처리
     * @param replyId 댓글 ID
     * @param postId 게시글 ID
     * @return 게시글 상세 페이지로 리다이렉트
     */
    @PostMapping("deleteReply")
    @ResponseBody
    public String deleteReply(@RequestParam("replyId") int replyId) {
    	Reply reply = new Reply();
    	reply.setReplyId(replyId);
        Map<String, Object> result = postService.deleteReply(reply);
        if ("success".equals(result.get("status"))) {
            return "댓글이 성공적으로 삭제되었습니다.";
        } else {
            return "댓글 삭제 중 오류가 발생했습니다: " + result.get("error");
        }
    }
    
    /**
     * 대댓글 추가 처리
     * @param replyId 댓글 ID
     * @param commentContent 대댓글 내용
     * @param commentWriter 
     * @return 성공 메시지
     */
    @PostMapping("addComment")
    @ResponseBody
    public String addComment(@RequestParam("replyId") int replyId, @RequestParam("commentContent") String commentContent) {
    	Comment comment = new Comment();
        comment.setReplyId(replyId);
        comment.setCommentContent(commentContent);
		comment.setCommentWriter("익명대댓글");
        postService.createComment(comment);
        return "대댓글이 성공적으로 추가되었습니다.";
    }
    
    /**
     * 대댓글 삭제 처리
     * @param commentId 대댓글 ID
     * @return 성공 또는 오류 메시지
     */
    @PostMapping("deleteComment")
    @ResponseBody
    public String deleteComment(@RequestParam("commentId") int commentId) {
    	Comment comment = new Comment();
    	comment.setCommentId(commentId);
        Map<String, Object> result = postService.deleteComment(comment);
        if ("success".equals(result.get("status"))) {
            return "대댓글이 성공적으로 삭제되었습니다.";
        } else {
            return "대댓글 삭제 중 오류가 발생했습니다: " + result.get("error");
        }
    }
}
