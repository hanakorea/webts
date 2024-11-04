package com.example.webts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webts.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

}
