package com.study.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private long id;
	
	private String writer;
	
	private String title;
	
	private String contents;
	
	public Question(){}
	public Question(String writer, String title, String contents){
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}
}
