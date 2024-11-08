package com.example.webts.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webts.domain.Post;
import com.example.webts.domain.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
  Page<Post> findByUserId(User user, Pageable pageable);
}
