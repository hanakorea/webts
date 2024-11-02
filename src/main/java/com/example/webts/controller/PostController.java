package com.example.webts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostController {
	
	@GetMapping("/postmain")
	public String postmain() {
		return "post/postmain";
	}
	
}
