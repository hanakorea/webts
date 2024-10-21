package com.example.webts.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

		User userCheckID = userService.userID(user.getUsername());
		User userCheckEmail = userService.userFind(user.getEmail());
		
		if(userCheckID.getUsername() == null && userCheckEmail.getEmail() == null ) {
			userService.userSet(user);
			return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() +"님 회원가입 완료");
		}
		
		if(userCheckID.getUsername() != null) {
			return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getUsername() + "중복 아이디");
		}
		return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), user.getEmail() + "이미 사용 중인 이메일");
	}
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String login(String email, String password, Model model, HttpSession session) {
		User user = userService.userFind(email);
		
		if(user != null && user.getEmail() != null) {	
			if(password.equals(user.getPassword())){
				session.setAttribute("principal", user);
				return "redirect:/";
			}else {
				model.addAttribute("passwordER", "유효하지 않은 비밀번호");
			}
		}else {
			model.addAttribute("emailER", " 유효하지 않은 이메일");			
		}
		return "user/login";
	}
}
