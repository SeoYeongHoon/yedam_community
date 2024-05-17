package com.yedam.app.yedam_post.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.yedam_post.mapper.CommentMapper;
import com.yedam.app.yedam_post.mapper.PostMapper;
import com.yedam.app.yedam_post.mapper.ReplyMapper;
import com.yedam.app.yedam_post.service.Comment;
import com.yedam.app.yedam_post.service.Post;
import com.yedam.app.yedam_post.service.PostService;
import com.yedam.app.yedam_post.service.Reply;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostMapper postMapper;

	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private CommentMapper commentMapper;

	// 게시글 등록
	@Override
	public int createPost(Post post) {
		return postMapper.insertPost(post);
	}
	
	// 게시글 전체 조회
	@Override
	public List<Post> getAllPosts() {
		return postMapper.getAllPosts();
	}
	
	// 댓글 등록
	@Override
	public int createReply(Reply reply) {
		return replyMapper.insertReply(reply);
	}
	
	// 댓글 수정 
	@Override
	public int updateReply(Reply reply) {
		return replyMapper.updateReply(reply);
	}
	
	// 댓글 삭제
	@Override
	public int deleteReply(int replyId) {
		return replyMapper.deleteReply(replyId);
	}
	
	// 대댓글 등록
	@Override
	public int createComment(Comment comment) {
		return postMapper.insertComment(comment);
	}

	// 대댓글 수정
	@Override
	public int updateComment(Comment comment) {
		return postMapper.updateComment(comment);
	}

	// 대댓글 삭제
	@Override
	public int deleteComment(int comment) {
		return postMapper.deleteComment(comment);
	}

	// 게시글 삭제
	@Override
	@Transactional
	public Map<String, Object> PostDelete(Post post) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            int postId = post.getPostId();
            postMapper.deletePost1(postId);
            postMapper.deletePost2(postId);
            postMapper.deletePost3(postId);
            postMapper.deletePost5(postId);

            resultMap.put("status", "success");
        } catch (Exception e) {
            resultMap.put("status", "error");
            resultMap.put("error", e.getMessage());
        }
        return resultMap;
    }

	// 게시글 수정
	@Override
    public Map<String, Object> PostUpdate(Post post) {
        Map<String, Object> result = new HashMap<>();
        try {
            postMapper.updatePost(post);
            result.put("success", true);
        } catch (Exception e) {
            result.put("success", false);
        }
        return result;
    }
	
     // 게시글 단건조회 + 댓글 + 대댓글
	 @Override
	 public Post getPostReplies(int postId) {
	        Post post = postMapper.getPostDetails(postId);
	                    postMapper.updatePostViewCNT(post);
	        List<Reply> replies = replyMapper.getReplies(postId);
	        List<Comment> comments = commentMapper.getComments(postId);
	        post.setReplies(replies);
	        post.setComments(comments);
	        return post;
	    }

	@Override
	public Post getPostDetails(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostDetails(int postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostComments(int replyId) {
		// TODO Auto-generated method stub
		return null;
	}
}
