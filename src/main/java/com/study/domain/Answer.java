package com.study.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Answer {
	@Id
	@GeneratedValue
	@JsonProperty
	private long id;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	@JsonProperty
	private User writer;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
	@JsonProperty
	private Question question;
	
	@Lob
	@JsonProperty
	private String contents;
	
	private LocalDateTime createDate;
	
	public Answer(){}
	public Answer(User writer, Question question, String contents){
		this.writer = writer;
		this.contents = contents;
		this.question = question;
		createDate = LocalDateTime.now();
	}
	
	public String getFormattedCreateDate(){
		if(createDate == null){
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
	}
	public boolean isSameWriter(User loginUser){
		return loginUser.equals(writer);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((writer == null) ? 0 : writer.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (id != other.id)
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Answer [id=" + id + ", writer=" + writer + ", question=" + question + ", contents=" + contents
				+ ", createDate=" + createDate + "]";
	}
}
