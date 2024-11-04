package com.example.webts.controller;


import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
		return "post/postcreat";
	}
	
	@PostMapping("/post/create")
	public String postCreate(Post post, HttpSession session) {
		User user = (User)session.getAttribute("principal");
		postService.savePost(post, user);
		return "redirect:/post/main";
	}
	
	
}
