package com.study.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.domain.Answer;
import com.study.repository.AnswerRepository;
import com.study.repository.QuestionRepository;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	
	@PostMapping("")
	public Answer create(@PathVariable long questionId, String contents, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return null;
		}
		
		Answer answer = new Answer(HttpSessionUtils.getUserFromSession(session), questionRepository.findOne(questionId), contents);
		return answerRepository.save(answer);
	}
}