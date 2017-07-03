package com.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String UserId);
}
