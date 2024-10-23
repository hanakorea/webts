package com.example.webts.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webts.DTO.ResponseDTO;
import com.example.webts.domain.User;
import com.example.webts.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
// 회원가입	
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
// 로그인	& 로그 아웃
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
	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
		session.removeAttribute("principal");
		redirectAttributes.addFlashAttribute("logoutMsg", "성공적으로 로그아웃되셨습니다");
		return "redirect:/";
	}
// 회원정보	
	@GetMapping("/userinfo")
	public String userinfo(){
		return "user/userinfo";
	}
	
	@PutMapping("/userinfo")
	@ResponseBody
	public ResponseDTO<?> userUpdate(@RequestBody User user, HttpSession session){
	 User userCheck = userService.userID(user.getUsername());
	 
	 if(userCheck.getUsername().equals(user.getUsername()) ||
			 userCheck.getUsername() == null ) {
		 User userUpdate = userService.userUpdate(user);
		 session.setAttribute("principal", userUpdate);
		 return new ResponseDTO<>(HttpStatus.OK.value(),"정보 수정 완료");
	 }
	 return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "중복된 닉네임 사용불가");
	}
}
