package com.yedam.app.yedam_post.web;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.yedam_common.LoginUserVO;
import com.yedam.app.yedam_post.service.BoardFiles;
import com.yedam.app.yedam_post.service.Comment;
import com.yedam.app.yedam_post.service.Post;
import com.yedam.app.yedam_post.service.PostService;
import com.yedam.app.yedam_post.service.Reply;
import com.yedam.app.yedam_post.service.Report;
/**
 * 게시판, 게시글
 * 2024.05.27
 * 임우열
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
	@GetMapping("/post/{boardId}")
	public String postList(@PathVariable int boardId,
						   Post post,
	                       @RequestParam(required = false, defaultValue = "1") int page,
	                       @RequestParam(required = false, defaultValue = "10") int pageSize,
	                       Model model) {
		
	    post.setBoardId(boardId);
	    List<Post> list = postService.getPosts(post , page, pageSize);
	    int totalCount = postService.getPostCount(post);

	    model.addAttribute("postList", list);
	    model.addAttribute("totalCount", totalCount);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("boardId", boardId);

	    return "posts/postList";
	}
	
	// --------------------------------------------
	// 게시글 단건 조회
	// --------------------------------------------
	@GetMapping("/post/{boardId}/{postId}")
	public String getPostDetail(@PathVariable int boardId,
	                            @PathVariable int postId,
	                            Model model) {
	    // 조회수 업데이트
	    postService.PostViewCnt(postId, boardId);
	    
	    // 게시글 단건 조회
	    Post postfind = postService.getPostReplies(postId, boardId);
	    
	    // 댓글 조회
	    List<Reply> replylist = postService.getPostReply(postId);
	    for (Reply reply : replylist) {
	        // 대댓글 조회
	        List<Comment> commentlist = postService.getPostComment(reply);
	        reply.setComments(commentlist);
	    }
	    postfind.setReplies(replylist);

	    List<BoardFiles> boardFiles = postService.getBoardFiles(postId, boardId);
	    postfind.setBoardFiles(boardFiles);

	    model.addAttribute("boardId", boardId);
	    model.addAttribute("postInfo", postfind);

	    return "posts/postInfo";
	}
	
	// --------------------------------------------
	// 게시글 등록
	// --------------------------------------------
	@GetMapping("/postInsert/{boardId}")
    public String postInsertForm(Model model
    		                   , @PathVariable int boardId
    		                   , Authentication authentication) {
        Post post = new Post();
        post.setBoardId(boardId);
        
        
        // 현재 로그인한 사용자 정보를 가져와서 writer 필드에 설정
        LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
        post.setWriter(userVO.getNickname());
        
        model.addAttribute("post", post);
        return "posts/postinsert";
    }

	// --------------------------------------------
	// 게시글 등록 처리
	// --------------------------------------------
	@PostMapping("/postInsert")
	public String postInsertProcess(Post post, 
	                                @RequestParam("file") MultipartFile file, 
	                                Authentication authentication) {
	    try {
	        if (!file.isEmpty()) {
	            String fileName = file.getOriginalFilename();
	            
	            String filePath = uploadPath + "/" + fileName;
	            
	            File directory = new File(uploadPath);
	            if (!directory.exists()) {
	                directory.mkdirs();
	            }
	            
	            File dest = new File(filePath);
	            file.transferTo(dest);
	            
	            post.setBoardfileName(fileName);
	            post.setBoardfileSize(file.getSize());
	            post.setBoardfileLocation(fileName);
	            post.setBoardfileExt(fileName.substring(fileName.lastIndexOf('.') + 1));
	        }

	        LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
	        post.setUserId(userVO.getuserId());
	        post.setWriter(userVO.getNickname());
	        
	        post.setCreateDate(new Date());
	        post.setUpdateDate(new Date());
	        postService.createPost(post);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "redirect:/post/" + post.getBoardId();
	}


	// --------------------------------------------
	// 게시글 삭제 처리
	// --------------------------------------------
	@GetMapping("/postDelete/{boardId}/{postId}")
	public String postDelete(@PathVariable int boardId
			               , @PathVariable int postId) {

		Post post = new Post();
		post.setPostId(postId);
		post.setBoardId(boardId);
		postService.PostDelete(post);

		return "redirect:/post/" + boardId;
	}

	// --------------------------------------------
	// 게시글 업데이트
	// --------------------------------------------
	@GetMapping("/postUpdate/{boardId}/{postId}")
	public String postUpdateForm(@PathVariable Integer boardId
			                   , @PathVariable Integer postId
			                   , Model model) {

		if (postId == null) {
			return "redirect:/post/" + boardId;
		}
		Post post = postService.getPostReplies(postId, boardId);
		model.addAttribute("post", post);
		model.addAttribute("boardId", boardId);
		return "posts/postUpdate";
	}

	// --------------------------------------------
	// 게시글 업데이트 처리
	// --------------------------------------------
	@PostMapping("/postUpdate")
	@ResponseBody
	public Map<String, Object> postUpdateProcess(@ModelAttribute Post post, 
	                                             @RequestParam("file") MultipartFile file) {
	    Map<String, Object> result = new HashMap<>();
	    try {
	        if (!file.isEmpty()) {
	            String fileName = file.getOriginalFilename();
	            String filePath = uploadPath + "/" + fileName;

	            File directory = new File(uploadPath);
	            if (!directory.exists()) {
	                directory.mkdirs();
	            }

	            File dest = new File(filePath);
	            file.transferTo(dest);

	            post.setBoardfileName(fileName);
	            post.setBoardfileSize(file.getSize());
	            post.setBoardfileLocation(fileName);
	            post.setBoardfileExt(fileName.substring(fileName.lastIndexOf('.') + 1));
	        }

	        post.setUpdateDate(new Date());
	        postService.PostUpdate(post);
	        result.put("success", true);
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.put("success", false);
	        result.put("message", "File upload error: " + e.getMessage());
	    }
	    return result;
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
