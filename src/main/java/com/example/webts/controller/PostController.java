package com.example.webts.controller;


import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.webts.domain.Post;
import com.example.webts.domain.User;
import com.example.webts.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {
	
	private final PostService postService;
	
	@GetMapping("/post/main")
	public String postmain(Model model, @PageableDefault(size=5, sort="id", direction=Direction.DESC)Pageable pageable) {
		Page<Post> postlist = postService.getPosts(pageable);
		model.addAttribute("postlist",postlist.getContent()); // content만 전달
		
		return "post/postmain";
	}
	
	@GetMapping("/post/create")
	public String postCreate(){
		return "post/postcreate";
	}
	
	@PostMapping("/post/create")
	public String postCreate(Post post, HttpSession session) {
		User user = (User)session.getAttribute("principal");
		postService.savePost(post, user);
		return "redirect:/post/main";
	}
	

    @GetMapping("/post/detail/{id}")
    public String postDetail(@PathVariable Integer id, Model model) {
        Post post = postService.checkPost(id); 
        if (post != null) {
            model.addAttribute("postInfo", post); 
            return "post/postdetail"; 
        } else {
            model.addAttribute("error", "게시물을 찾을 수 없습니다.");
            return "error"; // 게시물이 없다면 에러 페이지로 리턴
        }
    }
    
    @GetMapping("/post/update/{id}")
    public String postUpdate(@PathVariable Integer id, Model model) {
    	Post post = postService.checkPost(id);
    	model.addAttribute("postInfo", post);
    	return "post/postupdate";
    }
    
    @PostMapping("/post/update/{id}")
    public String postUpdate(@PathVariable Integer id, Post post, Model model) {
    	postService.updatePost(id, post);
    		return "redirect:/post/main";
    }
    
    @GetMapping("/post/delete/{id}")
    public String postDelete(@PathVariable Integer id) {
    	postService.deletePost(id);
    	return "redirect:/post/main";
    }
    
    
    @GetMapping("/myposts")
    public String myposts(Model model, HttpSession session,
    		@PageableDefault(size=5, sort="id", direction=Direction.DESC)Pageable pageable) {
    	User user = (User)session.getAttribute("prinicipal");
    	Page<Post> postlist = postService.getMyList(user, pageable);
		model.addAttribute("mylists",postlist.getContent()); // content만 전달
    	
    	return "post/myposts";
    }
}
