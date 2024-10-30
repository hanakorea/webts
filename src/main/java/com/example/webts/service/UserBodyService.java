package com.example.webts.service;

import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import com.example.webts.domain.User;
import com.example.webts.domain.UserBody;
import com.example.webts.repository.UserBodyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserBodyService {
	
	private final UserBodyRepository userBodyRepository;
	
	public UserBody userBodySave(UserBody userBody, User user, double total, double BMR) {
	    UserBody existingUserBody = userBodyRepository.findByUser(user).orElse(null);

	    // 기존 UserBody가 없으면 새로 생성
	    if (existingUserBody == null) {
	        userBody.setUser(user);
	        userBody.setBMR(BMR);
	        return userBodyRepository.save(userBody); 
	    }
	    // 기존 UserBody가 있을 경우 total 값을 업데이트
	    existingUserBody.setTotal(total);
	    existingUserBody.setBMR(BMR);
	    existingUserBody.setWeight(userBody.getWeight());
	    existingUserBody.setHeight(userBody.getHeight());
	    existingUserBody.setAge(userBody.getAge());
	    return userBodyRepository.save(existingUserBody);  
	}


	// 여자일떄, 남자일때
	public double userGender(UserBody userBody) {
		String getUserGender = userBody.getGender().toLowerCase();
		double BMR;
		switch(getUserGender) {
		case "woman":
			BMR = 447.6 + 9.25*userBody.getWeight() + 3.1*userBody.getHeight()-4.33*userBody.getAge();
			break;
		case "man":
			BMR = 88.4 + 13.4*userBody.getWeight() + 4.8*userBody.getHeight()-5.68*userBody.getAge();
			break;
		 default:
	            throw new IllegalArgumentException("Invalid activity level: " + getUserGender);	
		}
		
		 // 소수점 두 자리로 포맷
	    DecimalFormat df = new DecimalFormat("#.00");
	    return Double.parseDouble(df.format(BMR));
		
	}
	// 운동량에 따른 계산메서드
	public double userActive(String active, double BMR) {
	    double activityMultiplier;

	    switch (active.toLowerCase()) {
	        case "less":
	            activityMultiplier = 1.2;
	            break;
	        case "middle":
	            activityMultiplier = 1.55;
	            break;
	        case "high":
	            activityMultiplier = 1.9;
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid activity level: " + active);
	    }

	    double result = BMR * activityMultiplier;

	    // 소수점 두 자리로 포맷
	    DecimalFormat df = new DecimalFormat("#.00");
	    return Double.parseDouble(df.format(result));
	}

	
}
