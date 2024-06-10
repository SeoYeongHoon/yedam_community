package com.yedam.app.yedam_home.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.yedam_post.service.BoardFilesVO;
import com.yedam.app.yedam_post.service.PostService;
import com.yedam.app.yedam_post.service.PostVO;


@Controller
public class HomeController {
	// --------------------------------------------
	// 로컬 저장 경로
	// --------------------------------------------
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Autowired
	PostService postservice;
	
	// --------------------------------------------
	// 게시글 정보 불러오기
	// --------------------------------------------
	@GetMapping("/all/home")
    public String BoardList(Model model) {
        
		// 게시판 1 - 수강별, 2 - 구인/ 구인, 3 - 정보, 4 - 질문
        List<PostVO> board1Posts = postservice.getPostAll(5);
        List<PostVO> board2Posts = postservice.getPostAll(7);
        List<PostVO> board3Posts = postservice.getPostAll(8);
        List<PostVO> board4Posts = postservice.getPostAll(9);
        
        List<PostVO> allPosts = new ArrayList<>();
        allPosts.addAll(board1Posts);
        allPosts.addAll(board2Posts);
        allPosts.addAll(board3Posts);
        allPosts.addAll(board4Posts);
        
        // 파일 불러오기
        for (PostVO post : allPosts) {
            List<BoardFilesVO> boardFilesVO = postservice.getBoardFiles(post.getPostId(), post.getBoardId());
            for (BoardFilesVO file : boardFilesVO) {
                File find = new File(uploadPath + "/" + file.getBoardfileLocation());
                file.setExists(find.exists());
            }
            post.setBoardFiles(boardFilesVO);
        }
        
        // 인기 게시글
        List<PostVO> popularPosts = allPosts.stream()
                                            .sorted(Comparator.comparingInt(PostVO::getPostLike).reversed())
                                            .limit(10) // 상위 10개 게시글
                                            .collect(Collectors.toList());
        
        model.addAttribute("board1Posts", board1Posts);
        model.addAttribute("board2Posts", board2Posts);
        model.addAttribute("board3Posts", board3Posts);
        model.addAttribute("board4Posts", board4Posts);
        model.addAttribute("popularPosts", popularPosts);
        model.addAttribute("postList", allPosts);
        
        return "mainPages/home";
    }
}
