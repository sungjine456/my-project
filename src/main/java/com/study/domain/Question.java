package com.study.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_question_writer"))
	private User writer;
	
	private String title;
	
	private String contents;
	
	private LocalDateTime createDate;
	
	public Question(){}
	public Question(User writer, String title, String contents){
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		createDate = LocalDateTime.now();
	}
	
	public String getFormattedCreateDate(){
		if(createDate == null){
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}
	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}
	public boolean isSameWriter(User loginUser) {
		return this.writer.equals(loginUser);
	}
}
