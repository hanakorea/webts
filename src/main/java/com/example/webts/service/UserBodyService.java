package com.example.webts.service;

import org.springframework.stereotype.Service;

import com.example.webts.domain.User;
import com.example.webts.domain.UserBody;
import com.example.webts.repository.UserBodyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserBodyService {
	
	private final UserBodyRepository userBodyRepository;
	
	public UserBody userBodySave(UserBody userBody, User user, double total) {
	    UserBody existingUserBody = userBodyRepository.findByUser(user).orElse(null);

	    // 기존 UserBody가 없으면 새로 생성
	    if (existingUserBody == null) {
	        userBody.setUser(user);
	        userBody.setTotal(total);
	        return userBodyRepository.save(userBody); 
	    }
	    // 기존 UserBody가 있을 경우 total 값을 업데이트
	    existingUserBody.setTotal(total);
	    existingUserBody.setWeight(userBody.getWeight());
	    existingUserBody.setHeight(userBody.getHeight());
	    existingUserBody.setAge(userBody.getAge());
	    return userBodyRepository.save(existingUserBody);  
	}


	// 여자일떄, 남자일때
	public double userGender(UserBody userBody) {
		if(userBody.getGender().equals("woman")) {
			double BMR = 447.6 + 9.25*userBody.getWeight() + 3.1*userBody.getHeight()-4.33*userBody.getAge();
			return BMR;
		}else {
			double BMR = 88.4 + 13.4*userBody.getWeight() + 4.8*userBody.getHeight()-5.68*userBody.getAge();
			return BMR;
		}
	}
	// 운동량에 따른 계산메서드
	public double userActive(String active, double BMR) {
		if(active.equals("less")) {
			return BMR*1.2;
		}else if(active.equals("middle")) {
			return BMR*1.55;
		}
		return BMR*1.9;	
	}
	
}
