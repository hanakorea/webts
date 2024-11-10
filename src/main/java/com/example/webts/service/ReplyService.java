package com.example.webts.service;


import org.springframework.stereotype.Service;

import com.example.webts.domain.Post;
import com.example.webts.domain.Reply;
import com.example.webts.domain.User;
import com.example.webts.repository.PostRepository;
import com.example.webts.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {
	
	private final PostRepository postRepository;
	
	private final ReplyRepository replyRepository;

	
	public void saveReply(Reply reply, Integer postid, User user) {
		Post post = postRepository.findById(postid).get();
		reply.setPost(post);
		reply.setUser(user);
		replyRepository.save(reply);
	}
	public void deleteReply(Integer replyid) {
		replyRepository.deleteById(replyid);
	}

}
