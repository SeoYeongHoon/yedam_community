package com.yedam.app.yedam_post.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.app.yedam_common.LoginUserVO;
import com.yedam.app.yedam_common.PageDTO;
import com.yedam.app.yedam_curriculum.service.CurriculumService;
import com.yedam.app.yedam_curriculum.service.CurriculumVO;
import com.yedam.app.yedam_post.service.BoardFilesVO;
import com.yedam.app.yedam_post.service.PostCommentVO;
import com.yedam.app.yedam_post.service.PostReplyVO;
import com.yedam.app.yedam_post.service.PostService;
import com.yedam.app.yedam_post.service.PostVO;
import com.yedam.app.yedam_post.service.ReportVO;
/**
 * 게시판, 게시글
 * 2024.06.06
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
	
	@Autowired
	CurriculumService curriculumService;

	// --------------------------------------------
	// 게시글 리스트 조회
	// --------------------------------------------
	@GetMapping("/posts/{boardType}")
	public String postList(@PathVariable Integer boardType,
	                       PostVO postVO,
	                       @RequestParam(required = false, defaultValue = "1") Integer page,
	                       @RequestParam(required = false, defaultValue = "6") Integer pageSize,
	                       @RequestParam(required = false) String keyword,
	                       Model model) {
		
		 int boardId = postService.boardTypeSet(boardType);
		 postVO.setBoardId(boardId);
	    try {
	        postVO.setBoardType(boardType);

	        if (keyword != null && !keyword.isEmpty()) {
	            postVO.setKeyword(keyword);
	        }

	        List<PostVO> list = postService.getPosts(postVO, page, pageSize);
	        int totalCount = postService.getPostCount(postVO);
	        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
	        

	        // 게시글 리스트에서 파일 정보 가져오기
	        for (PostVO post : list) {
	            List<BoardFilesVO> boardFilesVO = postService.getBoardFiles(post.getPostId(), boardType);
	            for (BoardFilesVO file : boardFilesVO) {
	                File find = new File(uploadPath + "/" + file.getBoardfileLocation());
	                file.setExists(find.exists());
	            }
	            post.setBoardFiles(boardFilesVO);
	        }
	        
	        model.addAttribute("postList", list);
	        model.addAttribute("totalCount", totalCount);
	        model.addAttribute("currentPage", page);
	        model.addAttribute("pageSize", pageSize);
	        model.addAttribute("boardId", boardType);
	        model.addAttribute("keyword", keyword);
	        model.addAttribute("totalPages", totalPages);

	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "게시글 목록을 불러오는 중 오류가 발생했습니다.");
	        e.printStackTrace();
	    }

	    return "posts/postList";
	}

	//--------------------------------
	// 수료과정별 게시판
	//--------------------------------
	@GetMapping("/curriculumPost")
	public String curriculum(Model model,
							Authentication authentication) {
		
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
		int userId = userVO.getuserId();
		
		// int boardId =  postService.boardId(userId);
		
		List<CurriculumVO> list = postService.curriculumList();
		List<PostVO> postList = postService.postlist();
		// model.addAttribute("boardId",boardId);
		model.addAttribute("curriculum", list);
		model.addAttribute("postList", postList);
		return "posts/curriculumPost";
	}
	
//	//--------------------------------
//	// 수교과정별게시판 게시글 전체 조회
//	//--------------------------------
//	 @GetMapping("/postList")
//	 @ResponseBody 
//	 public List<PostVO> curriculumPost() {
//		 return  postService.postlist();
//	  }
	 
	//--------------------------------
	// 수교과정별게시판 갤러리 조회
	//--------------------------------
	 @GetMapping("/gallery")
	 @ResponseBody 
	 public List<BoardFilesVO> gallery(@RequestParam int curriculumId) {
		 List<BoardFilesVO> fileList = postService.successFileList(curriculumId);
		 
		 if (fileList != null) {
			 return  postService.successFileList(curriculumId);
		 }
		 return  null;
	  }
		 
		 
	//--------------------------------
	// 해당 커리큘럼 게시글 조회
	//--------------------------------
	 @GetMapping("/selectCurriculum")
	 @ResponseBody 
	 public List<PostVO> selectPost(@RequestParam int curriculumId) {
		 List<PostVO> postList =  postService.selectCurriculum(curriculumId);
		 return  postList;
	  }
	
	
	// --------------------------------------------
	// 게시글 단건 조회
	// --------------------------------------------
	@GetMapping("/post/{boardId}/{postId}")
	public String getPostDetail(@PathVariable int boardId,
                                @PathVariable int postId,
	                            Authentication authentication,
	                            Model model) {
	    // 게시글 단건 조회
	    PostVO postVO = postService.getPostReplies(postId, boardId);
	    int postcnt = postService.PostViewCnt(postId, boardId);
	    
	    // 현재 로그인한 유저와 작성자 비교
	    LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
	    String userName = userVO.getUsername();
	    String userType = userVO.getUserType();
	    
	    // 댓글 조회
	    List<PostReplyVO> replylist = postService.getPostReply(postId);
	    
	    for (PostReplyVO postreplyVO : replylist) {
	        // 대댓글 조회
	        List<PostCommentVO> commentlist = postService.getPostComment(postreplyVO);
	        postreplyVO.setComments(commentlist);
	    }
	    
	    // 파일 조회
	    List<BoardFilesVO> boardFilesVO = postService.getBoardFiles(postId, boardId);
	    for (BoardFilesVO file : boardFilesVO) {
	        File find = new File(uploadPath + "/" + file.getBoardfileLocation());
	        file.setExists(find.exists());
	    }
	    
	    // 추천 조회
	    int userId = userVO.getuserId();
	    Integer likeCheck = postService.likeCheck(postId, userId);
	    
	    model.addAttribute("isLikeChecked", likeCheck);
	    
	    postVO.setReplies(replylist);
	    postVO.setBoardFiles(boardFilesVO);	   
	    postVO.setBoardId(boardId);
	    postVO.setPostId(postId);
	    model.addAttribute("boardId", boardId);
	    model.addAttribute("postInfo", postVO);
	    model.addAttribute("viewcnt", postcnt);
	    // 작성자 여부 추가
	    model.addAttribute("userName", userName);
	    model.addAttribute("userType", userType);

	    return "posts/postInfo";
	}
	
	// --------------------------------------------
	// 게시글 등록
	// --------------------------------------------
	@GetMapping("/postInsert/{boardType}")
    public String postInsertForm(Model model
    		                   , PostVO postVO
    		                   , @PathVariable int boardType
    		                   , Authentication authentication) {
        postVO.setBoardType(boardType);
        int boardId = postService.boardTypeSet(boardType);
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
        
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String filePath = uploadPath + fileName;

            File directory = new File(uploadPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Path savePath = Paths.get(filePath);
            try {
                file.transferTo(savePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        return "redirect:/all/posts/" + postVO.getBoardType();
    }
	
	// --------------------------------------------
	// 수료게시판 게시글 등록
	// --------------------------------------------
	@GetMapping("/curriculumPostInsert")
    public String curriculumPostInsert(Model model
    		                   , PostVO postVO
//	    		               , @PathVariable int boardType
//    		                   , @RequestParam String curriculumSelect
    		                   , Authentication authentication) {
//		    postVO.setBoardType(boardType);
//		    int boardId = postService.boardTypeSet(boardType);
//		    postVO.setBoardId(boardId);
		
        // 현재 로그인한 사용자 정보를 가져와서 writer 필드에 설정
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
        List<CurriculumVO> currList = curriculumService.gradSelectAll();
		model.addAttribute("class", currList);
		
		if (!userVO.getUserType().equals("ROLE_ADMIN")) {
			postVO.setBoardId(postService.findIdByCurriculum(userVO.getUserCurriculumId()));
			postVO.setBoardType(postService.findByCurriculum(userVO.getUserCurriculumId()));
		}
		
        postVO.setWriter(userVO.getUsername());
        model.addAttribute("post", postVO);
        return "posts/curriculumPostInsert";
    }
	
	// --------------------------------------------
	// 게시글 등록 처리
	// --------------------------------------------
	@PostMapping("/coursePostInsert")
    public String coursePostInsert(PostVO postVO,
    		                       @RequestParam("file") MultipartFile file,
    		                       @RequestParam int curriculumSelect,
                                   Authentication authentication) {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String filePath = uploadPath + fileName;

            File directory = new File(uploadPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Path savePath = Paths.get(filePath);
            try {
                file.transferTo(savePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        
        if (userVO.getUserType().equals("ROLE_ADMIN")) {
        	postVO.setBoardId(postService.findIdByCurriculum(curriculumSelect));
        	postVO.setBoardType(postService.findByCurriculum(curriculumSelect));
        }
        
        postService.createPost(postVO);
        return "redirect:/all/curriculumPost";
    }
	
	
	// --------------------------------------------
	// 게시글 질문 토론 등록
	// --------------------------------------------
	@GetMapping("/postInsertVote/{boardType}")
    public String postInsertFormvote(Model model
    		                   , PostVO postVO
    		                   , @PathVariable int boardType
    		                   , Authentication authentication) {
		
		postVO.setBoardType(boardType);
		int boardId = postService.boardTypeSet(boardType);
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
		postVO.setWriter(userVO.getUsername());
        postVO.setBoardId(boardId);
        model.addAttribute("post", postVO);
        return "posts/postInsertvote";
    }
	
	// --------------------------------------------
	// 게시글 질문 토론 등록 처리
	// --------------------------------------------
	@PostMapping("/postInsertVote")
	public String postInsertVoteProcess(PostVO postVO
			                          , Authentication authentication) {
		
		LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
        postVO.setUserId(userVO.getuserId());
        postVO.setWriter(userVO.getUsername());
        postService.createVote(postVO);
        return "redirect:/all/posts/" + 4;
	}
	
	// --------------------------------------------
	// 게시글 질문 토론 단건조회
	// --------------------------------------------
	@GetMapping("/postvote/{boardId}/{postId}")
	public String getPostVoteDetail(Model model,
   	  	 	     					PostVO postVO,
   	  	 	     				    @PathVariable int postId,
   	  	 	     				    @PathVariable int boardId,
			                        Authentication authentication) {
		// 게시글 단건 조회
	    postVO = postService.getPostVotedetail(postVO);
	    PostVO find = postService.getPostVotedetailNo(postVO);
	    
	    int postcnt = postService.PostViewCnt(postId, boardId);
	    
	    // 현재 로그인한 유저와 작성자 비교
	    LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
	    String userName = userVO.getUsername();
	    String userType = userVO.getUserType();
	    
	   // 댓글 조회
	    List<PostReplyVO> replylist = postService.getPostReply(postId);
	    for (PostReplyVO postreplyVO : replylist) {
	        // 대댓글 조회
	        List<PostCommentVO> commentlist = postService.getPostComment(postreplyVO);
	        postreplyVO.setComments(commentlist);
	    }
	    
	    
	    // 추천 조회
	    int userId = userVO.getuserId();
	    Integer likeCheck = postService.likeCheck(postId, userId);
	    
	    model.addAttribute("isLikeChecked", likeCheck);
	    
	    
	   postVO.setPostId(postId);
	   postVO.setReplies(replylist);
	    model.addAttribute("postvote", postVO);
	    model.addAttribute("postvoteno",find);
	    model.addAttribute("viewcnt", postcnt);
	    // 작성자 여부 추가
	    model.addAttribute("userName", userName);
	    model.addAttribute("userType", userType);
	    
	    return "posts/postInfovote";
	}
	
	// --------------------------------------------
	// 게시글 삭제 처리
	// --------------------------------------------
	@DeleteMapping("/postDelete")
	@ResponseBody
	public String postDelete(@RequestParam int boardId
			               , @RequestParam int postId) {

		PostVO postVO = new PostVO();
		postVO.setPostId(postId);
		postVO.setBoardId(boardId);
		postService.PostDelete(postVO);

		return "redirect:/all/posts/" + boardId;
	}

	// --------------------------------------------
	// 게시글 업데이트
	// --------------------------------------------
	@GetMapping("/postUpdate/{boardId}/{postId}")
	public String postUpdateForm(@PathVariable Integer boardId
			                   , @PathVariable Integer postId
			                   , Model model) {

		if (postId == null) {
			return "redirect:/posts/" + boardId;
		}
		
		// 파일 조회
	    List<BoardFilesVO> boardFilesVO = postService.getBoardFiles(postId, boardId);
	    for (BoardFilesVO file : boardFilesVO) {
	        File find = new File(uploadPath + "/" + file.getBoardfileLocation());
	        file.setExists(find.exists());
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

	            Path savePath = Paths.get(filePath);
	            try {
					file.transferTo(savePath);
				} catch (IOException e) {
					e.printStackTrace();
				}
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
		Integer likeCheck = postService.likeCheck(postId, userId); // 유저가 추천을 눌렀는지 안닌지 확인
		
		if (likeCheck == 0) {
			postService.createLike(postId, userId); // 게시판의 추천을 눌렀을시 boardlikes 테이블에 insert
			postService.updateLike(postId); // 게시판 테이블 +1
		} else {
			postService.updateLikeCancel(postId); // 게시판 테이블 -1
			postService.LikeDelete(postId, userId); // boardlikes 테이블에 DELETE
		}
		return likeCheck == 1 ? true : false;
	}
	
	// ----------------
	// 게시글 필터링 및 페이징
	// ----------------
	@GetMapping("/filterPost")
	@ResponseBody
	public Map<String, Object> filterHomeworks(@RequestParam("filter") int filter,
											   @RequestParam(defaultValue = "1") int page,
											   @RequestParam(defaultValue = "") String searchQuery) {
		
		  List<PostVO> posts = postService.getPostsByFilter(filter,page, searchQuery); 
		  int totalCnt = postService.getTotalCnt(filter,searchQuery);
		  
		  PageDTO pageDTO = new PageDTO(page, totalCnt, 5);
		  
		  Map<String, Object> response = new HashMap<>(); 
		  response.put("posts",posts); 
		  response.put("page", pageDTO);
		
		return response;
	}
	
	// 투표 처리
	@PostMapping("/vote")
	@ResponseBody
	public boolean handleVote(@RequestParam("voteId") int voteId, 
	                          @RequestParam("voteItemId") int voteItemId, 
	                          Authentication authentication) {
	    LoginUserVO userVO = (LoginUserVO) authentication.getPrincipal();
	    int userId = userVO.getuserId();

	    Map<String, Object> map = new HashMap<>();
	    map.put("voteId", voteId);
	    map.put("userId", userId);
	    map.put("voteItemId", voteItemId);

	    Integer voteCheck = postService.voteExists(map); // 유저가 투표를 했는지 확인

	    if (voteCheck == 0) {
	        postService.submitVote(map); // 투표하지 않은 경우, 투표 추가
	        postService.VoteCountUP(map);
	    } else {
	        postService.cancelVote(map); // 이미 투표한 경우, 투표 취소
	        postService.VoteCountDOWN(map);
	    }

	    return voteCheck == 0;
	}
}