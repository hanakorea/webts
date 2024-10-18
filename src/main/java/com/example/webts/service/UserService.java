package com.example.webts.service;

import org.springframework.stereotype.Service;

import com.example.webts.domain.RoleType;
import com.example.webts.domain.User;
import com.example.webts.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	public void userSet(User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	public User userFind(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
}
