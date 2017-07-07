package com.study.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.domain.Answer;
import com.study.repository.AnswerRepository;
import com.study.repository.QuestionRepository;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	
	@PostMapping("")
	public String create(@PathVariable long questionId, String contents, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "redirect:/users/loginForm";
		}
		
		Answer answer = new Answer(HttpSessionUtils.getUserFromSession(session), questionRepository.findOne(questionId), contents);
		answerRepository.save(answer);
		
		return String.format("redirect:/questions/%d", questionId);
	}
}
