package com.yedam.app.yedam_post.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "post/postlist";
    }

    @GetMapping("postInfo")
    public String postInfo(@RequestParam("postId") int postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("postInfo", post);
        return "post/postinfo";
    }

    @GetMapping("postInsert")
    public String postInsertForm(Model model) {
        model.addAttribute("post", new Post());
        return "post/postinsert";
    }

    @PostMapping("postInsert")
    public String postInsertProcess(Post post) {
        post.setCreateDate(new Date());
        post.setUpdateDate(new Date());
        postService.createPost(post);
        return "redirect:postList";
    }

    @GetMapping("postDelete")
    public String postDelete(@RequestParam("postId") int postId) {
        postService.deletePost(postId);
        return "redirect:postList";
    }

    @GetMapping("postUpdate")
    public String postUpdateForm(@RequestParam("postId") int postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        return "post/postupdate";
    }

    @PostMapping("postUpdate")
    public String postUpdateProcess(Post post) {
        post.setUpdateDate(new Date());
        postService.updatePost(post);
        return "redirect:postInfo?postId=" + post.getPostId();
    }
}
