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
			return "/users/loginForm";
		}
		return "/qna/form";
	}
	
	@PostMapping("")
	public String create(String title, String contents, HttpSession session){
		if(!HttpSessionUtils.isLoginUser(session)){
			return "/users/loginForm";
		}
		
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		System.out.println(sessionUser);
		Question newQuestion = new Question(sessionUser, title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable long id, Model model){
		model.addAttribute("question",questionRepository.findOne(id));
		return "/qna/show";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable long id, Model model){
		model.addAttribute("question",questionRepository.findOne(id));
		return "/qna/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable long id, String title, String contents) {
		Question question = questionRepository.findOne(id);
		question.update(title, contents);
		questionRepository.save(question);
		return String.format("redirect:/questions/%d", id);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable long id) {
		questionRepository.delete(id);
		return "redirect:/";
	}
}
