package com.study.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.domain.Question;
import com.study.domain.User;
import com.study.repository.QuestionRepository;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/form")
	public String form(HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "/user/login";
		}
		return "/qna/form";
	}
	
	@PostMapping("")
	public String create(String title, String contents, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "/user/login";
		}
		
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionUser, title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable long id, Model model){
		model.addAttribute("question", questionRepository.findOne(id));
		return "/qna/show";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable long id, Model model, HttpSession session){
		Question question = questionRepository.findOne(id);
		try {
			hasPermission(session, question);
		}catch(IllegalStateException e){
			model.addAttribute("errorMessage", e.getMessage());
			return "/user/login";
		}
		model.addAttribute("question", question);
		return "/qna/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable long id, String title, String contents, HttpSession session, Model model) {
		Question question = questionRepository.findOne(id);
		try {
			hasPermission(session, question);
		}catch(IllegalStateException e){
			model.addAttribute("errorMessage", e.getMessage());
			return "/user/login";
		}

		question.update(title, contents);
		questionRepository.save(question);
		return String.format("redirect:/questions/%d", id);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable long id, HttpSession session, Model model){
		System.out.println("delete question");
		Question question = questionRepository.findOne(id);
		try {
			hasPermission(session, question);
		}catch(IllegalStateException e){
			model.addAttribute("errorMessage", e.getMessage());
			System.out.println("failer delete question");
			return "/user/login";
		}
		
		questionRepository.delete(id);
		System.out.println("success delete question");
		return "redirect:/";
	}
	
	private boolean hasPermission(HttpSession session, Question question){
		if(!HttpSessionUtils.isLoginUser(session)){
			throw new IllegalStateException("로그인이 필요합니다.");
		}
		if(!question.isSameWriter(HttpSessionUtils.getUserFromSession(session))){
			throw new IllegalStateException("자신이 쓴 글만 수정, 삭제가 가능합니다.");
		}
		return false;
	}
}
