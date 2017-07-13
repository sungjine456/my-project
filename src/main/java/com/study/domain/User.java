package com.study.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User extends AbstractEntity {
	@Column(nullable=false, length=20, unique=true)
	@JsonProperty
	private String userId;
	
	@JsonIgnore
	private String password;
	@JsonProperty
	private String name;
	@JsonProperty
	private String email;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean matchId(long newId){
		return super.getId() == newId;
	}
	public boolean matchPassword(String password){
		if(password == null){
			return false;
		}
		return this.password.equals(password);
	}

	public void update(User updateUser) {
		this.userId = updateUser.userId;
		this.password = updateUser.password;
		this.name = updateUser.name;
		this.email = updateUser.email;
	}
	
	@Override
	public String toString() {
		return "User [" + super.toString() + ", userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
}
