package com.example.webts.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.webts.domain.Reply;
import com.example.webts.domain.User;
import com.example.webts.service.PostService;
import com.example.webts.service.ReplyService;

@Controller
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	

	@PostMapping("/reply/{postid}")
	public String replyUpdate(@PathVariable Integer postid, Reply reply, HttpSession session, Model model) {
	    User user = (User) session.getAttribute("principal");
	    if (user == null) {
	        return "redirect:/login";  // 로그인 페이지로 리다이렉트
	    }
	    replyService.saveReply(reply, postid, user);
	    return "redirect:/post/detail/" + postid;
	}



	
	@GetMapping("/reply/{postid}/{replyid}")
	public String replyDelete(@PathVariable Integer postid ,@PathVariable Integer replyid) {
		
		replyService.deleteReply(replyid);
		
		return"redirect:/post/detail/"+ postid;
	}
	
	
	
}
