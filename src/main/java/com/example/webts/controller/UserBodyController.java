package com.example.webts.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.webts.DTO.ResponseDTO;
import com.example.webts.domain.User;
import com.example.webts.domain.UserBody;
import com.example.webts.repository.UserRepository;
import com.example.webts.service.UserBodyService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserBodyController {
	
	private final UserBodyService userBodyService;
	
	// 유저 정보 저장
	@PostMapping("/usercal")
	@ResponseBody
	public ResponseDTO<?> usercal(@RequestBody UserBody userBody, HttpSession session) {
		User user = (User)session.getAttribute("principal"); 
		
		
		double BMR = userBodyService.userGender(userBody);
		double total = userBodyService.userActive(userBody.getActive(), BMR);
		
		UserBody userBodyUpdate = userBodyService.userBodySave(userBody, user, total);
		session.setAttribute("userbody", userBodyUpdate);
		
		return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername()+"님 계산완료");
	}
}
