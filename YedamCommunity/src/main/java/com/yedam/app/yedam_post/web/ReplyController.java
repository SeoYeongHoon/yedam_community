package com.yedam.app.yedam_post.web;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.yedam_common.LoginUserVO;
import com.yedam.app.yedam_post.service.PostCommentVO;
import com.yedam.app.yedam_post.service.PostService;
import com.yedam.app.yedam_post.service.PostReplyVO;

@Controller
public class ReplyController {

	@Autowired
	private PostService postService;

	//--------------------------------------------
    //댓글 추가 처리
	//--------------------------------------------
	@PostMapping("/Reply")
	@ResponseBody
	public String addReply(
			               @RequestParam("boardId") int boardId,
			               @RequestParam("postId") int postId, 
			               @RequestParam("replyContent") String replyContent,
			               Authentication authentication) {
		
		
		PostReplyVO postreplyVO = new PostReplyVO();
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
		postreplyVO.setReplyWriter(userVO.getNickname());
		
		postreplyVO.setPostId(postId);
		postreplyVO.setBoardId(boardId); 
		postreplyVO.setReplyContent(replyContent);
		postreplyVO.setReplyDate(new Date());
		postreplyVO.setUpdateDate(new Date());
		postService.createReply(postreplyVO);
		return "success";
	}
	
	//--------------------------------------------
    //댓글 삭제 처리
    //--------------------------------------------
    @DeleteMapping("/Reply")
    @ResponseBody
    public String deleteReply(@RequestParam("replyId") int replyId) {
    	
    	PostReplyVO postreplyVO = new PostReplyVO();
    	postreplyVO.setReplyId(replyId);
        Map<String, Object> result = postService.deleteReply(postreplyVO);
        if ("success".equals(result.get("status"))) {
            return "댓글이 성공적으로 삭제되었습니다.";
        } else {
            return "댓글 삭제 중 오류가 발생했습니다: " + result.get("error");
        }
    }
    
    //--------------------------------------------
    //대댓글 추가 처리
    //--------------------------------------------
    @PostMapping("/Comment")
    @ResponseBody
    public String addComment(@RequestParam("replyId") int replyId,
    		                 @RequestParam("commentContent") String commentContent,
    		                 Authentication authentication) {
    	
    	PostCommentVO postcommentVO = new PostCommentVO();
    	
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
		postcommentVO.setCommentWriter(userVO.getNickname());
    	
        postcommentVO.setReplyId(replyId);
        postcommentVO.setCommentContent(commentContent);
        postService.createComment(postcommentVO);
        return "대댓글이 성공적으로 추가되었습니다.";
    }
    
    //--------------------------------------------
    //대댓글 삭제 처리
    //-------------------------------------------- 
    @DeleteMapping("/Comment")
    @ResponseBody
    public String deleteComment(@RequestParam("commentId") int commentId) {
    	
    	PostCommentVO postcommentVO = new PostCommentVO();
    	postcommentVO.setCommentId(commentId);
        Map<String, Object> result = postService.deleteComment(postcommentVO);
        if ("success".equals(result.get("status"))) {
            return "대댓글이 성공적으로 삭제되었습니다.";
        } else {
            return "대댓글 삭제 중 오류가 발생했습니다: " + result.get("error");
        }
    }
}
