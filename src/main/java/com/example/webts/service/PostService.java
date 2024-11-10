package com.example.webts.service;

import org.springframework.transaction.annotation.Transactional;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
	
	private String summary(String content) {
        if (content == null || content.isEmpty()) return "";

        // Jsoup을 사용하여 HTML 파싱
        Document doc = Jsoup.parse(content);

        // 이미지 태그가 있는지 확인하고, 이미지 URL을 추출
        boolean hasImage = !doc.select("img").isEmpty(); // 이미지 태그가 있으면 true

        // 1. 이미지만 있는 경우 "이미지"라는 요약을 반환
        if (hasImage && doc.text().isEmpty()) {
            return "이미지";
        }

        // 2. 글만 있는 경우, 텍스트만 추출하여 50자 이내로 요약
        String textOnly = doc.text();
        if (!hasImage) {
            return textOnly.length() > 50 ? textOnly.substring(0, 50) + "..." : textOnly;
        }

        // 3. 이미지와 텍스트가 혼합된 경우
        // 이미지가 포함되면, "이미지 포함"을 표시하고, 글을 요약
        String summary = textOnly.length() > 50 ? textOnly.substring(0, 50) + "..." : textOnly;
        return summary + " [이미지 포함]";
    	}

	
	public void savePost(Post post, User user) {
		post.setUser(user);
		post.setSummary(summary(post.getContent()));
		postRepository.save(post);
	}
	
  public Post checkPost(Integer postId) {
      return postRepository.findById(postId).orElse(new Post());  // 게시물 ID로 찾고 없으면 새 객체 반환
    }
  
	@Transactional(readOnly = true)
	public Page<Post> getPosts(Pageable pageable){
		return postRepository.findAll(pageable);
	}
	
	
	public void updatePost(Integer id, Post p) {
		Post post = postRepository.findById(id).get();
		post.setTitle(p.getTitle());
		post.setContent(p.getContent());
		post.setSummary(summary(p.getContent()));
		postRepository.save(post);
	}
	
	public void deletePost(Integer id) {
		postRepository.deleteById(id);
	}
	
	public Page<Post> getMyList(Integer userId, Pageable pageable){
		return postRepository.findByUserId(userId, pageable);	
	}
}
