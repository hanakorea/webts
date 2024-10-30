package com.example.webts.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.webts.DTO.ResponseDTO;
import com.example.webts.domain.User;
import com.example.webts.domain.UserBody;
import com.example.webts.repository.UserBodyRepository;
import com.example.webts.service.UserBodyService;
import com.example.webts.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	private final UserBodyRepository userBodyRepository;
	
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

	    // 이메일이 유효한지 확인
	    if (user == null || user.getEmail() == null) {
	        model.addAttribute("emailER", "유효하지 않은 이메일");
	        return "user/login";
	    }

	    // 비밀번호 확인
	    if (!password.equals(user.getPassword())) {
	        model.addAttribute("passwordER", "유효하지 않은 비밀번호");
	        return "user/login";
	    }

	    // 세션에 사용자 정보 저장
	    session.setAttribute("principal", user);

	    // 사용자 몸체 정보 가져오기
	    userBodyRepository.findByUser(user).ifPresent(userBodyUpdate -> {
	        session.setAttribute("userbody", userBodyUpdate);
	    });

	    return "redirect:/";
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
    public ResponseDTO<?> userUpdate(@RequestBody User user, HttpSession session) {
        try {
            User userCheck = userService.userID(user.getUsername());

            if (userCheck.getUsername() == null || userCheck.getUsername().equals(user.getUsername())) {
                User userUpdate = userService.userUpdate(user);
                session.setAttribute("principal", userUpdate);
                return new ResponseDTO<>(HttpStatus.OK.value(), "정보 수정 완료");
            }

            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "중복된 닉네임 사용 불가");
        } catch (DataIntegrityViolationException e) {
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "이미 사용 중인 값입니다.");
        } catch (Exception e) {
            return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버 오류: " + e.getMessage());
        }
	    }   

	
// 탈퇴
	@DeleteMapping("/userdelete")
	@ResponseBody
	public ResponseDTO<?> userDelete(@RequestParam String email, HttpSession session){
		userService.userDelete(email);
		session.removeAttribute("principal");
		return new ResponseDTO<>(HttpStatus.OK.value(), "탈퇴 완료");
	}
	


}
