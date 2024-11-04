package com.example.webts.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.webts.domain.Post;
import com.example.webts.domain.User;
import com.example.webts.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public void savePost(Post post, User user) {
		post.setUser(user);
		postRepository.save(post);
	}
	
	public Post checkPost(Post post) {
		postRepository.findById(post.getId()).orElseGet(()->{
			return new Post();
		});
		return post;
	}
	
	@Transactional(readOnly = true)
	public Page<Post> getPosts(Pageable pageable){
		return postRepository.findAll(pageable);
	}
}
