package com.study.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.domain.User;
import com.study.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/{id}")
	public User show(@PathVariable long id){
		return userRepository.findOne(id);
	}
}
