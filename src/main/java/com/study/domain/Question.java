package com.study.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Question extends AbstractEntity {
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_question_writer"))
	@JsonProperty
	private User writer;
	
	@JsonProperty
	private String title;
	
	@Lob
	@JsonProperty
	private String contents;
	
	@JsonProperty
	private int countOfAnswer = 0;
	
	@OneToMany(mappedBy="question")
	@OrderBy("id DESC")
	private List<Answer> answers;
	
	public Question(){}
	public Question(User writer, String title, String contents){
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}
	
	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}
	public boolean isSameWriter(User loginUser) {
		return this.writer.equals(loginUser);
	}
	public void addAnswer() {
		countOfAnswer += 1;
	}
	public void deleteAnswer() {
		countOfAnswer -= 1;
	}
	
	@Override
	public String toString() {
		return "Question [" + super.toString() + ", writer=" + writer + ", title=" + title + ", contents=" + contents + "]";
	}
}
