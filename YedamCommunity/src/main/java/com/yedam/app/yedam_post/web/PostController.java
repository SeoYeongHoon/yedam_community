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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.yedam_common.LoginUserVO;
import com.yedam.app.yedam_post.service.BoardFilesVO;
import com.yedam.app.yedam_post.service.PostCommentVO;
import com.yedam.app.yedam_post.service.PostReplyVO;
import com.yedam.app.yedam_post.service.PostService;
import com.yedam.app.yedam_post.service.PostVO;
import com.yedam.app.yedam_post.service.ReportVO;
/**
 * 게시판, 게시글
 * 2024.05.29
 * 임우열
 */
@Controller
@RequestMapping("/all")
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
	                       PostVO postVO,
	                       @RequestParam(required = false, defaultValue = "1") int page,
	                       @RequestParam(required = false, defaultValue = "6") int pageSize,
	                       @RequestParam(required = false) String keyword,
	                       Model model) {
	    try {
	        postVO.setBoardId(boardId);

	        if (keyword != null && !keyword.isEmpty()) {
	            postVO.setKeyword(keyword);
	        }

	        List<PostVO> list = postService.getPosts(postVO, page, pageSize);
	        int totalCount = postService.getPostCount(postVO);
	        int totalPages = (int) Math.ceil((double) totalCount / pageSize);

	        // 게시글 리스트에서 파일 정보 가져오기
	        for (PostVO post : list) {
	            List<BoardFilesVO> boardFilesVO = postService.getBoardFiles(post.getPostId(), boardId);
	            for (BoardFilesVO file : boardFilesVO) {
	                File find = new File(uploadPath + "/" + file.getBoardfileLocation());
	                file.setExists(find.exists());
	            }
	            post.setBoardFiles(boardFilesVO);
	        }
	        System.err.println("boardId: "+ boardId);
	        System.err.println("postId: " + postVO);
	        
	        model.addAttribute("postList", list);
	        model.addAttribute("totalCount", totalCount);
	        model.addAttribute("currentPage", page);
	        model.addAttribute("pageSize", pageSize);
	        model.addAttribute("boardId", boardId);
	        model.addAttribute("keyword", keyword);
	        model.addAttribute("totalPages", totalPages);

	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "게시글 목록을 불러오는 중 오류가 발생했습니다.");
	        e.printStackTrace();
	    }

	    return "posts/postList";
	}


	// --------------------------------------------
	// 게시글 단건 조회
	// --------------------------------------------
	@GetMapping("/post/{boardId}/{postId}")
	public String getPostDetail(@PathVariable int boardId,
	                            @PathVariable int postId,
	                            Authentication authentication,
	                            Model model) {
	    // 조회수 업데이트
	    postService.PostViewCnt(postId, boardId);
	    
	    // 게시글 단건 조회
	    PostVO postVO = postService.getPostReplies(postId, boardId);
	    // 댓글 조회
	    List<PostReplyVO> replylist = postService.getPostReply(postId);
	    for (PostReplyVO postreplyVO : replylist) {
	        // 대댓글 조회
	        List<PostCommentVO> commentlist = postService.getPostComment(postreplyVO);
	        postreplyVO.setComments(commentlist);
	    }
	    postVO.setReplies(replylist);
	    
	    // 현재 로그인한 유저와 작성자 비교
	    LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
	    boolean isOwner = userVO.getuserId().equals(postVO.getUserId());
	    
	    // 파일 조회
	    List<BoardFilesVO> boardFilesVO = postService.getBoardFiles(postId, boardId);
	    for (BoardFilesVO file : boardFilesVO) {
	        File find = new File(uploadPath + "/" + file.getBoardfileLocation());
	        file.setExists(find.exists());
	    }
	    
	    postVO.setBoardFiles(boardFilesVO);	    
	    postVO.setBoardId(boardId);
	    postVO.setPostId(postId);
	    model.addAttribute("boardId", boardId);
	    model.addAttribute("postInfo", postVO);
	    // 작성자 여부 추가
	    model.addAttribute("isOwner", isOwner);

	    return "posts/postInfo";
	}
	
	// --------------------------------------------
	// 게시글 등록
	// --------------------------------------------
	@GetMapping("/postInsert/{boardId}")
    public String postInsertForm(Model model
    		                   , @PathVariable int boardId
    		                   , Authentication authentication) {
        PostVO postVO = new PostVO();
        postVO.setBoardId(boardId);
        
        
        // 현재 로그인한 사용자 정보를 가져와서 writer 필드에 설정
        LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
        postVO.setWriter(userVO.getUsername());
        
        model.addAttribute("post", postVO);
        return "posts/postInsert";
    }

	// --------------------------------------------
	// 게시글 등록 처리
	// --------------------------------------------
	@PostMapping("/postInsert")
	public String postInsertProcess(PostVO postVO, 
	                                @RequestParam("file") MultipartFile file, 
	                                Authentication authentication) {
	    try {
	        if (!file.isEmpty()) {
	            String fileName = file.getOriginalFilename();
	            System.err.println("fileName :" + fileName);
	            String filePath = uploadPath + fileName;
	            System.err.println("filePath :" + filePath);
	            
	            File directory = new File(uploadPath);
	            if (!directory.exists()) {
	                directory.mkdirs();
	            }
	            
	            File dest = new File(filePath);
	            file.transferTo(dest);
	            postVO.setBoardfileName(fileName);
	            postVO.setBoardfileSize(file.getSize());
	            postVO.setBoardfileLocation(fileName);
	            postVO.setBoardfileExt(fileName.substring(fileName.lastIndexOf('.') + 1));
	        }

	        LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
	        postVO.setUserId(userVO.getuserId());
	        postVO.setWriter(userVO.getUsername());
	        postVO.setCreateDate(new Date());
	        postVO.setUpdateDate(new Date());
	        postService.createPost(postVO);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "redirect:/all/post/" + postVO.getBoardId();
	}


	// --------------------------------------------
	// 게시글 삭제 처리
	// --------------------------------------------
	@GetMapping("/postDelete/{boardId}/{postId}")
	public String postDelete(@PathVariable int boardId
			               , @PathVariable int postId) {

		PostVO postVO = new PostVO();
		postVO.setPostId(postId);
		postVO.setBoardId(boardId);
		postService.PostDelete(postVO);

		return "redirect:/all/post/" + boardId;
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
		PostVO postVO = postService.getPostReplies(postId, boardId);
		model.addAttribute("post", postVO);
		model.addAttribute("boardId", boardId);
		model.addAttribute(postVO);
		return "posts/postUpdate";
	}

	// --------------------------------------------
	// 게시글 업데이트 처리
	// --------------------------------------------
	@PostMapping("/postUpdate")
	@ResponseBody
	public Map<String, Object> postUpdateProcess(@ModelAttribute PostVO postVO, 
	                                             @RequestParam("file") MultipartFile file) {
	    Map<String, Object> result = new HashMap<>();
	    try {
	        if (!file.isEmpty()) {
	            String fileName = file.getOriginalFilename();
	            String filePath = uploadPath + fileName;

	            File directory = new File(uploadPath);
	            if (!directory.exists()) {
	                directory.mkdirs();
	            }

	            File dest = new File(filePath);
	            file.transferTo(dest);

	            postVO.setBoardfileName(fileName);
	            postVO.setBoardfileSize(file.getSize());
	            postVO.setBoardfileLocation(fileName);
	            postVO.setBoardfileExt(fileName.substring(fileName.lastIndexOf('.') + 1));
	        }

	        postVO.setUpdateDate(new Date());
	        postService.PostUpdate(postVO);
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
	public String reportPost(ReportVO reportVO,
			                 Authentication authentication) {
		try {
			LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
			reportVO.setReporter(userVO.getUsername());
			reportVO.setProcess(0);
			postService.createReport(reportVO);
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
	public boolean updateLike(@RequestParam("postId") int postId, 
			              Authentication authentication) {
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
		PostVO postVO = new PostVO();
		postVO.setUserId(userVO.getuserId());
	    int userId = userVO.getuserId();
		Integer likeCheck = postService.likeCheck(postId, userId);
		
		if (likeCheck == 0) {
			postService.createLike(postId, userId); // 게시판의 추천을 눌렀을시 boardlikes 테이블에 insert
			postService.updateLike(postId); // 게시판 테이블 +1
		} else {
			postService.updateLikeCancel(postId); // 게시판 테이블 -1
			postService.LikeDelete(postId, userId); // boardlikes 테이블에 DELETE
		}
		return likeCheck==1? true : false;
	}

}
