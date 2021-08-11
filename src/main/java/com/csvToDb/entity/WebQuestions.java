package com.csvToDb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "csvToDb-questions")
public class WebQuestions {
	
	@Id
	private String questions;
	private String username;
	private String askedOn;
	
	public WebQuestions() {}
	
	public WebQuestions(String questions, String username, String askedOn) {
		super();
		this.questions = questions;
		this.username = username;
		this.askedOn = askedOn;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAskedOn() {
		return askedOn;
	}

	public void setAskedOn(String askedOn) {
		this.askedOn = askedOn;
	}

}
