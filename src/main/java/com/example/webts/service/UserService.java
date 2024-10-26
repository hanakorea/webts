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
	
	public User userID(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
	public User userFind(String Email) {
		User user = userRepository.findByEmail(Email).orElseGet(()->{
			return new User();
		});
		return user;
	}
	
	public User userUpdate(User user){
		User userUpdate = userFind(user.getEmail());
		
		userUpdate.setUsername(user.getUsername());
		userUpdate.setPassword(user.getPassword());
		
		userRepository.save(userUpdate);
		
		return userUpdate;
	}
	
	public void userDelete(String email) {
		User user = userRepository.findByEmail(email).get();
		userRepository.deleteById(user.getId());
	}
	
}


