package com.example.webts.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.webts.DTO.PostDTO;
import com.example.webts.domain.Post;
import com.example.webts.domain.User;
import com.example.webts.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {
	
	private final PostService postService;
	
	private final ModelMapper modelMapper;
	
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
	public String postCreate(@Valid PostDTO postDTO, BindingResult bindingResult, 
			Model model, HttpSession session) {
		if(bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errors.add(error.getDefaultMessage());
			}
			model.addAttribute("errors",errors);
			return "post/postcreate";
		}
		Post post =modelMapper.map(postDTO, Post.class);
		
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
    public String postUpdate(@Valid PostDTO postDTO, BindingResult bindingResult, @PathVariable Integer id, Model model) {
    	if(bindingResult.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errors.add(error.getDefaultMessage());
			}
			Post postSave = postService.checkPost(id);
			model.addAttribute("errors",errors);
			model.addAttribute("postInfo", postSave);
			return"post/postupdate";
		}
		Post post = modelMapper.map(postDTO, Post.class);
		
    	postService.updatePost(id, post);
    		return "redirect:/post/main";
    }
    
    @GetMapping("/post/delete/{id}")
    public String postDelete(@PathVariable Integer id) {
    	postService.deletePost(id);
    	return "redirect:/post/main";
    }
    
    // user로 db가져오지 못하고 있음..
    @GetMapping("/myposts")
    public String myposts(Model model, HttpSession session,
    		@PageableDefault(size=5, sort="id", direction=Direction.DESC)Pageable pageable) {
    	User user = (User)session.getAttribute("principal");
    	Page<Post> postlist = postService.getMyList(user.getId(), pageable);
		model.addAttribute("mylists",postlist.getContent()); // content만 전달
    	
    	return "post/myposts";
    }
}
