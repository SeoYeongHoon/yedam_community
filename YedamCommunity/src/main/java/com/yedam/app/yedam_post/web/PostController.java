package com.yedam.app.yedam_post.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.yedam_post.service.Post;
import com.yedam.app.yedam_post.service.PostService;

@Controller
public class PostController {
	
	@Autowired
    private PostService postService;

    @GetMapping("postList")
    public String postList(Model model) {
        List<Post> list = postService.getAllPosts();
        model.addAttribute("postList", list);
        return "posts/postlist";
    }

    @GetMapping("postInfo")
    public String postInfo(Post post, Model model) {
        Post postDetails = postService.getPostDetails(post);
        model.addAttribute("postInfo", postDetails);
        return "posts/postinfo";
    }

    @GetMapping("postInsert")
    public String postInsertForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/postinsert";
    }

    @PostMapping("postInsert")
    public String postInsertProcess(Post post) {
        post.setCreateDate(new Date());
        post.setUpdateDate(new Date());
        postService.createPost(post);
        return "redirect:postList";
    }

    @GetMapping("postDelete")
    public String postDelete(Post post) {
        postService.PostDelete(post);
        return "redirect:postList";
    }

//    @GetMapping("postUpdate")
//    public String postUpdateForm(@RequestParam Integer postId, Model model) {
//        if (postId == null) {
//            return "redirect:postList";
//        }
//        Post post = postService.getPostDetails(postId);
//        if (post == null) {
//            return "redirect:postList";
//        }
//        model.addAttribute("postInfo", post);
//        return "posts/postUpdate";
//    }
    
//    @GetMapping("postUpdate")
//	public String postUdateForm(@RequestParam Integer postId, Model model) {
//		Post post = new Post();
//		post.setPostId(postId);
//		
//		Post postfind = new Post();
//		model.addAttribute("boardInfo", postfind);
//		return "posts/postUpdate";
//	}

//    @PostMapping("postUpdate")
//    public String postUpdateProcess(Post post) {
//        post.setUpdateDate(new Date());
//        postService.updatePost(post);
//        return "redirect:postInfo?postId=" + post.getPostId();
//    }
    
//    @ResponseBody
//	public Map<String, Object> postUPdateProcess(@RequestBody Post post) {
//		return postService.PostUpdate(post);
//	}
    
}
