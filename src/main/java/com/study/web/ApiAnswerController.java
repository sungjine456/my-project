package com.study.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.domain.Answer;
import com.study.domain.Result;
import com.study.domain.User;
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
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable long questionId, @PathVariable long id, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return Result.fail("로그인해야 합니다.");
		}
		Answer answer = answerRepository.findOne(id);
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!answer.isSameWriter(loginUser)){
			return Result.fail("자신의 글만 삭제할 수 있습니다.");
		}
		answerRepository.delete(answer);
		return Result.ok();
	}
}