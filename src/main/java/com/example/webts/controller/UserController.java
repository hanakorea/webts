package com.example.webts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.webts.DTO.ResponseDTO;
import com.example.webts.domain.User;
import com.example.webts.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup() {
		return "user/signup";
	}
	
	@PostMapping("/signup")
	@ResponseBody
	public ResponseDTO<?> signup(@RequestBody User user) {

		User userSignup = userService.userFind(user.getUsername());
		
		if(userSignup.getUsername() == null) {
			userService.userSet(user);
			return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() +"님 회원가입 완료");
		}
		return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + "이미 사용 중인 아이디");
	}
}
