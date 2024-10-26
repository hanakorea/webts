package com.example.webts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webts.domain.User;
import com.example.webts.domain.UserBody;

@Repository
public interface UserBodyRepository extends JpaRepository<UserBody, Integer>{
	Optional<UserBody> findByUser(User user);
}
